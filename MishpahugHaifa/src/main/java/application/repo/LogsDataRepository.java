package application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.LogsDataEntity;

public interface LogsDataRepository extends JpaRepository<LogsDataEntity, Long>{

}
