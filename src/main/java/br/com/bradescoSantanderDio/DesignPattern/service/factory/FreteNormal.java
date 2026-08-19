package br.com.bradescoSantanderDio.DesignPattern.service.factory;

import br.com.bradescoSantanderDio.DesignPattern.client.ViaCepClient;
import br.com.bradescoSantanderDio.DesignPattern.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("PAC")
public class FreteNormal implements ServicoFrete {

    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public BigDecimal calcular(String cep) {
        System.out.println("[FRETE NORMAL] Buscando CEP " + cep + " no ViaCEP...");
        EnderecoDTO endereco = viaCepClient.buscarEnderecoPorCep(cep);

        System.out.println("[FRETE NORMAL] Entrega para: " + endereco.getLocalidade() + " - " + endereco.getUf());

        // Regra de negócio: Entregas normais para SP custam R$ 10. Para outros estados, R$ 20.
        if (endereco.getUf().equalsIgnoreCase("SP")) {
            return new BigDecimal("10.00");
        }
        return new BigDecimal("20.00");
    }
}