package com.employe.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	private String Email; 
	
	@Column(name = "first_name", nullable = false, length = 20)
	private String FirstName;
	
	@Column(name = "last_name", nullable = false, length = 20)
	private String LastName;
	
	@Column(nullable = false, length = 64)	
	private String Password;
	
	
	

	

}
