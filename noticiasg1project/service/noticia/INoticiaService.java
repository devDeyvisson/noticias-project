package br.com.ifs.noticiasg1project.service.noticia;

import br.com.ifs.noticiasg1project.model.NoticiaModel;
import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INoticiaService {
    void carregarNoticias();
    void deninirDataPublicacao(SyndEntry entry, NoticiaModel noticia);
    ResponseEntity<List<NoticiaModel>> listarNoticiasPorUsuario(int id) throws Exception;
    ResponseEntity<List<NoticiaModel>> listarNoticiasMaisRecentes() throws Exception;
}
