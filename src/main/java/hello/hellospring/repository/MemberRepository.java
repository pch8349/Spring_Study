package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 java 8부터 들어간 기능. 데이터가 없으면 null로 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
