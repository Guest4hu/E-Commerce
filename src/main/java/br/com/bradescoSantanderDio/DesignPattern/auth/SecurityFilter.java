package br.com.bradescoSantanderDio.DesignPattern.auth;

import br.com.bradescoSantanderDio.DesignPattern.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Diz ao Spring que este é um componente que deve ser carregado na memória
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Tenta achar o token escondido no cabeçalho (Header) da requisição
        String token = this.recoverToken(request);

        // 2. Se o token existir, vamos validá-lo
        if (token != null) {
            String login = tokenService.validarToken(token); // Descriptografa e devolve o email/login

            // Se o login não for vazio (ou seja, o token é verdadeiro e não está expirado)
            if (!login.isEmpty()) {

                // Vai no banco de dados e busca quem é esse usuário
                UserDetails usuario = usuarioRepository.findByLogin(login);

                if (usuario != null) {
                    // Cria o "crachá" oficial do Spring Security para este usuário
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                    // Coloca o crachá no pescoço do usuário (Salva no contexto do Spring)
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // 3. Independentemente do que aconteceu, manda a requisição seguir em frente.
        // Se ele não tiver o "crachá" salvo ali em cima, o próprio Spring barra ele no próximo passo.
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extrair a palavra "Bearer " do Token e pegar só a parte criptografada
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}