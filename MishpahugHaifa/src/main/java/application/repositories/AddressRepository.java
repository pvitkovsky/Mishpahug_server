package application.repositories;

import application.entities.properties.AddressEntity;
import application.entities.QAddressEntity;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>,
        QuerydslPredicateExecutor<AddressEntity>,
        QuerydslBinderCustomizer<QAddressEntity> {

    @Override
    default public void customize(QuerydslBindings bindings, QAddressEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.cityEntity.name).all((path, value) -> {
            List<? extends String> NamesOfEvents = new ArrayList<>(value);
            return Optional.of(path.contains(NamesOfEvents.get(0)));
        });
    }
}
