package dev.emreozkpln.api.service.impl;

import dev.emreozkpln.api.dto.StudentRequest;
import dev.emreozkpln.api.dto.StudentResponseDto;
import dev.emreozkpln.api.dto.converter.StudentResponseConverter;
import dev.emreozkpln.api.exception.AlreadyExistsException;
import dev.emreozkpln.api.exception.NotFoundException;
import dev.emreozkpln.api.model.School;
import dev.emreozkpln.api.model.Student;
import dev.emreozkpln.api.repository.SchoolRepository;
import dev.emreozkpln.api.repository.StudentRepository;
import dev.emreozkpln.api.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SchoolRepository schoolRepository;

    private final StudentResponseConverter studentResponseConverter;

    public StudentImpl(StudentRepository studentRepository,
                       ModelMapper modelMapper,
                       StudentResponseConverter studentResponseConverter,
                       SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.studentResponseConverter = studentResponseConverter;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<StudentResponseDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto findById(Integer id) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(()->new NotFoundException("Student not found with the given ID."));
            StudentResponseDto studentResponseDto = studentResponseConverter.convert(student);
            return studentResponseDto;
        }catch (Exception e){
            log.debug("ID bulunamadı {}",id,e);
            throw new NotFoundException("Eklenmedi");
        }
    }

    @Override
    public List<StudentResponseDto> findByName(String name) {
        List<Student> students = studentRepository.findAllByFirstnameContaining(name);
        return students.stream()
                .map(studentResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public StudentRequest addStudent(StudentRequest studentRequest) {
        var existingStudent = studentRepository.findStudentByStudentNo(studentRequest.getStudentNo());
        if(existingStudent != null){
            throw new AlreadyExistsException("Student already have");
        }
        School school = schoolRepository.findById(studentRequest.getSchool_id())
                .orElseThrow(()->new NotFoundException("School not found"));

        Student student = modelMapper.map(studentRequest,Student.class);
        student.setSchool(school);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentRequest.class);
    }

    @Override
    public StudentRequest updateStudent(StudentRequest studentRequest,Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Student not found"));
        School school = schoolRepository.findById(studentRequest.getSchool_id())
                .orElseThrow(()->new NotFoundException("School not found"));
        student.setStudentNo(studentRequest.getStudentNo());
        student.setFirstname(studentRequest.getFirstname());
        student.setLastname(studentRequest.getLastname());
        student.setSchool(school);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent,StudentRequest.class);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Student not found with the given ID."));
        studentRepository.delete(student);
    }
}
