package JPA1.spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import JPA1.spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// 스프링에서 JPA를 사용할 때, EntityManager 객체를 자동으로 주입받는 방법
	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}
	public Member findOne(Long id) {
		Member member = em.find(Member.class, id);
		return member;
	}

	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
	}
}
