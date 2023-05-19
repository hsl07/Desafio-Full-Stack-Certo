package acc.br.Desafio.FullStack;

import acc.br.Desafio.FullStack.consume.ApiViaCep;
import acc.br.Desafio.FullStack.entity.Endereco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DesafioFullStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioFullStackApplication.class, args);

	/*	ApiViaCep cep = new ApiViaCep();
		try {
			Endereco endereco = cep.consultaCEP("58360000");
			System.out.println(endereco);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
	}




}
