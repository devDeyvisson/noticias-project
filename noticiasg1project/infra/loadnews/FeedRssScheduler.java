package br.com.ifs.noticiasg1project.infra.loadnews;

import br.com.ifs.noticiasg1project.service.noticia.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FeedRssScheduler {
    @Autowired
    private NoticiaService noticiaService;

    @Scheduled(fixedRate = 3600000)
    public void lerAgendadorFeedRss() {
        noticiaService.carregarNoticias();
    }
}
