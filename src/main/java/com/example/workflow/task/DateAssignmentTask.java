package com.example.workflow.task;

import com.example.workflow.domain.Holidays;
import com.example.workflow.service.HolidaysService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Date;
import java.util.List;

@Component
public class DateAssignmentTask implements TaskListener {
    private static final Logger log = LoggerFactory.getLogger(DateAssignmentTask.class);
    private HolidaysService service;
    public static final int DURATION = 5;

    public DateAssignmentTask(@Qualifier("holidaysService") HolidaysService service) {
        this.service = service;
    }

    public void notify(DelegateTask delegateTask) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime date1 = null;
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.of(18, 0);
        if (currentDate.getHour() < 9 || currentDate.getHour() > 18) {
           date1 = LocalDateTime.of(localDate, time);
        } else {
            date1 = currentDate;
        }
        Date date = null;
        List<Holidays> dates = null;
        int count = 0;
        while (count < DURATION) {
            date1 = date1.plusDays(1);
            count++;
            date = Date.from(date1.atZone(ZoneId.of("Europe/Moscow"))
                    .toInstant());
            try {
                dates = service.findByDateofholiday(date);
                if (date1.getDayOfWeek() == DayOfWeek.SUNDAY || date1.getDayOfWeek() == DayOfWeek.SATURDAY || dates.size() > 0) {
                    count--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        delegateTask.setDueDate(date);
        }

}

