package com.crispandclear.teacher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crispandclear.teacher.entity.Teacher;
import com.crispandclear.teacher.exception.IdNotFoundException;
import com.crispandclear.teacher.exception.NameNotFoundException;
import com.crispandclear.teacher.exception.RecordNotFoundException;
import com.crispandclear.teacher.vo.TeacherStudentVo;

@Service
public interface TeacherService {

	Teacher addTeacher(Teacher teacher);

	List<Teacher> getAllTeachers();

	Optional<Teacher> getTeacherById(Long id) throws IdNotFoundException;

	void deleteTeacherById(Long id) throws IdNotFoundException;

	Teacher updateTeacher(Long id, Teacher teacher) throws IdNotFoundException;

	Teacher getTeacherByName(String name) throws NameNotFoundException;

	List<Teacher> getByExperience(int experience) throws RecordNotFoundException;

	List<Teacher> getTeacherByCity(String city) throws RecordNotFoundException;

	TeacherStudentVo getTeacherWithStudents(Long id);

}
