package br.com.ifs.noticiasg1project.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInputDefinirCategoria {

    @NotBlank(message = "O nome da categoria é origatório!")
    private String nome;
}
