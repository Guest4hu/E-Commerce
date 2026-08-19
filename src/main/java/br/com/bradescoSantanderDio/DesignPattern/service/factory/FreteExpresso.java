package br.com.bradescoSantanderDio.DesignPattern.service.factory;
import br.com.bradescoSantanderDio.DesignPattern.client.ViaCepClient;
import br.com.bradescoSantanderDio.DesignPattern.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("SEDEX")
public class FreteExpresso implements ServicoFrete {

    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public BigDecimal calcular(String cep) {
        System.out.println("[FRETE EXPRESSO] Buscando CEP " + cep + " no ViaCEP...");
        EnderecoDTO endereco = viaCepClient.buscarEnderecoPorCep(cep);

        System.out.println("[FRETE EXPRESSO] Entrega para: " + endereco.getLocalidade() + " - " + endereco.getUf());

        // Regra de negócio: Entregas expressas para SP custam R$ 30. Para outros estados, R$ 50.
        if (endereco.getUf().equalsIgnoreCase("SP")) {
            return new BigDecimal("30.00");
        }
        return new BigDecimal("50.00");
    }
}