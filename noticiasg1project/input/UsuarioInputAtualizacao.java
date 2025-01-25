package br.com.ifs.noticiasg1project.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioInputAtualizacao {

    @NotNull
    private int id;
    private String nome;
    private String numTelefone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Maceio")
    private LocalDate dtNascimento;

}
