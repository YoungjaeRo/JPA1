package JPA1.spring;

import org.springframework.stereotype.Repository;

import JPA1.spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MemberRepository {
	@PersistenceContext
	private EntityManager em; // 알아서 엔터티 매니저를 생성해줌

	public Long save(Member member) {
		em.persist(member);
		return member.getId();

	}

	public Member find(Long id) {
		return em.find(Member.class, id);

	}
}
