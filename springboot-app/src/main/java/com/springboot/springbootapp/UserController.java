package com.springboot.springbootapp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping(path = "/{name}")
	public String getName(@PathVariable String name) {
		
		LocalTime jetzt = LocalTime.now();
		LocalTime mitternacht=LocalTime.of(00, 00);
		LocalTime morgens=LocalTime.of(06, 00);
		LocalTime mittag=LocalTime.of(13, 00);
		LocalTime abbends=LocalTime.of(19, 00);
	
		String sql="insert into LOGIN(NAME,LOGINDATE) values ('"+name+"',CURRENT_TIMESTAMP)";
		
		jdbcTemplate.execute(sql);
		
		if(jetzt.isAfter(mitternacht)&jetzt.isBefore(mittag)) {
			return "Guten Morgen "+name+"!"; 
			
		}else if(jetzt.isAfter(mittag)&jetzt.isBefore(abbends)) {
			return "Guten Tag "+name+"!"; 
			
		}else {
			return "Guten Abend "+name+"!";  
		}
		
	}
	
}
