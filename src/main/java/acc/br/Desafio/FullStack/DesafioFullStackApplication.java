package acc.br.Desafio.FullStack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioFullStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioFullStackApplication.class, args);

	/*	ApiViaCep cep = new ApiViaCep();
		try {
			EnderecoFonecedor endereco = cep.consultaCEP("58360000");
			System.out.println(endereco);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
	}




}
