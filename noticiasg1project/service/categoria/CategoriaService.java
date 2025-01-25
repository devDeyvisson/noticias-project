package br.com.ifs.noticiasg1project.service.categoria;

import br.com.ifs.noticiasg1project.input.CategoriaInputDefinirCategoria;
import br.com.ifs.noticiasg1project.model.CategoriaModel;
import br.com.ifs.noticiasg1project.repository.CategoriaRepository;
import br.com.ifs.noticiasg1project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity<List<CategoriaModel>> listarCategorias() throws Exception {
        List<CategoriaModel> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @Override
    public ResponseEntity<Void> definirCategorias(int id, CategoriaInputDefinirCategoria categoriaIn) throws Exception {
        var usuario = userRepository.getReferenceById(id);
        var categoria = categoriaRepository.findByNome(categoriaIn.getNome());
        if (usuario != null && categoria != null) {
            if (!usuario.getCategorias().contains(categoria)) {
                usuario.getCategorias().add(categoria);
                userRepository.save(usuario);
            }
        } else {
            throw new Exception("Usuário inválido, ou categoria inválida!");
        }
        return ResponseEntity.ok().build();
    }
}
