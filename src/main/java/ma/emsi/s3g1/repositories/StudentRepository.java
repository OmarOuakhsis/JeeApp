package ma.emsi.s3g1.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.s3g1.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

@Transactional public interface StudentRepository extends JpaRepository<Student,Integer> {

List<Student> findByFullName(String name);
List<Student> findByFullNameContains(String name);
Page<Student> findByFullNameContains(String name, PageRequest pageable);

}
