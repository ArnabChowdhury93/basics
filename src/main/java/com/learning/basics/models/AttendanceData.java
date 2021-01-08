package com.learning.basics.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Attendance_Data")
public class AttendanceData {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name="hours")
    private double hours;

    @Column(name="lead_id")
    private int leadId;

}
