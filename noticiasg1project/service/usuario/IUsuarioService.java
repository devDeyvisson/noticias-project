package br.com.ifs.noticiasg1project.service.usuario;

import br.com.ifs.noticiasg1project.input.UsuarioInput;
import br.com.ifs.noticiasg1project.input.UsuarioInputAtualizacao;
import br.com.ifs.noticiasg1project.input.UsuarioInputDadosAutenticacao;
import br.com.ifs.noticiasg1project.output.UsuarioOutputListagem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsuarioService {
    ResponseEntity<?> registrar(UsuarioInput dadosUsuarioIn) throws Exception;
    ResponseEntity<?> efetuarLogin(UsuarioInputDadosAutenticacao dadosUsuarioIn) throws Exception;
    ResponseEntity<List<UsuarioOutputListagem>> listarTodos() throws Exception;
    ResponseEntity<List<UsuarioOutputListagem>> listarPorID(int id) throws Exception;
    ResponseEntity<Void> atualizar(UsuarioInputAtualizacao dadosAtualizacaoIn) throws Exception;
    ResponseEntity<Void> remover(int id) throws Exception;
    ResponseEntity<Void> inativar(int id);
    ResponseEntity<Void> reativar(int id);
}
