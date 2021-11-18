package uz.pdp.projectthree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectthree.entity.Group;
import uz.pdp.projectthree.entity.Student;
import uz.pdp.projectthree.entity.Subject;
import uz.pdp.projectthree.payload.StudentDto;
import uz.pdp.projectthree.repository.GroupRepository;
import uz.pdp.projectthree.repository.StudentRepository;
import uz.pdp.projectthree.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
    }

    //3. FACULTY DEKANAT
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student> getStudentListForFaculty(@PathVariable Integer facultyId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_FacultyId(facultyId, pageable);
    }

    //4. GROUP OWNER
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentListForGroup(@PathVariable Integer groupId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAllByGroup_Id(groupId, pageable);
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentDto studentDto) {
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent())
            return "group not found";
        List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectsId());
        if (subjectList.size() != studentDto.getSubjectsId().size())
            return "subject not found";
        studentRepository.save(new Student(null, studentDto.getFirstName(), studentDto.getLastName(), optionalGroup.get(), subjectList));
        return "added";
    }

    @PutMapping("/edit")
    public String editStudent(@PathVariable int id, @RequestBody StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent())
            return "student not found";
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent())
            return "group not found";
        List<Subject> subjectList = subjectRepository.findAllById(studentDto.getSubjectsId());
        if (subjectList.size() != studentDto.getSubjectsId().size())
            return "subject not found";
        Student student = optionalStudent.get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setGroup(optionalGroup.get());
        student.setSubjects(subjectList);
        studentRepository.save(student);
        return "edited";
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@PathVariable int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.delete(optionalStudent.get());
            return "deleted";
        } else {
            return "student not found";
        }
    }
}
