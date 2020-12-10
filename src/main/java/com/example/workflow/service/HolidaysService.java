package com.example.workflow.service;
import com.example.workflow.domain.Holidays;

import java.util.Date;
import java.util.List;

public interface HolidaysService {
    public List<Holidays> findAll();
    public Holidays findById(Long id);
    public Holidays save(Holidays holiday);
    public void delete(Long id);
    public List<Holidays> findByDateofholiday(Date date);
}
