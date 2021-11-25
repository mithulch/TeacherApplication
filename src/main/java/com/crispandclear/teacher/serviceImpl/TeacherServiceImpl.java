package com.crispandclear.teacher.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crispandclear.teacher.entity.Teacher;
import com.crispandclear.teacher.exception.IdNotFoundException;
import com.crispandclear.teacher.exception.NameNotFoundException;
import com.crispandclear.teacher.exception.RecordNotFoundException;
import com.crispandclear.teacher.repository.TeacherRepo;
import com.crispandclear.teacher.service.TeacherService;
import com.crispandclear.teacher.vo.Student;
import com.crispandclear.teacher.vo.TeacherStudentVo;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepo teacherRepo;

	@Autowired
	private RestTemplate template;

	@Override
	public Teacher addTeacher(Teacher teacher) {
		return teacherRepo.save(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepo.findAll();
	}

	@Override
	public Optional<Teacher> getTeacherById(Long id) throws IdNotFoundException {

		Optional<Teacher> teacher = teacherRepo.findById(id);
		if (!teacher.isPresent()) {
			throw new IdNotFoundException("Teacher Record is not found for the given Id");
		}
		return teacher;
	}

	@Override
	public void deleteTeacherById(Long id) throws IdNotFoundException {
		Teacher teacher = teacherRepo.getById(id);
		if (teacher == null) {
			throw new IdNotFoundException("Teacher record is not found given Id");
		}
		teacherRepo.deleteById(id);
	}

	@Override
	public Teacher updateTeacher(Long id, Teacher teacher) throws IdNotFoundException {

		Optional<Teacher> teacherResp = teacherRepo.findById(id);
		if (!teacherResp.isPresent()) {
			throw new IdNotFoundException("Teacher with given Id is not found.");
		}
		teacher.setTeacherId(id);
		return teacherRepo.save(teacher);

	}

	@Override
	public Teacher getTeacherByName(String name) throws NameNotFoundException {

		Teacher teacher = teacherRepo.findByfirstName(name);
		if (teacher == null) {
			throw new NameNotFoundException("Teacher with Given Name is not found in Database.");
		}
		return teacher;
	}

	@Override
	public List<Teacher> getByExperience(int experience) throws RecordNotFoundException {

		List<Teacher> teacherList = teacherRepo.getByExperience(experience);
		if (teacherList.isEmpty()) {
			throw new RecordNotFoundException("No record found with the given experience.");
		}
		return teacherList;
	}

	@Override
	public List<Teacher> getTeacherByCity(String city) throws RecordNotFoundException {
		List<Teacher> teacherList = teacherRepo.getByCity(city);
		if (teacherList.isEmpty()) {
			throw new RecordNotFoundException("No records found for the given city.");
		}
		return teacherList;
	}

	@Override
	public TeacherStudentVo getTeacherWithStudents(Long id) {
		TeacherStudentVo vo = new TeacherStudentVo();
		Teacher teacher = teacherRepo.findById(id).get();
		// List<Student> listOfStudents = (List<Student>) template.getForObject(
		// "http://localhost:8082/student/getallstuforteacher/" +
		// teacher.getTeacherId(), Student[].class);

		Student[] listOfStudent = template.getForObject(
				"http://localhost:8082/student/getallstuforteacher/" + teacher.getTeacherId(), Student[].class);
		List<Student> stu = Arrays.asList(listOfStudent);

		vo.setTeacher(teacher);
		vo.setStudent(stu);

		return vo;
	}

}
