package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.PasswordEntity;

public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {

}
