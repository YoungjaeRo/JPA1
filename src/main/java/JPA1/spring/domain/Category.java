package JPA1.spring.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
@Entity
@Getter @Setter
public class Category {
	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "category_item",
		joinColumns = @JoinColumn(name = "category_id"),
		inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items = new ArrayList<>();

	// 카테고리 계층 구조 표현
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();


	//==연관관계 메서드==// (다시 한번 말하지만, 양방향일때, 사용하면 좋음)
	public void addChildCategory(Category child){
		this.child.add(child);
		child.setParent(this);
	}
}
