package com.sunbeam.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.models.OrderStatus;
import com.sunbeam.models.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String orderId;
	
	@ManyToOne
	private User user;
	
	private Long sellerId;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderitems = new ArrayList<>();
	
	@ManyToOne
	private Address shipingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalMrpPrice;
	
	private Integer totalSellingPrice;
	
	private Integer discount;
	
	private OrderStatus orderStatus;
	
	private int totalItem;
	
	private PaymentStatus paymentStatus = PaymentStatus.PENDING;
	
	private LocalDateTime orderDate = LocalDateTime.now();
	private LocalDateTime deliverDate = orderDate.plusDays(7);
	
	
}
