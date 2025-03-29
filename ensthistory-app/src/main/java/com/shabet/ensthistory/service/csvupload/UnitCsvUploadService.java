package com.shabet.ensthistory.service.csvupload;

import com.shabet.ensthistory.domain.unit.Unit;
import com.shabet.ensthistory.domain.unit.UnitRepository;
import com.shabet.ensthistory.domain.unit.dto.UnitCsvDto;
import com.shabet.ensthistory.global.utils.CsvUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@Service
public class UnitCsvUploadService {

    private final UnitRepository unitRepository;

    public UnitCsvUploadService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Transactional
    public void uploadFromCsv(MultipartFile file) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;

            Map<String, Integer> headerMap = CsvUtils.parseHeader(reader.readLine());
            reader.readLine(); // 설명행 스킵

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                UnitCsvDto dto = CsvUtils.toUnitCsvDto(tokens, headerMap); // 유틸로 분리 가능
                Unit unit = new Unit(dto);
                unitRepository.save(unit);
            }
        }
    }
}
