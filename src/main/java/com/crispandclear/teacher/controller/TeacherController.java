package com.crispandclear.teacher.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crispandclear.teacher.dto.TeacherDto;
import com.crispandclear.teacher.entity.Teacher;
import com.crispandclear.teacher.exception.IdNotFoundException;
import com.crispandclear.teacher.exception.NameNotFoundException;
import com.crispandclear.teacher.exception.RecordNotFoundException;
import com.crispandclear.teacher.service.TeacherService;
import com.crispandclear.teacher.vo.TeacherStudentVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/teacher")
@Slf4j
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ModelMapper mapper;

	@PostMapping(value = "/add")
	public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
		log.info("adding teacher" + teacher);
		Teacher teacherResp = teacherService.addTeacher(teacher);
		return new ResponseEntity<Teacher>(teacherResp, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Teacher>> getAllTeachers() throws RecordNotFoundException {
		log.info("showing all teachers information");
		List<Teacher> listOfTeachers = teacherService.getAllTeachers();
		if (listOfTeachers == null) {
			throw new RecordNotFoundException("No Record Found");
		}
		return new ResponseEntity<List<Teacher>>(listOfTeachers, HttpStatus.OK);
	}

	@GetMapping(value = "/getbyid/{id}")
	public TeacherDto getTeacherById(@PathVariable("id") Long id) throws IdNotFoundException {
		log.info("Recieved request to view information for teacher id" + id);
		Optional<Teacher> teacher = teacherService.getTeacherById(id);
		if (teacher == null) {
			throw new IdNotFoundException("Given Id is not found");
		}
		Teacher teach = teacher.get();
		TeacherDto teacherDto = mapper.map(teach, TeacherDto.class);
		return teacherDto;

	}

	@DeleteMapping(value = "/deletebyid/{id}")
	public ResponseEntity<Void> deleteTeacherById(@PathVariable("id") Long id) throws IdNotFoundException {
		log.info("Deleting Teacher record having Id :" + id);
		Optional<Teacher> teacher = teacherService.getTeacherById(id);
		if (!teacher.isPresent()) {
			throw new IdNotFoundException("Input Id is not found in the database");
		}
		teacherService.deleteTeacherById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping(value = "/updatebyid/{id}")
	public ResponseEntity<Teacher> updateTeacherById(@PathVariable("id") Long id, @RequestBody Teacher teacher)
			throws IdNotFoundException {
		log.info("Updating Teacher having id" + id + "with new values " + teacher);
		Teacher teacherResp = teacherService.updateTeacher(id, teacher);
		return new ResponseEntity<Teacher>(teacherResp, HttpStatus.OK);
	}

	@GetMapping(value = "/byname/{name}")
	public TeacherDto getTeacherByName(@PathVariable String name) throws NameNotFoundException {
		log.info("get teacher info having name :" + name);
		Teacher teacher = teacherService.getTeacherByName(name);
		if (teacher.getFirstName() == null) {
			throw new NameNotFoundException("Teacher with the input name is not found.");
		}
		TeacherDto teacherDto = mapper.map(teacher, TeacherDto.class);
		return teacherDto;

	}

	@GetMapping(value = "/byexperience/{experience}")
	public ResponseEntity<List<Teacher>> getTeacherByExperience(@PathVariable("experience") int experience)
			throws RecordNotFoundException {
		log.info("show the list of teachers having experience :" + experience);
		List<Teacher> teacher = teacherService.getByExperience(experience);
		return new ResponseEntity<List<Teacher>>(teacher, HttpStatus.OK);

	}

	@GetMapping(value = "/bycity/{city}")
	public ResponseEntity<List<Teacher>> getTeacherByCity(@PathVariable("city") String city)
			throws RecordNotFoundException {
		log.info("Show the list of teachers having city as :" + city);
		List<Teacher> teacher = teacherService.getTeacherByCity(city);
		return new ResponseEntity<List<Teacher>>(teacher, HttpStatus.OK);
	}

	@GetMapping(value = "/teacherwithstudents/{id}")
	public TeacherStudentVo getTeacherWithStudents(@PathVariable("id") Long id) {
		log.info("Fetch info of teacher along with all its students for teacher id :" + id);
		return teacherService.getTeacherWithStudents(id);
	}

}
