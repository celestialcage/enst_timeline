package com.shabet.ensthistory.domain.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByKorName(String korName);
}
