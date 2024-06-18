package kr.co.survive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.survive.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	public MemberEntity findByUsername(String username);
}
