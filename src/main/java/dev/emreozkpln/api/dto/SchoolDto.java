package dev.emreozkpln.api.dto;

import dev.emreozkpln.api.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class SchoolDto {

    private Integer id;
    private String name;
    private List<Student> students;
}
