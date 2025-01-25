package br.com.ifs.noticiasg1project.repository;

import br.com.ifs.noticiasg1project.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {
    CategoriaModel findByNome(String nome);

}
