package com.shabet.ensthistory.domain.chirregular;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.common.enums.CardAttr;
import com.shabet.ensthistory.domain.common.enums.CardProps;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ChIrregular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private Integer id;

    @Column(name = "ch_irr_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ch_irr_type")
    private ChIrrType type;

    @ManyToOne
    @JoinColumn(name = "main_char_id")
    private Character mainCharacter;

    private Boolean isCollab;

    @Column(name = "ch_irr_detail")
    private String detail;

    @Enumerated(EnumType.STRING)
    private CardProps mainCardProp;

    @Enumerated(EnumType.STRING)
    private CardAttr mainCardAttr;
}
