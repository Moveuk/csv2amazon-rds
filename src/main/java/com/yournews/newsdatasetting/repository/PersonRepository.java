package com.yournews.newsdatasetting.repository;

import com.yournews.newsdatasetting.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
