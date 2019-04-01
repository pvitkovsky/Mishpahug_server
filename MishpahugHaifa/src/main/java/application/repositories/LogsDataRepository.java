package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;

import application.entities.LogsDataEntity;
import application.entities.QLogsDataEntity;

public interface LogsDataRepository extends JpaRepository<LogsDataEntity, Long>,
		QuerydslPredicateExecutor<LogsDataEntity>, QuerydslBinderCustomizer<QLogsDataEntity> {

	@Override
	default public void customize(QuerydslBindings bindings, QLogsDataEntity root) {
		bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
	}
}
