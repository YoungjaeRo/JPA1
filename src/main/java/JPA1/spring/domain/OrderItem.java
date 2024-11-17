package JPA1.spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	private int orderPrice; // 주문 당시의 가격

	private int count; //  주문당시의 수량

	// 생성 메서드 //
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count); // 주문한 만큼 수량에서 제외
		return orderItem;
	}

	// 비즈니스 로직 //
	public void cancel() {
		//취소를 했기 때문에, 수량을 취소한 만큼 다시 늘려줌
		getItem().addStock(count);
	}

	// 조회 로직 //
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
