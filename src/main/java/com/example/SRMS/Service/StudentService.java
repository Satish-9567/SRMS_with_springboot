package com.example.SRMS.Service;

import com.example.SRMS.Entity.Result;
import com.example.SRMS.Entity.Student;
import com.example.SRMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private Student student1;
    public boolean isValid(int rollNumber,String password){
        List<Student> studentList = studentRepository.findAll();
        for (Student student:studentList){
            if(rollNumber==student.getRollnumber()&&password.equals(student.getPassword())){
                this.student1=student;
                return true;
            }
        }
        return false;
    }
    public Student getStudent(){
        return this.student1;
    }
    public Student getStudent(Integer id){
        return studentRepository.findById(id).get();
    }
    public int getRollNumber(Integer id){
        List<Student> students=studentRepository.findAll();
        for (Student student:students){
            if(student.getRollnumber()==id){
                return student.getRollnumber();
            }
            System.out.println(id+" "+student.getRollnumber());
        }
        return 1;
    }
    public void updateStudent(Integer id,Student student){
        Student student2=studentRepository.findById(id).get();
        student2.setId(student.getId());
        student2.setName(student.getName());
        student2.setPassword(student.getPassword());
        student2.setRollnumber(student.getRollnumber());
        studentRepository.save(student2);

    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public boolean isIntger(String roll){
        for(char ch:roll.toCharArray()){
            if(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'){
                return false;
            }
            if (!Character.isDigit(ch) && (ch < 32 || (ch > 57 && ch < 65) || (ch > 90 && ch < 97) || ch > 122)) {
                return false;
            }
        }
        return true;
    }
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }

}
