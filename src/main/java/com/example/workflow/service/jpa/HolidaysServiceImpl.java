package com.example.workflow.service.jpa;

import com.example.workflow.domain.Holidays;
import com.example.workflow.repository.HolidaysRepository;
import com.example.workflow.service.HolidaysService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("holidaysService")
@Repository
@Transactional
public class HolidaysServiceImpl implements HolidaysService {
    @Autowired
    private HolidaysRepository holidaysRepository;

    @Transactional(readOnly=true)
    public List<Holidays> findAll() {
        return  Lists.newArrayList(holidaysRepository.findAll());
    }

    @Transactional(readOnly=true)
    public Holidays findById(Long id) {
        return holidaysRepository.findById(id).get();
    }

    public Holidays save(Holidays holiday) {
        return holidaysRepository.save(holiday);
    }

    public void delete(Long id) {
        holidaysRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public List<Holidays> findByDateofholiday(Date date) {
        return Lists.newArrayList(holidaysRepository.findByDateofholiday(date));
    }
}
