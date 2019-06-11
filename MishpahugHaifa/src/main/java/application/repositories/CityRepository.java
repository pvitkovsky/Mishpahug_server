package application.repositories;

import application.entities.CityEntity;
import application.entities.QCityEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Integer>,
        QuerydslPredicateExecutor<CityEntity>, QuerydslBinderCustomizer<QCityEntity> {
    public CityEntity getByName(String name);
    public void deleteByName(String name);
    public Boolean existsByName(String name);


    @Override
    default public void customize(QuerydslBindings bindings, QCityEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.bind(root.name).all((path, value) -> {
            List<? extends String> NamesOfCities = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfCities.get(0)));
        });
    }

}
