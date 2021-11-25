package com.crispandclear.teacher.vo;

import java.util.List;

import com.crispandclear.teacher.entity.Teacher;

import lombok.Data;

@Data
public class TeacherStudentVo {

	private Teacher teacher;
	private List<Student> student;

}
