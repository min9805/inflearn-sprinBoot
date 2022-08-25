package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("name");

        //when
        Member result = memberRepository.save(member);

        //then
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById(){
        //given
        Member member = new Member();
        member.setName("name");
        memberRepository.save(member);

        //when
        Member result = memberRepository.findById(1L).get();

        //then
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("Spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memberRepository.save(member2);

        //when
        Member result = memberRepository.findByName("Spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        //given
        Member member = new Member();
        member.setName("MemberName");
        memberRepository.save(member);

        Member member2 = new Member();
        member.setName("MemberName2");
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
