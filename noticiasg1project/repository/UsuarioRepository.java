package br.com.ifs.noticiasg1project.repository;

import br.com.ifs.noticiasg1project.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    UserDetails findByLogin(String login);
    List<UsuarioModel> findById(int id);
}
