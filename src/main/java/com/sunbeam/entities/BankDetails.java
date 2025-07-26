package com.sunbeam.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {
	
	
	private String accountNumber;
	
	private String accountHolderName;
	
	private String ifscCode;
	
}
