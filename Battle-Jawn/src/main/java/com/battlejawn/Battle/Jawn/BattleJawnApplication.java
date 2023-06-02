package com.battlejawn.Battle.Jawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BattleJawnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleJawnApplication.class, args);
	}

}
