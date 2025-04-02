package com.shabet.ensthistory.domain.event;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.eventlineup.EventLineup;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType type;

    @Column(name = "event_title", length = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "first_char_id")
    private Character firstCharacter;

    @ManyToOne
    @JoinColumn(name = "second_char_id")
    private Character secondCharacter;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventLineup> lineups = new ArrayList<EventLineup>();

    @Column(name = "event_org_title", length = 100)
    private String orgTitle;

    @Column(name = "event_transl_title", length = 100)
    private String translTitle;

    protected Event() {}
}
