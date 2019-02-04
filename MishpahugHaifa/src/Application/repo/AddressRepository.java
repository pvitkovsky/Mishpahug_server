package Application.repo;

import Application.entities.AddressItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressItem, Integer> {}
