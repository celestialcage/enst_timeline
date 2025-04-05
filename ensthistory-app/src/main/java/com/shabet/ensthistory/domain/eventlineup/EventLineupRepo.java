package com.shabet.ensthistory.domain.eventlineup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventLineupRepo extends JpaRepository<EventLineup, Long> {
    List<EventLineup> findByEventId(Integer id);
}
