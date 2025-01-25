package br.com.ifs.noticiasg1project.repository;

import br.com.ifs.noticiasg1project.model.CategoriaModel;
import br.com.ifs.noticiasg1project.model.NoticiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaModel, Integer> {
    boolean existsByTituloAndCategoria(String titulo, CategoriaModel categoria);
    List<NoticiaModel> findByCategoriaIn(List<CategoriaModel> categorias);
    List<NoticiaModel> findAll();
    List<NoticiaModel> findAllByOrderByDtPublicacaoDesc();
}

