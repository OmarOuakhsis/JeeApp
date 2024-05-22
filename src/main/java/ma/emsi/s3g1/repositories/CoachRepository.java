package ma.emsi.s3g1.repositories;

import ma.emsi.s3g1.entities.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
    Page<Coach> findByFullNameContains(String fullName, Pageable pageable);
}