package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // test용이라 굳이 public 안해도됨

     MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //한 메서드가 끝날 때마다 실행됨. 콜백메서드
    public void afterEach(){
        repository.clearStore(); // 아래 함수들은 순서대로 실행되지 않아서 한번에 실행시 member에 값이 달라져서 에러가 남.
        //따라서 store에 값을 지워줌
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // findById()의 반환타입이 optional이기에 .get()으로 읽어옴
        Assertions.assertEquals(member, result); // 같은지 판별하기, 비교할 값을 뒤에 두기
//        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); Assertions 에서 Alt + Enter 하면 아래처럼 줄여서 됨
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
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
