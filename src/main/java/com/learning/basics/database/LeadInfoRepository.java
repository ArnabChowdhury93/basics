package com.learning.basics.database;

import com.learning.basics.models.LeadInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadInfoRepository extends JpaRepository<LeadInfo, Integer> {

}
