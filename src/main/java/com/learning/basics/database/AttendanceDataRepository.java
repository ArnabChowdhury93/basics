package com.learning.basics.database;

import com.learning.basics.models.AttendanceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDataRepository extends JpaRepository<AttendanceData, Long> {
}
