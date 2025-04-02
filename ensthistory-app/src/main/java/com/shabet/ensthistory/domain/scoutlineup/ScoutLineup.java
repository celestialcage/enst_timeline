package com.shabet.ensthistory.domain.scoutlineup;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.common.enums.CardAttr;
import com.shabet.ensthistory.domain.common.enums.CardProps;
import com.shabet.ensthistory.domain.scout.Scout;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ScoutLineup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scout_id", nullable = false)
    private Scout scout;

    @ManyToOne
    @JoinColumn(name = "char_id", nullable = false)
    private Character character;

    private Integer cardClass;

    @Enumerated(EnumType.STRING)
    private CardProps cardProp;

    @Enumerated(EnumType.STRING)
    private CardAttr cardAttr;

    protected ScoutLineup() {}
}