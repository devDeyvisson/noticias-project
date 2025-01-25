package br.com.ifs.noticiasg1project.controller;

import br.com.ifs.noticiasg1project.model.NoticiaModel;
import br.com.ifs.noticiasg1project.service.noticia.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("listar/{id}")
    public ResponseEntity<List<NoticiaModel>> listarNoticiasPorUsuario(@PathVariable int id) {
        try {
            return noticiaService.listarNoticiasPorUsuario(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/recentes")
    public  ResponseEntity<List<NoticiaModel>> listarNoticiasMaisRecentes() {
        try {
            return noticiaService.listarNoticiasMaisRecentes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
