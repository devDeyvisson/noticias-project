package br.com.ifs.noticiasg1project.service.noticia;

import br.com.ifs.noticiasg1project.infra.loadnews.FeedRssService;
import br.com.ifs.noticiasg1project.model.CategoriaModel;
import br.com.ifs.noticiasg1project.model.NoticiaModel;
import br.com.ifs.noticiasg1project.repository.CategoriaRepository;
import br.com.ifs.noticiasg1project.repository.NoticiaRepository;
import br.com.ifs.noticiasg1project.repository.UsuarioRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticiaService implements INoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private FeedRssService feedRssService;

    @Transactional
    @Override
    public void carregarNoticias() {
        List<CategoriaModel> categorias = categoriaRepository.findAll();
        for (CategoriaModel categoria : categorias) {
            List<SyndEntry> entries = feedRssService.lerFeedRSS(categoria.getEnderecoUrl());
            for (SyndEntry entry : entries) {
                if (!noticiaRepository.existsByTituloAndCategoria(entry.getTitle(), categoria)) {
                    NoticiaModel noticia = new NoticiaModel();
                    noticia.setTitulo(entry.getTitle());
                    noticia.setEnderecoImg(feedRssService.extrairUrlDaImagem(entry));

                    deninirDataPublicacao(entry, noticia);

                    noticia.setCategoria(categoria);
                    noticiaRepository.save(noticia);
                }
            }
        }
    }

    @Override
    public void deninirDataPublicacao(SyndEntry entry, NoticiaModel noticia) {
        Date publishedDate = entry.getPublishedDate();
        if (publishedDate != null) {
            Instant instant = publishedDate.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            noticia.setDtPublicacao(localDate);
        }
    }

    @Override
    public ResponseEntity<List<NoticiaModel>> listarNoticiasPorUsuario(int id) {
        try {
            var usuario = userRepository.getReferenceById(id);
            List<CategoriaModel> categorias = new ArrayList<>(usuario.getCategorias());
            List<NoticiaModel> noticias;
            if (categorias.isEmpty()) {
                noticias = noticiaRepository.findAll();
            } else {
                noticias = noticiaRepository.findByCategoriaIn(categorias);
            }
            return ResponseEntity.ok(noticias);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<NoticiaModel>> listarNoticiasMaisRecentes() throws Exception {
        List<NoticiaModel> noticias = noticiaRepository.findAllByOrderByDtPublicacaoDesc();
        return ResponseEntity.ok(noticias);
    }
}
