package com.reactProject.exam.backend.repository;

import com.reactProject.exam.backend.Entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, String> {
}