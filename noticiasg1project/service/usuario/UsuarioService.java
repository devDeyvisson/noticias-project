package br.com.ifs.noticiasg1project.service.usuario;

import br.com.ifs.noticiasg1project.infra.security.DadosTokenJWT;
import br.com.ifs.noticiasg1project.infra.security.TokenService;
import br.com.ifs.noticiasg1project.input.UsuarioInput;
import br.com.ifs.noticiasg1project.input.UsuarioInputAtualizacao;
import br.com.ifs.noticiasg1project.input.UsuarioInputDadosAutenticacao;
import br.com.ifs.noticiasg1project.model.UsuarioModel;
import br.com.ifs.noticiasg1project.output.UsuarioOutputListagem;
import br.com.ifs.noticiasg1project.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<?> efetuarLogin(UsuarioInputDadosAutenticacao dadosUsuarioIn) {
        var token = new UsernamePasswordAuthenticationToken(dadosUsuarioIn.getLogin(), dadosUsuarioIn.getSenha());
        //System.out.println("Tentando autenticar usuário: " + dadosUsuario.getLogin());
        var autenticacao = manager.authenticate(token);
        //System.out.println("Usuário autenticado: " + autenticacao.getName());
        var tokenJWT = tokenService.generateToken((UsuarioModel) autenticacao.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @Override
    public ResponseEntity<?> registrar(UsuarioInput dadosUsuarioIn) throws Exception {
        if (this.userRepository.findByLogin(dadosUsuarioIn.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dadosUsuarioIn.getSenha());
        //Criptografa a senha do usuário!
        //System.out.println("Registrando usuário " + dadosUsuarioIn.getLogin() + " com senha criptografada: " + encryptedPassword);
        UsuarioModel usuarioModel = new UsuarioModel(dadosUsuarioIn.getNome(), dadosUsuarioIn.getNumTelefone(),
                dadosUsuarioIn.getEmail(), dadosUsuarioIn.getDtNascimento(), dadosUsuarioIn.getLogin(),
                encryptedPassword, dadosUsuarioIn.getRole());
        this.userRepository.save(usuarioModel);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UsuarioOutputListagem>> listarTodos() throws Exception {
        return ResponseEntity.ok(toOut(userRepository.findAll()));
    }

    @Override
    public ResponseEntity<List<UsuarioOutputListagem>> listarPorID(int id) {
        return ResponseEntity.ok(toOut(userRepository.findById(id)));
    }

    @Override
    public ResponseEntity<Void> atualizar(UsuarioInputAtualizacao dadosAtualizacaoIn) throws Exception {
        var usuario = userRepository.getReferenceById(dadosAtualizacaoIn.getId());
        usuario.atualizarInformacoes(dadosAtualizacaoIn);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> remover(int id) throws Exception {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> inativar(int id) {
        var usuario = userRepository.getReferenceById(id);
        usuario.desativar();
        userRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> reativar(int id) {
        var usuario = userRepository.getReferenceById(id);
        usuario.ativar();
        userRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    private List<UsuarioOutputListagem> toOut(List<UsuarioModel> ls) {
        List<UsuarioOutputListagem> lsOut = new ArrayList<>();
        ls.forEach(model -> {
            lsOut.add(UsuarioOutputListagem.fromModel(model));
        });
        return lsOut;
    }

}
