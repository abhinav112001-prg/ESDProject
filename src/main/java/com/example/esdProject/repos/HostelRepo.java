package com.example.esdProject.repos;

import com.example.esdProject.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface HostelRepo extends JpaRepository<Hostel, Integer> {
    @Modifying
    @Query(value = "update hostel set student_id = null where student_id = :id", nativeQuery = true)
    int clearStudentByStudentId(@Param("id") Integer id);

    @Modifying
    @Query(value = "update hostel set student_id = :newId where student_id = :currentId", nativeQuery = true)
    int setStudentIdWhereStudentId(@Param("currentId") Integer currentId, @Param("newId") Integer newId);

    @Modifying
    @Query(value = "update hostel set student_id = :newId where student_id is null", nativeQuery = true)
    int setStudentIdWhereNull(@Param("newId") Integer newId);
}
