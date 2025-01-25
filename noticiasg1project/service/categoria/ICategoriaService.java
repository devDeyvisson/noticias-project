package br.com.ifs.noticiasg1project.service.categoria;

import br.com.ifs.noticiasg1project.input.CategoriaInputDefinirCategoria;
import br.com.ifs.noticiasg1project.model.CategoriaModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICategoriaService {
    ResponseEntity<List<CategoriaModel>> listarCategorias() throws Exception;
    ResponseEntity<Void> definirCategorias(int id, CategoriaInputDefinirCategoria categoriaIn) throws Exception;
}
