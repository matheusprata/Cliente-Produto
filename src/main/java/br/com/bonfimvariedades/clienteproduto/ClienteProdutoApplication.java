package br.com.bonfimvariedades.clienteproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class ClienteProdutoApplication {
	public static void main(String[] args) {SpringApplication.run(ClienteProdutoApplication.class, args);
	}
}