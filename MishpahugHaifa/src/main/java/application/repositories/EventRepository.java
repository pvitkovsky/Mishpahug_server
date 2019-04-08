package application.repositories;

import application.entities.EventEntity;
import application.entities.QEventEntity;
import application.repositories.custom.EventRepositoryCustom;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: disallow/hide DELETE;
public interface EventRepository extends JpaRepository<EventEntity, Integer>,
        QuerydslPredicateExecutor<EventEntity>, QuerydslBinderCustomizer<QEventEntity>,
        EventRepositoryCustom{
    public EventEntity getByNameOfEvent(String name);

    @Override
    default public void customize(QuerydslBindings bindings, QEventEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.nameOfEvent).all((path, value) -> {
            List<? extends String> NamesOfEvents = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfEvents.get(0)));
        });

        bindings.bind(root.holiDay.name).all((path, value) -> {
            List<? extends String> NamesOfHolidays = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfHolidays.get(0)));
        });

        bindings.bind(root.addressEntity.cityEntity.name).all((path, value) -> {
            List<? extends String> NamesOfCities = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfCities.get(0)));
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
    }

    }
