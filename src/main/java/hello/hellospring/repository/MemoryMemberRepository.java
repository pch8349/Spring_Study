package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ // alt + Enter 하면 인터페이스의 모든 인자들 가져옴

    private static Map<Long, Member> store = new HashMap<>(); //회원 키는 long으로, 값은 member로 저장.
    //공유되는 변수는 동시성 문제가 있어 ConcurrentHashMap을 써야함. 예제니깐~
    private static long sequence = 0L; // key값을 생성해주는 (ex. 0, 1, 2 등등) 역할. 역시 동시성 문제로 AtomicLong 사용하기

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//get했을때 null 발생 방지를 위해 Optional.ofNullable()로 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // values() 는 store에 있는 모든 상수를 저장한 배열을 생성해 반환, stream()은 컬렉션에 저장되어 있는 엘리먼트들을 하나씩 순회하면서 처리
                .filter(member -> member.getName().equals(name)) // filter로 반복 돌리기. member와 getname한게 하나라도 같으면
                .findAny(); // 하나라도 찾으면 결과가 optional로 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // list에 store에 있는 값들을 반환
    }

    public void clearStore(){
        store.clear();
    }
}
