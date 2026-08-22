package br.com.bradescoSantanderDio.DesignPattern.controller;

import br.com.bradescoSantanderDio.DesignPattern.ai.AudioAssistService;
import br.com.bradescoSantanderDio.DesignPattern.dto.ChatRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.ChatResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/assistente")
public class AssistController {

    private final AudioAssistService audioAssistService;

    public AssistController(AudioAssistService audioAssistService) {
        this.audioAssistService = audioAssistService;
    }


    // Metodo para texto.
    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatResponseDTO> conversarPorTexto(@RequestBody ChatRequestDTO request) {

        if (request == null || request.mensagem() == null || request.mensagem().isBlank()) {
            return ResponseEntity.badRequest().body(new ChatResponseDTO("A mensagem não pode ser vazia."));
        }

        String resposta = audioAssistService.processarComando(request.mensagem());

        return ResponseEntity.status(HttpStatus.OK).body(new ChatResponseDTO(resposta));
    }

    // Metodo para audio
    @PostMapping(value = "/chat", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChatResponseDTO> conversarPorAudio(@RequestParam("audio") MultipartFile arquivoAudio) {

        if (arquivoAudio == null || arquivoAudio.isEmpty()) {
            return ResponseEntity.badRequest().body(new ChatResponseDTO("O arquivo de áudio não pode estar vazio."));
        }

        try {
            String respostaInteligente = audioAssistService.processarComandoDeVoz(arquivoAudio);
            return ResponseEntity.ok(new ChatResponseDTO(respostaInteligente));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ChatResponseDTO("Erro ao processar o áudio: " + e.getMessage()));
        }
    }
}