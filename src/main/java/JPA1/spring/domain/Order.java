package JPA1.spring.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //주문 회원


	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 자식이 부모따라 영향을 받음, 주로 OneToMany에서 사용됨(부모 엔티티쪽)
	private List<OrderItem> orderItems = new ArrayList<>();


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery; //배송정보


	private LocalDateTime orderDate; //주문시간


	@Enumerated(EnumType.STRING)
	private OrderStatus status; //주문상태 [ORDER, CANCEL]



	//==연관 관계 편의 메서드 (엔터티가 양방향일때 사용하면 좋음 & 주로 컨트롤 하는 쪽에서 메서드 생성)
	public void setMember(Member member) {
		this.member = member; // 일단 본인들이 참조하는 부모 엔터티를 선택, 그리고 그 부모 엔터티의 컬렉션 변수에 본인들의 객체를(Order)을 저장
		member.getOrders().add(this); // 리스트 자체의 참조값을 가져와서, 현재 Order 객체를 저장
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

}