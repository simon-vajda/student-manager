package hu.vsimon.studentmanager.student;

import hu.vsimon.studentmanager.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
