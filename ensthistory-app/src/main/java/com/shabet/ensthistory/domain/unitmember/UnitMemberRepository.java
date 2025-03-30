package com.shabet.ensthistory.domain.unitmember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMemberRepository extends JpaRepository<UnitMember, Long> {
}
