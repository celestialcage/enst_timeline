package com.shabet.ensthistory.api.systemadmin.controller;

import com.shabet.ensthistory.domain.turn.dto.IdListDto;
import com.shabet.ensthistory.domain.turn.dto.TurnGridRequestDto;
import com.shabet.ensthistory.domain.turn.dto.TurnGridResponseDto;
import com.shabet.ensthistory.service.turninfo.TurnInfoService;
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
    public void createTurn(@RequestBody TurnGridRequestDto request) {

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
