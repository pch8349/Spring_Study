package study.spring_practice.repository;

import study.spring_practice.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 공유되는 변수의 경우 concurrent hashmap 사용해야함
    private static Map<Long, Member> store = new HashMap<>();

    // 실무에선 동시성 문제 고려해 automic Long 사용해야 한다고 함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        // null 이어도 반환이 되게 optional로 감싸준다. 이후 처리는 client 에서 처리한다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        // java 8 람다표현식.
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 파라미터로 받은 name 과 같은 member 가 있는지
                .findAny(); // findAny 는 하나라도 반환
        // 하나도 없을 시 optional 에 null 이 포함되어 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트 시에만 사용하므로(테스트에서는 인터페이스 호출을 하지 않도록 작성해서) 인터페이스를 override 하지 않음
    public void clearStore(){
        store.clear();
    }
}
