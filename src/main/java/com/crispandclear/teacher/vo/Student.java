package com.crispandclear.teacher.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	
	private long id;
	private String name;
	private int standard;
	private String city;
	private int teacherId;
	
}
