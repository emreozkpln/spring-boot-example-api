package dev.emreozkpln.api.controller;

import dev.emreozkpln.api.dto.StudentRequest;
import dev.emreozkpln.api.dto.StudentResponseDto;
import dev.emreozkpln.api.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAllStudents(){
        List<StudentResponseDto> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Integer id){
        StudentResponseDto studentResponse = studentService.findById(id);
        return ResponseEntity.ok(studentResponse);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<StudentResponseDto>> findByName(@PathVariable String name){
        List<StudentResponseDto> studentResponse = studentService.findByName(name);
        return ResponseEntity.ok(studentResponse);
    }
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest){
        studentService.addStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable Integer id){
        studentService.updateStudent(studentRequest,id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student updated succesfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted succesfully.");
    }
}
