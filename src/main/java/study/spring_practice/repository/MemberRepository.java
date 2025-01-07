package study.spring_practice.repository;

import study.spring_practice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // find 했을 시 값이 없어 null 반환될 경우를 대비해 optional로 값을 감싸 반환함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
