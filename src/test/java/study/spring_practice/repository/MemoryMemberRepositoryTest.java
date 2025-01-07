package study.spring_practice.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.spring_practice.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 굳이 public 일 필요는 없다. 테스트 에서만 사용하니까!
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 테스트에서의 메서드들은 실행 순서가 항상 무작위이다.
     * 따라서 실행 순서에 관계없이 항상 참이 되게 해야한다.
     * ##테스트가 끝날 시 데이터 클리어가 필요하다##
     */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // junit.jupiter 버전. 출력하지 않고 run 콘솔창 상태로 동작 확인 할 수 있음
//        Assertions.assertEquals(member, result);

        // assertj 버전 사용법
//        Assertions.assertThat(member).isEqualTo(result);

        // assertj 버전에서 alt + enter 시 더 간편버전으로 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + f6 시 빠른 rename
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
