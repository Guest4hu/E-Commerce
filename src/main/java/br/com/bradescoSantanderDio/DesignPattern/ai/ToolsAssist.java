package br.com.bradescoSantanderDio.DesignPattern.ai;

import br.com.bradescoSantanderDio.DesignPattern.enums.TipoFrete;
import br.com.bradescoSantanderDio.DesignPattern.service.factory.FreteFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ToolsAssist {

    private final FreteFactory freteFactory;

    public ToolsAssist(FreteFactory freteFactory) {
        this.freteFactory = freteFactory;
    }

    @Tool(description = "Calcula o valor e o prazo do frete para o cliente baseado em um CEP de destino. O tipo de frete deve ser 'PAC' ou 'SEDEX'.")
    public String calcularFrete(
            @ToolParam(description = "CEP de destino") String cep,
            @ToolParam(description = "Tipo de frete: PAC ou SEDEX") TipoFrete tipoFrete) {

        try {
            BigDecimal resultadoFrete = freteFactory.obterServico(tipoFrete).calcular(cep);

            return "Cálculo bem sucedido. O valor final do frete é de R$ " + resultadoFrete;

        } catch (Exception e) {
            return "Aviso interno: Houve um erro ao calcular o frete (" + e.getMessage() + "). " +
                    "Peça desculpas ao cliente e diga que o sistema de entregas está temporariamente indisponível.";
        }
    }
}