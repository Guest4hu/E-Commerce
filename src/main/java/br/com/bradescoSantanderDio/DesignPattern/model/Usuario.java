package br.com.bradescoSantanderDio.DesignPattern.model;

import br.com.bradescoSantanderDio.DesignPattern.enums.TipoUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tbl_usuarios") // Nome da tabela no banco
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,unique = true, name = "usuario_id")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String login; // Pode ser o e-mail do usuário

    @Column(nullable = false)
    private String senha; // A senha será gravada criptografada


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario role;

    // Construtor vazio obrigatório do JPA
    public Usuario() {
    }

    public Usuario(String login, String senha, TipoUsuario role) {
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    // =======================================================================
    // MÉTODOS OBRIGATÓRIOS DA INTERFACE USERDETAILS (SPRING SECURITY)
    // =======================================================================

    // Aqui dizemos qual o "cargo" do usuário. Por enquanto, todo mundo é USER.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    // Daqui para baixo, são validações de conta. Vamos deixar todas retornando TRUE (Conta ativa e liberada), por conta do tipo de validação, cada uma tem sua especifica, precisa ser herdada do User Details.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}