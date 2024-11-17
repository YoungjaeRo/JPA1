package JPA1.spring.repository;

import static org.springframework.data.jpa.domain.AbstractPersistable_.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import JPA1.spring.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) { // 아직까지는 id는 등록되지 않았지 떄문에, id는 null임
			em.persist(item); // persist를 통해, 영속성 컨텍스트에 저장하면서 id를 할당함
		} else {
			em.merge(item);
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}

