package hu.vsimon.studentmanager.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Student> findPage(int pageNumber, int pageSize) {
        if(pageSize < 5) pageSize = 5;
        if(pageSize > 100) pageSize = 100;

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageable);
    }
}
