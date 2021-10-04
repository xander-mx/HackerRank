package com.gnp.hackerrank.HackerRank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HackerRankApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerRankApplication.class, args);
	}

}
