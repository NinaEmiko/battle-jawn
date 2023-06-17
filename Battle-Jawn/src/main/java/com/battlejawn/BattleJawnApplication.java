package com.battlejawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.battlejawn.repository")
// (exclude={DataSourceAutoConfiguration.class})
public class BattleJawnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleJawnApplication.class, args);
	}

}
