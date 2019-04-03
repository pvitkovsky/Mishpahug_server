package application.repositories;

import application.entities.LogsDataEntity;
import application.entities.QLogsDataEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LogsDataRepository extends JpaRepository<LogsDataEntity, Long>,
		QuerydslPredicateExecutor<LogsDataEntity>, QuerydslBinderCustomizer<QLogsDataEntity> {

	@Override
	default public void customize(QuerydslBindings bindings, QLogsDataEntity root) {
		bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

		bindings.bind(root.userActor.userName).all((path, value) -> {
			List<? extends String> NamesOfEvents = new ArrayList<>(value);
			return Optional.of(path.contains(NamesOfEvents.get(0)));
		});

		bindings.bind(root.date).all((path, value) -> {
			List<? extends LocalDate> dates = new ArrayList<>(value);
			if (dates.size() == 1) {
				return Optional.of(path.eq(dates.get(0)));
			} else {
				LocalDate from = dates.get(0);
				LocalDate to = dates.get(1);
				return Optional.of(path.between(from, to));
			}
		});

		bindings.bind(root.time).all((path, value) -> {
			List<? extends LocalTime> times = new ArrayList<>(value);
			if (times.size() == 1) {
				return Optional.of(path.eq(times.get(0)));
			} else {
				LocalTime from = times.get(0);
				LocalTime to = times.get(1);
				return Optional.of(path.between(from, to));
			}
		});

		bindings.bind(root.description).all((path, value) -> {
			List<? extends String> NamesOfEvents = new ArrayList<>(value);
			return Optional.of(path.contains(NamesOfEvents.get(0)));
		});
	}
}
