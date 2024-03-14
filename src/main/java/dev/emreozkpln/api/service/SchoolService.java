package dev.emreozkpln.api.service;

import dev.emreozkpln.api.dto.SchoolDto;

import java.util.List;

public interface SchoolService {
    List<SchoolDto> findAllSchools();
    SchoolDto findById(Integer id);
    void addSchool(SchoolDto schoolDto);
    SchoolDto updateSchool(SchoolDto schoolDto, Integer id);
    void deleteSchool(Integer id);
}
