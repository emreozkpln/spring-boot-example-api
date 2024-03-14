package dev.emreozkpln.api.service;

import dev.emreozkpln.api.dto.StudentRequest;
import dev.emreozkpln.api.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    List<StudentResponseDto> findAllStudents();
    StudentResponseDto findById(Integer id);
    List<StudentResponseDto> findByName(String name);
    StudentRequest addStudent(StudentRequest StudentRequest);

    StudentRequest updateStudent(StudentRequest studentRequest,Integer id);

    void deleteStudent(Integer id);
}
