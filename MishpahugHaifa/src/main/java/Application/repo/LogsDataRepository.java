package Application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Application.entities.LogsDataEntity;

public interface LogsDataRepository extends JpaRepository<LogsDataEntity, Long>{

}
