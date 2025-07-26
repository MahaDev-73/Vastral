package com.sunbeam.entities;

import java.util.HashSet;
import java.util.Set;

import com.sunbeam.models.PaymentMethod;
import com.sunbeam.models.PaymentOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class PaymentOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long amount;
	
	private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
	
	private PaymentMethod paymentMethod;
	
	private String paymentLinkedId;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private Set<Orders> orders = new HashSet<>();
	
	
	
}
