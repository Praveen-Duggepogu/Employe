package com.employe.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employe")
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String address;
	private String email;
	private String name;
	private long phonenumber;
	private float salary;
	//@OneToOne(targetEntity =TimeSheet.class,cascade = CascadeType.ALL )
   //private TimeSheet timesheet;
}
