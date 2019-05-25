package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entities.GenderEntity;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {
    public GenderEntity getByName(String name);
}
