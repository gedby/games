package sk.gamestudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Main {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
