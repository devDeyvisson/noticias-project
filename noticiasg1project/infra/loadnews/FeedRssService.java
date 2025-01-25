package br.com.ifs.noticiasg1project.infra.loadnews;

import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class FeedRssService {

    public List<SyndEntry> lerFeedRSS(String urlFeed) {
        try {
            URL url = new URL(urlFeed);
            XmlReader reader = new XmlReader(url);
            SyndFeed syndFeed = new SyndFeedInput().build(reader);
            return syndFeed.getEntries();
        } catch (Exception e) {
            throw new RuntimeException("O carregamento do Feed falhou!", e);
        }
    }

    public String extrairUrlDaImagem(SyndEntry entry) {
        List<Module> modules = entry.getModules();
        for (Module module : modules) {
            if (module instanceof MediaEntryModule) {
                MediaEntryModule mediaModule = (MediaEntryModule) module;
                MediaContent[] mediaContents = mediaModule.getMediaContents();
                for (MediaContent mediaContent : mediaContents) {
                    if (mediaContent.getReference() != null && mediaContent.getReference().toString().startsWith("https://")) {
                        return mediaContent.getReference().toString();
                    }
                }
            }
        }
        return null;
    }
}
