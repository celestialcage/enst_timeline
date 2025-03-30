package com.shabet.ensthistory.domain.unitmember;

import com.shabet.ensthistory.domain.character.Character;
import com.shabet.ensthistory.domain.unit.Unit;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UnitMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "char_id", nullable = false)
    private Character character;

    @Column(name = "mem_order")
    private Integer memOrder;

    protected UnitMember() {}

    public UnitMember(Unit unit, Character character, Integer memOrder) {
        this.unit = unit;
        this.character = character;
        this.memOrder = memOrder;
    }
}
