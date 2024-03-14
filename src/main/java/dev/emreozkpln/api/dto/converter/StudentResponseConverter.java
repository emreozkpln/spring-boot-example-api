package dev.emreozkpln.api.dto.converter;

import dev.emreozkpln.api.dto.StudentResponseDto;
import dev.emreozkpln.api.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseConverter {
    public StudentResponseDto convert(Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .schoolName(student.getSchool().getName())
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .build();
    }
}
