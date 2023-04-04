package com.sterlite.bankapplication.controller;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	
	private int accountNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String ifsc;
	private Double amount;
}
