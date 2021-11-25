package com.crispandclear.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crispandclear.teacher.entity.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

	// Query Method Example.
	Teacher findByfirstName(String name);

	//named query example. 
	@Query("select t from Teacher t where t.experience = ?1" )
	List<Teacher> getByExperience(int experience);

	//native query Example.
	@Query(value = "SELECT * FROM TEACHER WHERE CITY = ?1", nativeQuery = true)
	List<Teacher> getByCity(String city);

}
