package uz.pdp.projectthree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectthree.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
