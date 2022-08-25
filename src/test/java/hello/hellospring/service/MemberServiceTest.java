package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {
	MemberService memberService;
	MemoryMemberRepository   memberRepository;

	@BeforeEach
	void beforeEach(){
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	void afterEach(){
		memberRepository.clearStore();
	}

	@Test
	void join() {
		//given
		Member member1 = new Member();
		member1.setName("Spring");

		//when
		Long saveId = memberService.join(member1);

		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(findMember).isEqualTo(member1);
	}

	@Test
	void joinDuplicate() {
		//given
		Member member1 = new Member();
		member1.setName("Spring");

		Member member2 = new Member();
		member2.setName("Spring");

		//when
		memberService.join(member1);

		//then
		Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));

	}

	@Test
	void findAll() {
		//given
		Member member1 = new Member();
		member1.setName("Spring1");
		memberService.join(member1);

		Member member2 = new Member();
		member2.setName("Spring2");
		memberService.join(member2);

		//when
		List<Member> result = memberService.findMembers();

		//then
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	void findOne() {
		//given
		Member member1 = new Member();
		member1.setName("Spring1");
		memberService.join(member1);

		//when
		Optional<Member> result = memberService.findOne(member1.getId());

		//then
		assertThat(result.get()).isEqualTo(member1);
	}
}