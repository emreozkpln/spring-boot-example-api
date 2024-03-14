package dev.emreozkpln.api.repository;

import dev.emreozkpln.api.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {

    School findSchoolByName(String name);
}
