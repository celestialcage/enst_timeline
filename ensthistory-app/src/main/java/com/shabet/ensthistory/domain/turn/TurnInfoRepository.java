package com.shabet.ensthistory.domain.turn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnInfoRepository extends JpaRepository<TurnInfo, Integer> {
}
