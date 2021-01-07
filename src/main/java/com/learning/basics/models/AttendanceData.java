package com.learning.basics.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Lead_Info")
public class AttendanceData {
    private double hours;
    private int leadId;

}
