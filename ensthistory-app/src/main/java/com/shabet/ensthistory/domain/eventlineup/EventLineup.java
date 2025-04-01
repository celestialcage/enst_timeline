package com.shabet.ensthistory.domain.eventlineup;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.common.enums.CardAttr;
import com.shabet.ensthistory.domain.common.enums.CardProps;
import com.shabet.ensthistory.domain.event.Event;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class EventLineup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "char_id", nullable = false)
    private Character character;

    private Integer cardClass;

    @Enumerated(EnumType.STRING)
    private CardProps cardProp;

    @Enumerated(EnumType.STRING)
    private CardAttr cardAttr;
}
