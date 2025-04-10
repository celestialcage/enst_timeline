package com.shabet.ensthistory.service.turninfo;

import com.shabet.ensthistory.domain.eventlineup.EventLineup;
import com.shabet.ensthistory.domain.eventlineup.EventLineupRepo;
import com.shabet.ensthistory.domain.scoutlineup.ScoutLineup;
import com.shabet.ensthistory.domain.scoutlineup.ScoutLineupRepo;
import com.shabet.ensthistory.domain.turn.TurnInfo;
import com.shabet.ensthistory.domain.turn.TurnInfoRepository;
import com.shabet.ensthistory.domain.turn.dto.TurnGridResponseDto;
import com.shabet.ensthistory.domain.turn.dto.TurnResponseRowDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TurnInfoService {
    private final TurnInfoRepository turnInfoRepository;
    private final EventLineupRepo eventLineupRepo;
    private final ScoutLineupRepo scoutLineupRepo;

    public TurnInfoService(TurnInfoRepository turnInfoRepository, EventLineupRepo eventLineupRepo, ScoutLineupRepo scoutLineupRepo) {
        this.turnInfoRepository = turnInfoRepository;
        this.eventLineupRepo = eventLineupRepo;
        this.scoutLineupRepo = scoutLineupRepo;
    }

    public List<TurnGridResponseDto> findAllTurns() {
        List<TurnInfo> turnInfos = turnInfoRepository.findAll();
        // 여기서 엔티티에서 가져온 것들을 싹다 매핑해야하는거겠지 너무 많아 매퍼 필요해
        // -> 따로 메서드, 스트림으로 해결됐군

        return turnInfos.stream().map(this::toTurnGridDto).collect(Collectors.toList());
    }

    public TurnGridResponseDto toTurnGridDto(TurnInfo turn) {
        TurnGridResponseDto dto = new TurnGridResponseDto();
        dto.setTurnSeq(turn.getSeq());
        dto.setGameYear(dto.getGameYear());
        dto.setEventType(dto.getEventType());
        dto.setEventTitle(dto.getEventTitle());
        dto.setThemeScout(dto.getThemeScout());
        dto.setFeatureScout(dto.getFeatureScout());
        dto.setChScout(dto.getChScout());
        dto.setChIrregular(dto.getChIrregular());
        dto.setChScoutChar(dto.getChScoutChar());
        dto.setChScoutCardAttr(dto.getChScoutCardAttr());
        dto.setChScoutCardProp(dto.getChScoutCardProp());
        dto.setChIrrChar(dto.getChIrrChar());
        dto.setChIrrCardAttr(dto.getChIrrCardAttr());
        dto.setChIrrCardProp(dto.getChIrrCardProp());

        List<EventLineup> eventLineups = eventLineupRepo.findByEventId(turn.getEvent().getId());
        List<ScoutLineup> tScoutLineups = scoutLineupRepo.findByScoutId(turn.getThemeScout().getId());
        List<ScoutLineup> fScoutLineups = scoutLineupRepo.findByScoutId(turn.getFeatureScout().getId());

        List<TurnResponseRowDto> rows = mergeLineups(eventLineups, tScoutLineups, fScoutLineups);
        dto.setRows(rows);

        return dto;
    }

    public List<TurnResponseRowDto> mergeLineups(List<EventLineup> eventLineups, List<ScoutLineup> tScoutLineups, List<ScoutLineup> fScoutLineups) {
        int maxLength = Stream.of(
                eventLineups.size(),
                tScoutLineups.size(),
                fScoutLineups.size()
//                , chScoutLineups.size(),
//                chIrrLineups.size()
        ).max(Integer::compare).orElse(0);

        List<TurnResponseRowDto> result = new ArrayList<>();

        for (int i = 0; i < maxLength; i++) {
            TurnResponseRowDto row = new TurnResponseRowDto();
            row.setOrder(i + 1); // 1부터 시작

            if (i < eventLineups.size()) {
                EventLineup e = eventLineups.get(i);
                row.setEventChar(e.getCharacter().getName());
                row.setEventCardAttr(e.getCardAttr().name());
                row.setEventCardProp(e.getCardProp().name());
            }

            if (i < tScoutLineups.size()) {
                ScoutLineup t = tScoutLineups.get(i);
                row.setTScoutChar(t.getCharacter().getName());
                row.setTScoutCardAttr(t.getCardAttr().name());
                row.setTScoutCardProp(t.getCardProp().name());
            }

            if (i < fScoutLineups.size()) {
                ScoutLineup f = fScoutLineups.get(i);
                row.setFScoutChar(f.getCharacter().getName());
                row.setFScoutCardAttr(f.getCardAttr().name());
                row.setFScoutCardProp(f.getCardProp().name());
            }

            result.add(row);
        }

        return result;
    }
}
