package com.example.SpringPSS.repositories;

import com.example.SpringPSS.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    Event findById(int id);
    Event findByTitle(String title);
}
