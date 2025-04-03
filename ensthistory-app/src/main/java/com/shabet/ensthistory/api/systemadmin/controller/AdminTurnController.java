package com.shabet.ensthistory.api.systemadmin.controller;

import com.shabet.ensthistory.domain.turn.dto.TurnGridRequestDto;
import com.shabet.ensthistory.domain.turn.dto.TurnGridResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/system-admin/turns")
public class AdminTurnController {

    @GetMapping
    public List<TurnGridResponseDto> getAllTurns() {

        List<TurnGridResponseDto> turns = new ArrayList<TurnGridResponseDto>();

        return turns;
    }

    @PostMapping
    public void createTurn(@RequestBody TurnGridRequestDto request) {

    }

    @PatchMapping("/{id}")
    public void updateTurn(@PathVariable("id") Integer id, @RequestBody TurnGridRequestDto request) {

    }

    @DeleteMapping("/{id}")
    public void deleteTurn(@PathVariable("id") Integer id) {

    }

}
