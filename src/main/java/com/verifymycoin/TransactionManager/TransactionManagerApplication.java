package com.verifymycoin.TransactionManager;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TransactionManagerApplication {

	@Value("${foo}")
	private String configExample;

	@RequestMapping("/")
	public String home() {
		return "Hello " + configExample;
	}

	public static void main(String[] args) {
		SpringApplication.run(TransactionManagerApplication.class, args);
	}

}
