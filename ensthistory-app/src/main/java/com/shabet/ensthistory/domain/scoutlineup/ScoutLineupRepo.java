package com.shabet.ensthistory.domain.scoutlineup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoutLineupRepo extends JpaRepository<ScoutLineup, Long> {
    List<ScoutLineup> findByScoutId(Integer id);
}
