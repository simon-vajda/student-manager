package hu.vsimon.studentmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(long id) {
        Optional<Student> optional = studentRepository.findById(id);

        if(optional.isEmpty())
            throw new RuntimeException("Student with id=" + id + " is not found");

        return optional.get();
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }
}
