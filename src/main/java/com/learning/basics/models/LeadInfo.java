package com.learning.basics.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Lead_Info")
public class LeadInfo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name="first_name", nullable=false)
    private String first_name;

    @Column(name="last_name", nullable=false)
    private String last_name;

    @Column(name="mobile", nullable = false, length = 10,unique = true)
    private String mobile;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="location_type", nullable = false)
    private LocationType location_type;

    @Column(name="location_string", nullable = false)
    private String location_string;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "communication")
    private String communication = null;

}
