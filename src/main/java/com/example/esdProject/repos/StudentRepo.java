package com.example.esdProject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.esdProject.entity.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
