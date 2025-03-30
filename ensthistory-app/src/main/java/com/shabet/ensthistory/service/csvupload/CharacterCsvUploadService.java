package com.shabet.ensthistory.service.csvupload;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.character.CharacterRepository;
import com.shabet.ensthistory.domain.character.dto.CharacterCsvDto;
import com.shabet.ensthistory.domain.unit.Unit;
import com.shabet.ensthistory.domain.unit.UnitRepository;
import com.shabet.ensthistory.domain.unitmember.UnitMember;
import com.shabet.ensthistory.domain.unitmember.UnitMemberRepository;
import com.shabet.ensthistory.global.utils.CsvUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CharacterCsvUploadService {

    private final UnitRepository unitRepository;
    private final CharacterRepository characterRepository;
    private final UnitMemberRepository unitMemberRepository;

    public CharacterCsvUploadService(UnitRepository unitRepository, CharacterRepository characterRepository, UnitMemberRepository unitMemberRepository) {
        this.unitRepository = unitRepository;
        this.characterRepository = characterRepository;
        this.unitMemberRepository = unitMemberRepository;
    }

    @Transactional
    public void uploadCharacterWithRelation(MultipartFile file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;

            Map<String, Integer> headerMap = CsvUtils.parseHeader(reader.readLine());
            reader.readLine(); // 설명행 스킵

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                CharacterCsvDto dto = CsvUtils.toCharacterCsvDto(tokens, headerMap);

                // 얘는 쿼리가 너무 많아서 정규유닛 캐싱으로 변경★
                // Unit unit = unitRepository.findByKorName(dto.getMainUnitName()).orElseThrow(() -> new IllegalArgumentException("해당 유닛 없음: " + dto.getMainUnitName()));
                Map<String, Unit> unitMap = unitRepository.findAll().stream()
                        .collect(Collectors.toMap(Unit::getKorName, Function.identity()));
                // Function.identity() 없이 람다로 쓰는 법. 하지만 위가 더 간결, 깔끔해서 실무에서 많이 쓴다 함.
                // .collect(Collectors.toMap(unit -> unit.getKorName(), unit -> unit));
                Unit unit = unitMap.get(dto.getMainUnitName());
                if (unit == null) throw new IllegalArgumentException("없는 유닛: " + dto.getMainUnitName());


                // 1. 캐릭터 먼저 저장
                Character character = new Character(dto, unit);
                characterRepository.save(character);

                // 2. 관계 테이블에 매핑 저장
                UnitMember relation = new UnitMember(unit, character, dto.getMemOrder());
                unitMemberRepository.save(relation);
            }
        }

    }
}
