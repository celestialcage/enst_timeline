package com.shabet.ensthistory.domain.scout;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.scoutlineup.ScoutLineup;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Scout {

    @Id
    @GeneratedValue
    @Column(name = "scout_id")
    private Integer id;

    @Column(name = "scout_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "scout_type", nullable = false)
    private ScoutType type;

    @ManyToOne
    @JoinColumn(name = "main_char_id")
    private Character mainCharacter;

    @OneToMany(mappedBy = "scout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScoutLineup> lineups = new ArrayList<ScoutLineup>();

    @Column(name = "scout_org_name")
    private String orgName;

    @Column(name = "scout_transl_name")
    private String translName;

    protected Scout() {}

}
