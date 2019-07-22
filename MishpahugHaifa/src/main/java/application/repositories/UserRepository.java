package application.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.StringPath;

import application.models.user.QUserEntity;
import application.models.user.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>,
        QuerydslPredicateExecutor<UserEntity>, QuerydslBinderCustomizer<QUserEntity>{

    public UserEntity findByUserNameAndAndEncrytedPassword(String username, String password);

    public UserEntity findByUserName(String userName);
    
    public Boolean existsByUserName(String userName);
    
    public Boolean existsByUserNameAndEMail(String userName, String email);

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

        bindings.bind(root.phoneNumber).all((path, value) -> {
            List<? extends String> phoneNumbers = new ArrayList<>(value);
            return Optional.of(path.contains(phoneNumbers.get(0)));
        });

    }

}
