package acc.br.Desafio.FullStack.consume;

import acc.br.Desafio.FullStack.dto.EnderecoDTO;
import acc.br.Desafio.FullStack.entity.EnderecoFonecedor;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ApiViaCep {
   // private static Logger logger = LoggerFactory.getLogger(ApiViaCep.class);
    public EnderecoDTO consultaCEP (String cep) throws IOException {

        EnderecoDTO endereco = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");

        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
            CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if(entity !=null){

                String result = EntityUtils.toString(entity);
               Gson gson = new Gson();

               endereco = gson.fromJson(result, EnderecoDTO.class);

            }
            EnderecoFonecedor enderecoFonecedor1 = new EnderecoFonecedor(endereco);
            return endereco;
        }

    }


}
