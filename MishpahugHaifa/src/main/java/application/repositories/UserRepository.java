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

import application.entities.GenderEntity;
import application.entities.KitchenTypeEntity;
import application.entities.MaritalStatusEntity;
import application.entities.QUserEntity;
import application.entities.ReligionEntity;
import application.entities.UserEntity;
import application.repositories.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, UserRepositoryCustom,
		QuerydslPredicateExecutor<UserEntity>, QuerydslBinderCustomizer<QUserEntity> {

	public List<UserEntity> findByReligion(ReligionEntity religionEntity);

	public List<UserEntity> findByKitchenType(KitchenTypeEntity kitchenEntity);

	public List<UserEntity> findByGender(GenderEntity genderEntity);

	public List<UserEntity> findByMaritalStatus(MaritalStatusEntity maritalStatusEntity);

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
	}

}
