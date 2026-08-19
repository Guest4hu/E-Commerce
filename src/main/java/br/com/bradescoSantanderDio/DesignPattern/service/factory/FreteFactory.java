package br.com.bradescoSantanderDio.DesignPattern.service.factory;

import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FreteFactory {

    // O Spring injeta as calculadoras (FreteNormal e FreteExpresso) automaticamente aqui
    @Autowired
    private Map<String, ServicoFrete> servicosFrete;

    public ServicoFrete obterServico(TipoFrete tipoFrete) {
        ServicoFrete servico = servicosFrete.get(tipoFrete.name());


        if (servico == null) {
            throw new IllegalArgumentException("Tipo de frete não suportado!");
        }

        return servico;
    }
}