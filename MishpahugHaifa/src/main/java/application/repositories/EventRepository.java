package application.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.entities.QEventEntity;
import application.entities.QUserEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import application.entities.EventEntity;
import application.repositories.custom.EventRepositoryCustom;

public interface EventRepository extends JpaRepository<EventEntity, Integer>, EventRepositoryCustom,
        QuerydslPredicateExecutor<EventEntity>, QuerydslBinderCustomizer<QEventEntity> {
    public EventEntity getByNameOfEvent(String name);

    @Override
    default public void customize(QuerydslBindings bindings, QEventEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.nameOfEvent).all((path, value) -> {
            List<? extends String> NamesOfEvents = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfEvents.get(0)));
        });
    }

    }
