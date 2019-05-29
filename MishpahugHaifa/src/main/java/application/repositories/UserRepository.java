package application.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;

import application.entities.QUserEntity;
import application.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>,
        QuerydslPredicateExecutor<UserEntity>, QuerydslBinderCustomizer<QUserEntity>{

    public UserEntity findByUserNameAndAndEncrytedPassword(String username, String password);

    public UserEntity findByUserName(String userName);

    @Override
    default public void customize(QuerydslBindings bindings, QUserEntity root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(root.dateOfBirth).all((path, value) -> {
            List<? extends LocalDate> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                LocalDate from = dates.get(0);
                LocalDate to = dates.get(1);
                return Optional.of(path.between(from, to));
            }
        });

        bindings.bind(root.firstName).all((path, value) -> {
            List<? extends String> firstNames = new ArrayList<>(value);
            return Optional.of(path.contains(firstNames.get(0)));
        });

        bindings.bind(root.lastName).all((path, value) -> {
            List<? extends String> lastNames = new ArrayList<>(value);
            return Optional.of(path.contains(lastNames.get(0)));
        });

        bindings.bind(root.eMail).all((path, value) -> {
            List<? extends String> eMails = new ArrayList<>(value);
            return Optional.of(path.eq(eMails.get(0)));
        });

        bindings.bind(root.addressEntity.cityEntity.name).all((path, value) -> {
            List<? extends String> cities = new ArrayList<>(value);
            return Optional.of(path.contains(cities.get(0)));
        });

        bindings.bind(root.addressEntity.street).all((path, value) -> {
            List<? extends String> streets = new ArrayList<>(value);
            return Optional.of(path.contains(streets.get(0)));
        });

        bindings.bind(root.addressEntity.building).all((path, value) -> {
            List<? extends Integer> builds = new ArrayList<>(value);
            return Optional.of(path.eq(builds.get(0)));
        });

        bindings.bind(root.addressEntity.apartment).all((path, value) -> {
            List<? extends Integer> apartments = new ArrayList<>(value);
            return Optional.of(path.eq(apartments.get(0)));
        });

        bindings.bind(root.phoneNumber).all((path, value) -> {
            List<? extends String> phoneNumbers = new ArrayList<>(value);
            return Optional.of(path.contains(phoneNumbers.get(0)));
        });

        bindings.bind(root.gender.name).all((path, value) -> {
            List<? extends String> genderNames = new ArrayList<>(value);
            return Optional.of(path.eq(genderNames.get(0)));
        });
    }

}
