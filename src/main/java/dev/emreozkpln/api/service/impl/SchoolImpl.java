package dev.emreozkpln.api.service.impl;

import dev.emreozkpln.api.dto.SchoolDto;
import dev.emreozkpln.api.exception.AlreadyExistsException;
import dev.emreozkpln.api.exception.NotFoundException;
import dev.emreozkpln.api.model.School;
import dev.emreozkpln.api.repository.SchoolRepository;
import dev.emreozkpln.api.service.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final ModelMapper modelMapper;

    public SchoolImpl(SchoolRepository schoolRepository, ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SchoolDto> findAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream()
                .map(school->modelMapper.map(school,SchoolDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDto findById(Integer id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(()->new NotFoundException("School id not found"));
        return modelMapper.map(school,SchoolDto.class);
    }

    @Override
    public void addSchool(SchoolDto schoolDto) {
        var existingSchool = schoolRepository.findSchoolByName(schoolDto.getName());
        if(existingSchool != null){
            throw new AlreadyExistsException("School already have");
        }
        School school = modelMapper.map(schoolDto, School.class);
        School savedSchool = schoolRepository.save(school);
        modelMapper.map(savedSchool, SchoolDto.class);
    }

    @Override
    public SchoolDto updateSchool(SchoolDto schoolDto, Integer id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(()->new NotFoundException("School not found"));
        school.setName(schoolDto.getName());
        School updatedSchool = schoolRepository.save(school);
        return modelMapper.map(updatedSchool,SchoolDto.class);
    }

    @Override
    public void deleteSchool(Integer id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(()->new NotFoundException("School id not found"));
        schoolRepository.delete(school);
    }
}
