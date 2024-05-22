package ma.emsi.s3g1;

import ma.emsi.s3g1.entities.Student;
import ma.emsi.s3g1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Date;

@SpringBootApplication
public class S3G1Application implements CommandLineRunner {

    @Autowired StudentRepository studentRepository;
    public static void main(String[] args) {
        SpringApplication.run(S3G1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* studentRepository.save(new Student(null, "QZE","Ahmed",new Date("2000/02/04"), true, new Date()));
        studentRepository.save(new Student(null, "SER","Nora",new Date("2001/05/04"), false, new Date()));
        studentRepository.save(new Student(null, "VFT","Ilyas",new Date("2002/07/04"), true, new Date()));
        studentRepository.save(new Student(null, "RTH","Yahya",new Date("2006/01/04"), false, new Date()));
        studentRepository.save(new Student(null, "XCD","Oussama",new Date("2004/08/04"), true, new Date()));
        studentRepository.save(new Student(null, "GTY","Hatim",new Date("2005/09/04"), false, new Date()));
        studentRepository.save(new Student(null, "NBJ","Soufiane",new Date("2003/03/04"), true, new Date()));
        studentRepository.save(new Student(null, "LOM","Wafae",new Date("2003/09/04"), false, new Date()));
*/

       // List<Student> students = studentRepository.findAll();
        // students.forEach(System.out::println);



        List<Student> pageStudents = studentRepository.findByFullNameContains("ah");

        pageStudents.forEach(System.out::println);
    }
}
