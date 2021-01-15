package com.learning.basics;

import com.learning.basics.database.AttendanceDataRepository;
import com.learning.basics.database.LeadInfoRepository;
import com.learning.basics.database.MyUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {MyUserRepository.class, AttendanceDataRepository.class
									, LeadInfoRepository.class})
public class BasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicsApplication.class, args);
	}

}
