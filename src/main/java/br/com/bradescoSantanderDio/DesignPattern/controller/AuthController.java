package br.com.bradescoSantanderDio.DesignPattern.controller;

import br.com.bradescoSantanderDio.DesignPattern.auth.TokenService;
import br.com.bradescoSantanderDio.DesignPattern.dto.LoginRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.LoginResponseDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.RegisterRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoUsuario;
import br.com.bradescoSantanderDio.DesignPattern.model.Usuario;
import br.com.bradescoSantanderDio.DesignPattern.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {

        // Empacota o email e senha no formato que o Spring Security entende
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        // O Spring Security vai até o banco, pega a senha criptografada, compara com a senha digitada e autentica
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        // Se a senha bater, nós geramos o Token JWT
        // (Fazemos um cast seguro para Usuario, pois sabemos que a nossa entidade implementa UserDetails)
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        // Devolvemos o Token para o cliente!
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/registrar")
    public ResponseEntity<LoginResponseDTO> registrar(@RequestBody RegisterRequestDTO data) {

        // Verifica se o login (e-mail) já existe no banco de dados
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(409)).body(new LoginResponseDTO("Login já existe!"));
        }


        // Cria um novo usuário, com a senha criptografada, e o tipo cliente padrão
        this.repository.save(
                new Usuario(data.login(),
                passwordEncoder.encode(data.senha()),
                TipoUsuario.USER));

        return ResponseEntity.ok().build();
    }
}