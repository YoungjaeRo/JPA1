package JPA1.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import JPA1.spring.domain.Member;
import JPA1.spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // 생성자 주입을 자동으로 처리해줌
public class MemberService {

	private final MemberRepository memberRepository;

	//회원 가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복되는 회원이 있는 지 확인후, 아무 문제 없다면, 아래 실행
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()){ // 해당 이름을 가지고 있는 멤버 객체가 있으면 예외 발생
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	//회원 전체 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId){
		return memberRepository.findOne(memberId);
	}

}
