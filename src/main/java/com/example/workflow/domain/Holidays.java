package com.example.workflow.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="holidays")
public class Holidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(value=TemporalType.DATE)
    private Date dateofholiday;

    public Long getId() {
        return id;
    }

    public Date getDateofholiday() {
        return dateofholiday;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateofholiday(Date dateofholiday) {
        this.dateofholiday = dateofholiday;
    }
}
