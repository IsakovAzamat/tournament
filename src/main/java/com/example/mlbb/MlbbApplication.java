package com.example.mlbb;

import com.example.mlbb.entity.Scrim;
import com.example.mlbb.entity.Team;
import com.example.mlbb.enums.ScrimFormat;
import com.example.mlbb.enums.ScrimStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;


@Slf4j
@SpringBootApplication
public class MlbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlbbApplication.class, args);
	}

}
