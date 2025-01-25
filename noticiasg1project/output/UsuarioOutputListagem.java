package br.com.ifs.noticiasg1project.output;

import br.com.ifs.noticiasg1project.model.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioOutputListagem {

    private int id;
    private String nome;
    private String numTelefone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Maceio")
    private LocalDate dtNascimento;
    private boolean status;

    public static UsuarioOutputListagem fromModel(UsuarioModel model) {
        return UsuarioOutputListagem.builder().nome(model.getNome()).id(model.getId()).nome(model.getNome())
                .numTelefone(model.getNumTelefone()).email(model.getEmail()).dtNascimento(model.getDtNascimento())
                .status(model.isStatus()).build();
    }
}
