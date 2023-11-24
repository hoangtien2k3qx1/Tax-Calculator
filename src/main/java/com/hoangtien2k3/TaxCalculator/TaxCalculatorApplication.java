package com.hoangtien2k3.TaxCalculator;

import com.hoangtien2k3.TaxCalculator.secutity.properties.SecurityConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityConfigProperties.class)
public class TaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxCalculatorApplication.class, args);
	}

}
