package Application.repo;

import Application.entities.LogsDataItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsDataRepository extends JpaRepository<LogsDataItem, Integer> {
}
