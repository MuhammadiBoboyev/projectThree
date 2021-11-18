package uz.pdp.projectthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectthree.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
