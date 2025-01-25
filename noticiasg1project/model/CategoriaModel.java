package br.com.ifs.noticiasg1project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categorias", schema = "public")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat", nullable = false)
    private int id;

    @Column(name = "nome_cat", nullable = false)
    private String nome;

    @Column(name = "endereco_url", nullable = false)
    private String enderecoUrl;

    // 1 categoria está relacionada a muitas notícias
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<NoticiaModel> noticias;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private Set<UsuarioModel> usuarios = new HashSet<>();

}
