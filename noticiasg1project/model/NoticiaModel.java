package br.com.ifs.noticiasg1project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "noticias", schema = "public")
public class NoticiaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_not")
    private int id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "endereco_img", nullable = false)
    private String enderecoImg;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dtPublicacao;

    // Muitas notícias estão relacionadas a 1 categoria
    @ManyToOne
    @JoinColumn(name = "id_cat", referencedColumnName = "id_cat", nullable = false)
    @JsonIgnore
    private CategoriaModel categoria;

}
