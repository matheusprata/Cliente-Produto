package br.com.bonfimvariedades.ClienteFiado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class ClienteFiadoApplication {

	@GetMapping
	public String getHometeste() {
		return "API CLIENTE-FIADO";
	}
	public static void main(String[] args) {
		SpringApplication.run(ClienteFiadoApplication.class, args);
	}

}
