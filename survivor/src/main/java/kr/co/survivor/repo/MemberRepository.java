package kr.co.survivor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.survivor.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	public MemberEntity findByUsername(String username);
}
