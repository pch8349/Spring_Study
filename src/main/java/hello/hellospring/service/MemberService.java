package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member){
        //중복 이름 회원은 안됨
//        memberRepository.findByName(member.getName()); 에서 ctrl+alt+v 하면 아래처럼 optional로 바뀜.
//        Optional<Member> result = memberRepository.findByName(member.getName()); //
//        result.ifPresent(m ->  {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });//ifPresent() == 만약 값이 있으면

        //아래처럼 바로 하는것도 방법
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // ctrl + alt + m 하면 따로 함수로 뽑아줌
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
