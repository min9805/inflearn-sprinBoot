package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class springConfiguration {

//	private DataSource dataSource;
//
//	@Autowired
//	public springConfiguration(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

//	private EntityManager em;
//
//	@Autowired
//	public springConfiguration(EntityManager em) {
//		this.em = em;
//	}

	private final MemberRepository memberRepository;

	public springConfiguration(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
//

//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}
