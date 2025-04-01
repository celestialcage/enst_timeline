package com.shabet.ensthistory.domain.turn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class YearSeason {

    @Id
    private Integer gameYear;

    private String season;

    @Column(nullable = false)
    private LocalDate startDate;
}
