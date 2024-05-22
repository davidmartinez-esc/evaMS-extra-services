package evaMS.ingresoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IngresoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngresoServiceApplication.class, args);
	}

}
