package br.com.openmind.pecto.repository;

import br.com.openmind.pecto.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByEmail(String email);
}
// extenção "JpaRepository" fornecida pelo Spring Data JPA. Permite operações com a entidade Usuarios