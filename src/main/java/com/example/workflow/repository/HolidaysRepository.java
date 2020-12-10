package com.example.workflow.repository;

import com.example.workflow.domain.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {
    List<Holidays> findByDateofholiday(Date date);
}
