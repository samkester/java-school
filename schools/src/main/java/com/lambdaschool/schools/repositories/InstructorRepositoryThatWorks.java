package com.lambdaschool.schools.repositories;

import com.lambdaschool.schools.models.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepositoryThatWorks extends CrudRepository<Instructor, Long> {

}
