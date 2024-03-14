package dev.emreozkpln.api.controller;

import dev.emreozkpln.api.dto.SchoolDto;
import dev.emreozkpln.api.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> findAllSchool(){
        List<SchoolDto> schools = schoolService.findAllSchools();
        return ResponseEntity.ok(schools);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<SchoolDto> findById(@PathVariable Integer id){
        SchoolDto school = schoolService.findById(id);
        return ResponseEntity.ok(school);
    }
    @PostMapping
    public ResponseEntity<String> addSchool(@RequestBody SchoolDto schoolDto){
        schoolService.addSchool(schoolDto);
        return ResponseEntity.ok("School added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchool(@RequestBody SchoolDto schoolDto,@PathVariable Integer id){
        schoolService.updateSchool(schoolDto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body("School updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable Integer id){
        schoolService.deleteSchool(id);
        return ResponseEntity.ok("School deleted successfully");
    }
}
