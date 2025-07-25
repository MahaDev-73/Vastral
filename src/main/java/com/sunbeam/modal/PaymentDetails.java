package com.sunbeam.modal;

import com.sunbeam.domain.PaymentStatus;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PaymentDetails {

	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatusString;
	private String razorpayPaymentIdString;
	private PaymentStatus status;
	
}
 