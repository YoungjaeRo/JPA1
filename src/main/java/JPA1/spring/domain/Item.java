package JPA1.spring.domain;

import java.util.ArrayList;
import java.util.List;

import JPA1.spring.exception.NotEnoughStockException;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // 이 속성에 따라 자식 테이블들을 구분함
@Getter
@Setter
public abstract class Item {
	@Id
	@GeneratedValue // id가 실제로 persist를 통해 영속성 컨텍스트에 포함되기 전까지는, id값을 할당하지 않음
	@Column(name = "item_id")
	private Long id;

	private String name;

	private int price;

	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories= new ArrayList<>();

// 비즈니스 로직

	public void addStock(int quantity){
		this.stockQuantity += quantity;
	}
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;

		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}

		this.stockQuantity = restStock;
	}

}
