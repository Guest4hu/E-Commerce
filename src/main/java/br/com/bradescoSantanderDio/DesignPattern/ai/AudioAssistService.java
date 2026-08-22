package br.com.bradescoSantanderDio.DesignPattern.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AudioAssistService {

    private final ChatClient chatClient;

    public AudioAssistService(ChatClient.Builder chatClientBuilder, ToolsAssist toolsAssist) {
        this.chatClient = chatClientBuilder
                .defaultSystem("Você é um assistente virtual inteligente e muito educado de um e-commerce de tecnologia. " +
                        "Sua missão é ajudar os clientes com o cálculo de fretes. " +
                        "Use sempre as ferramentas disponíveis para obter valores reais. " +
                        "Seja amigável, direto ao ponto e responda sempre em Português do Brasil.")
                .defaultTools(toolsAssist)
                .build();
    }

    public String processarComando(String mensagemUsuario) {

        String respostaGemini = chatClient.prompt()
                .user(mensagemUsuario)
                .call()
                .content();

        return respostaGemini;
    }


    public String processarComandoDeVoz(MultipartFile arquivoDeAudio) {

        Resource resource = arquivoDeAudio.getResource();
        String contentType = arquivoDeAudio.getContentType();
        if (contentType == null) {
            contentType = "audio/mpeg";
        }

        String finalContentType = contentType;
        return chatClient.prompt()
                .user(u -> u
                        // Instrução de contexto misturada com o áudio
                        .text("Por favor, ouça este áudio. Se o usuário estiver solicitando o cálculo de um frete, " +
                                "extraia as informações e use a ferramenta de frete para responder.")
                        .media(MimeTypeUtils.parseMimeType(finalContentType), resource)
                )
                // O .call() vai invocar a classe ToolsAssist automaticamente
                .call()
                .content();
    }


}