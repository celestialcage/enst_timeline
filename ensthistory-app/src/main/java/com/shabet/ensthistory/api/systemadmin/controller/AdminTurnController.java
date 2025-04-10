package com.shabet.ensthistory.api.systemadmin.controller;

import com.shabet.ensthistory.domain.turn.dto.IdListDto;
import com.shabet.ensthistory.domain.turn.dto.TurnGridRequestDto;
import com.shabet.ensthistory.domain.turn.dto.TurnGridResponseDto;
import com.shabet.ensthistory.service.turninfo.TurnInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/system-admin/turns")
public class AdminTurnController {

    private final TurnInfoService turnInfoService;

    public AdminTurnController(TurnInfoService turnInfoService) {
        this.turnInfoService = turnInfoService;
    }

    @GetMapping
    public ResponseEntity<List<TurnGridResponseDto>> getAllTurns() {

        List<TurnGridResponseDto> turns = turnInfoService.findAllTurns();

        return ResponseEntity.ok(turns);
    }

    @PostMapping
    public ResponseEntity<String> createTurn(@RequestBody TurnGridRequestDto request) {
        // 잘못된 요청 거르기... 할 수 있는 것들...
        // 1. 유효성 검증 / 2. 요청 파라미터, url 검증(PathVariable, RequestParam) / 3. 비즈니스 로직 검증 (중복 체크 등)
        // 4. 인층, 권한 처리(로그인 여부, 역할) / 5. HTTP 상태 코드 활용 / 6. 글로벌 예외 처리(@ControllerAdvice)
        if (request.getTurnSeq() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("턴 수는 0이 될 수 없습니다.");
        }

        try {
            System.out.println("된거니..?");
            System.out.println(request.getEventTitle());
            System.out.println("된거야..?");

            // 성공적으로 처리되면 201 Created 응답
            return ResponseEntity.status(HttpStatus.CREATED).body("턴이 생성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while creating turn: " + e.getMessage());
        }
    }

    @PostMapping("/list")
    public void createTurns(@RequestBody List<TurnGridRequestDto> request) {

    }

    @PatchMapping("/{id}")
    public void updateTurn(@PathVariable("id") Integer id, @RequestBody TurnGridRequestDto request) {

    }

    @PatchMapping("/list")
    public void updateTurns(@RequestBody List<TurnGridRequestDto> request) {

    }

    @DeleteMapping("/{id}")
    public void deleteTurn(@PathVariable("id") Integer id) {

    }

    @DeleteMapping("/list")
    public void deleteTurn(@RequestBody IdListDto idListDto) {

    }

}
