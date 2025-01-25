package br.com.ifs.noticiasg1project.model;

import br.com.ifs.noticiasg1project.input.UsuarioInputAtualizacao;
import br.com.ifs.noticiasg1project.model.role.RoleUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", schema = "public")
public class UsuarioModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usu")
    private int id;

    @Column(name = "nome_usu", nullable = false)
    private String nome;

    @Column(name = "num_telefone", nullable = false)
    private String numTelefone;

    @Column(name = "email_usu", nullable = false)
    private String email;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "login_usu", nullable = false)
    private String login;

    @Column(name = "senha_usu", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_usu", nullable = false)
    private RoleUsuario role;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToMany
    @JoinTable(
            name = "categorias_usuarios",
            joinColumns = @JoinColumn(name = "id_usu"),
            inverseJoinColumns = @JoinColumn(name = "id_cat")
    )
    private Set<CategoriaModel> categorias = new HashSet<>();

    public UsuarioModel(String nome, String numTelefone, String email, LocalDate dtNascimento, String login, String senha, RoleUsuario role) {
        this.nome = nome;
        this.numTelefone = numTelefone;
        this.email = email;
        this.dtNascimento = dtNascimento;
        this.login = login;
        this.senha = senha;
        this.role = role;
        this.status = true;
    }

    public void atualizarInformacoes(@Valid UsuarioInputAtualizacao usuarioAtualizacao) {
        if (usuarioAtualizacao.getNome() != null) {
            this.nome = usuarioAtualizacao.getNome();
        }
        if (usuarioAtualizacao.getNumTelefone() != null) {
            this.numTelefone = usuarioAtualizacao.getNumTelefone();
        }
        if (usuarioAtualizacao.getEmail() != null) {
            this.email = usuarioAtualizacao.getEmail();
        }
        if (usuarioAtualizacao.getDtNascimento() != null) {
            this.dtNascimento = usuarioAtualizacao.getDtNascimento();
        }
    }

    public void ativar() {
        this.status = true;
    }

    public void desativar() {
        this.status = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RoleUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

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

