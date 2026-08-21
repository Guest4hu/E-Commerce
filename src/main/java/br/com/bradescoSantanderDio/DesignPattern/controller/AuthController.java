package br.com.bradescoSantanderDio.DesignPattern.controller;

import br.com.bradescoSantanderDio.DesignPattern.auth.TokenService;
import br.com.bradescoSantanderDio.DesignPattern.dto.LoginRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.LoginResponseDTO;
import br.com.bradescoSantanderDio.DesignPattern.dto.RegisterRequestDTO;
import br.com.bradescoSantanderDio.DesignPattern.enums.TipoUsuario;
import br.com.bradescoSantanderDio.DesignPattern.model.Usuario;
import br.com.bradescoSantanderDio.DesignPattern.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Gerenciador de login do próprio Spring Security (que exportamos lá na SecurityConfig)
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    // Encriptador de senha (que exportamos lá na SecurityConfig)
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================================
    // 1. ENDPOINT DE LOGIN
    // ==========================================
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

    // ==========================================
    // 2. ENDPOINT DE REGISTRO
    // ==========================================
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequestDTO data) {

        // Verifica se o login (e-mail) já existe no banco de dados
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Já existe um usuário com este e-mail.");
        }

        // Criptografa a senha antes de salvar no banco!
        String senhaCriptografada = passwordEncoder.encode(data.senha());

        // Cria um novo usuário com a senha já encriptada e criar por padrão usuario como user
        Usuario novoUsuario = new Usuario(data.login(), senhaCriptografada, TipoUsuario.USER);

        // Salva no banco de dados H2
        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}