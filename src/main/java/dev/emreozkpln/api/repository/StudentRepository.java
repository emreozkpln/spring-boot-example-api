package dev.emreozkpln.api.repository;

import dev.emreozkpln.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findAllByFirstnameContaining(String firstname);
    Student findStudentByStudentNo(String studentNo);
}
