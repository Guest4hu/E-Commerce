package br.com.bradescoSantanderDio.DesignPattern.controller;

import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.PedidoResponseDTO;
import br.com.bradescoSantanderDio.DesignPattern.service.facade.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Diz ao Spring que esta classe responde a requisições web (APIs REST)
@RequestMapping("/api/checkout") // Define o endereço base do endpoint
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutFacade;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> finalizarCompra(@RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO response = checkoutFacade.processarCheckout(request);

        return ResponseEntity.ok(response);
    }
}