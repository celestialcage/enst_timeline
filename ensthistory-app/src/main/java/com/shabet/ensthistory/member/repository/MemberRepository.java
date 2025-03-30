package com.shabet.ensthistory.member.repository;

import com.shabet.ensthistory.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
