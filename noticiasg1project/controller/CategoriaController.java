package br.com.ifs.noticiasg1project.controller;

import br.com.ifs.noticiasg1project.input.CategoriaInputDefinirCategoria;
import br.com.ifs.noticiasg1project.model.CategoriaModel;
import br.com.ifs.noticiasg1project.service.categoria.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarCategorias() {
        try {
            return categoriaService.listarCategorias();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/definircategorias/{id}")
    public ResponseEntity<Void> definirCategorias(@PathVariable int id, @RequestBody @Valid CategoriaInputDefinirCategoria categoriaIn) {
        try {
            return categoriaService.definirCategorias(id, categoriaIn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
