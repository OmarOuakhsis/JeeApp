package ma.emsi.s3g1.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.s3g1.entities.Sport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

@Transactional public interface SportRepository extends JpaRepository<Sport,Integer> {


    Page<Sport> findByNameContains(String name, PageRequest pageable);
}
