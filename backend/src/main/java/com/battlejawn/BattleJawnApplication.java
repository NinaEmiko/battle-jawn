package com.battlejawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BattleJawnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleJawnApplication.class, args);
	}

}
