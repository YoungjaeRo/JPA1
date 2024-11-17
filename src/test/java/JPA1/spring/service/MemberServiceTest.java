package JPA1.spring.service;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import JPA1.spring.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //데이터를 실제로 변경(commit / rollback) 해야하기 때문에
public class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;


	@Test
	public void 회원가입() throws Exception {
		//given (이렇게 주어졌을때 ~)



		// when (이렇게 하면 ~)


		//then (이렇게 되어야 한다)


	}


	@Test
	public void 중복_회원_예외() throws Exception {
		//given



		// when


		//then




	}

}