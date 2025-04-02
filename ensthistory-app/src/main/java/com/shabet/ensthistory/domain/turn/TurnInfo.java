package com.shabet.ensthistory.domain.turn;

import com.shabet.ensthistory.domain.chirregular.ChIrregular;
import com.shabet.ensthistory.domain.event.Event;
import com.shabet.ensthistory.domain.scout.Scout;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class TurnInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turn_id")
    private Integer id;

    @Column(name = "turn_seq")
    private Integer seq;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "theme_scout_id")
    private Scout themeScout;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "feature_scout_id")
    private Scout featureScout;

    @ManyToOne
    @JoinColumn(name = "game_year")
    private YearSeason gameYear;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ch_scout_id", referencedColumnName = "ch_id")
    private ChIrregular chScout;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ch_irr_id", referencedColumnName = "ch_id")
    private ChIrregular chIrregular;

    protected TurnInfo() {}

}
