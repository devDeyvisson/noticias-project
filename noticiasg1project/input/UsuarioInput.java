package br.com.ifs.noticiasg1project.input;

import br.com.ifs.noticiasg1project.model.role.RoleUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioInput {

    @NotBlank(message = "O nome de usuário é obrigatório!")
    private String nome;
    @NotBlank(message = "O número de telefone é obrigatório!")
    private String numTelefone;
    @NotBlank(message = "O endereço de email é obrigatório!")
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Maceio")
    private LocalDate dtNascimento;
    @NotBlank(message = "O login é obrigatório!")
    private String login;
    @NotBlank(message = "A senha é obrigatória!")
    private String senha;
    private RoleUsuario role;
    private boolean status;

//    public UsuarioModel toModel() {
//        return UsuarioModel.builder().nome(nome).numTelefone(numTelefone)
//                                .email(email).dtNascimento(dtNascimento).login(login).senha(senha).status(true).build();
//    }

}
