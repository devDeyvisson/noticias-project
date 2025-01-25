package br.com.ifs.noticiasg1project.controller;

import br.com.ifs.noticiasg1project.input.UsuarioInput;
import br.com.ifs.noticiasg1project.input.UsuarioInputAtualizacao;
import br.com.ifs.noticiasg1project.input.UsuarioInputDadosAutenticacao;
import br.com.ifs.noticiasg1project.output.UsuarioOutputListagem;
import br.com.ifs.noticiasg1project.service.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UsuarioInputDadosAutenticacao dadosUsuarioIn) {
        try{
            return userService.efetuarLogin(dadosUsuarioIn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody @Valid UsuarioInput dadosUsuarioIn){
        try {
            return userService.registrar(dadosUsuarioIn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioOutputListagem>> listarTodos() {
        try {
            return userService.listarTodos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<UsuarioOutputListagem>> listarPorID(@PathVariable int id) {
        try {
            return userService.listarPorID(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid UsuarioInputAtualizacao dadosAtualizacaoIn) {
        try {
            return userService.atualizar(dadosAtualizacaoIn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        try {
           return userService.remover(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable int id) {
        return userService.inativar(id);
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativar(@PathVariable int id) {
        return userService.reativar(id);
    }
}
