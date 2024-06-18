package kr.co.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.user.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{
	public MemberEntity findByUsername(String username);
}
