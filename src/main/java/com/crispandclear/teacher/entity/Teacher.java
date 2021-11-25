package com.crispandclear.teacher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teacherId;
	
	@Column(name = "firstname", length = 10, nullable = false)
	private String firstName;
	
	@Column(name = "lastname", length = 10, nullable = false)
	private String lastName;
	
	@Column(name = "experience", length = 2, nullable = false)
	private int experience;
	
	@Column(name = "salary")
	private long salary;
	
	@Column(name = "city")
	private String city;

}
