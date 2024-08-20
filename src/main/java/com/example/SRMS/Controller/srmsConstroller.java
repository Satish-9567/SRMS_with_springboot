package com.example.SRMS.Controller;

import com.example.SRMS.Entity.Result;
import com.example.SRMS.Entity.Student;
import com.example.SRMS.Repository.ResultRepository;
import com.example.SRMS.Repository.StudentRepository;
import com.example.SRMS.Service.AdminService;
import com.example.SRMS.Service.ResultService;
import com.example.SRMS.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class srmsConstroller {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ResultRepository resultRepository;
    private Integer id;
    @GetMapping("/home")
    public String home(){
        return "index";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashBoard(){
        return "admin_home";
    }
    @PostMapping("/student")
    public String studentHome(){
        return "student";
    }
    @GetMapping("/student/home")
    public String studentLogin(@RequestParam("rollNumber") String rollNumber,
                               @RequestParam("password") String password,
                               Model model){
        boolean isIntger=studentService.isIntger(rollNumber);
        int newrollNumber=0;
        if (!isIntger){
            model.addAttribute("error", "Invalid roll number . Please try again.");
            return "invalid_login";
        }
        else{
            newrollNumber=Integer.parseInt(rollNumber);
        }
        boolean isValid = studentService.isValid(newrollNumber,password);
        Student student= studentService.getStudent();

        if (!isValid){
            model.addAttribute("error", "Invalid roll number or password. Please try again.");
            return "invalid_login";
        }
        model.addAttribute("student",student);
        return "student";
    }
    @GetMapping("/student/login")
    public String studentLoginPage(){
        return "invalid_login";
    }
    @GetMapping("/admin/home")
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model){
        boolean isValid=adminService.isValid(username,password);
        if (!isValid){
            model.addAttribute("error", "Invalid Username or password. Please try again.");
            return "admin";
        }
        return "admin_home";
    }
    @GetMapping("/home/admin")
    public String homeAdmin(){
        return "admin";
    }
    @PostMapping ("/add/result")
    public String addResult(@ModelAttribute("result")Result result){
        resultService.addResult(result);
        return "redirect:/admin/add/result";
    }
    @GetMapping("/admin/add/result")
    public String addResultpage(Model model){
        Result result=new Result();
        model.addAttribute("result",result);
        return "add_result";
    }
    @GetMapping("/viewResults/{id}")
    public String viewResultPage(@PathVariable Integer id,
                                 Model model){
//        Integer rollnumber = studentService.getRollNumber(id);
        model.addAttribute("result",resultService.getResult(id));
        return "view_result";
    }
    @GetMapping("/update/profile/{id}")
    public String updateProfilePage(@PathVariable Integer id,
                                    Model model){
        this.id=id;
        Student student=studentService.getStudent(id);
        model.addAttribute("student",student);
        return "update_student";
    }
    @PostMapping("/student/updateProfile")
    public String updateProfile(
                                @ModelAttribute("student") Student student,
                                Model model){
        System.out.println(this.id+" "+student.getName());
        Student student2=studentRepository.findById(this.id).get();
        student2.setId(this.id);
        student2.setName(student.getName());
        student2.setPassword(student.getPassword());
        student2.setRollnumber(student.getRollnumber());
        studentRepository.save(student2);
        //studentService.updateStudent(this.id,student);
        return "student";
    }
    @GetMapping("/admin/results")
    public String getResultList(Model model){
        model.addAttribute("results",resultService.getResultList());
        return "resultlist";
    }
    @GetMapping("/admin/students")
    public String adminStudents(Model model){
        model.addAttribute("students",studentService.getStudents());
        return "manage_student";
    }
    @GetMapping("/admin/logout")
    public String logout(){
        return "logout";
    }
    @GetMapping("/admin/addStudent")
    public String addStudentpage(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "add_student";
    }
    @PostMapping("/admin/students/add")
    public String addStudent(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/admin/addStudent";
    }
    @GetMapping("/admin/updateStudent/{id}")
    public String updatestudentpage(@PathVariable Integer id,
                                    Model model){
        Student student=studentService.getStudent(id);
        model.addAttribute("student",student);
        return "update_admin_student";
    }
    @GetMapping("admin/student/update/Profile/{id}")
    public String updateStudent(@PathVariable Integer id,
                                @ModelAttribute("student") Student student,
                                Model model){
        //Student student=studentService.getStudent(id);
        Student student2=studentRepository.findById(id).get();
        student2.setId(id);
        student2.setName(student.getName());
        student2.setPassword(student.getPassword());
        student2.setRollnumber(student.getRollnumber());
        studentRepository.save(student2);
        return "redirect:/admin/students";
    }
    @GetMapping("/admin/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }
    @GetMapping("/updateResult/{id}")
    public String updateResultPage(@PathVariable Integer id,
                               Model model){
        model.addAttribute("result",resultService.getResult(id));
        return "update_result";
    }
    @PostMapping("/update/result/{id}")
    public String updateResult(@PathVariable Integer id,@ModelAttribute("result") Result result){
        Result result1=resultRepository.findById(id).get();
        result1.setId(id);
        result1.setHindi(result.getHindi());
        result1.setEnglish(result.getEnglish());
        result1.setMath(result.getMath());
        result1.setScience(result.getScience());
        result1.setSocial_science(result.getSocial_science());
        resultRepository.save(result1);
        return "redirect:/admin/results";
    }
    @GetMapping("/deleteResult/{id}")
    public String deleteResult(@PathVariable Integer id){
        resultService.deleteResult(id);
        return "redirect:/admin/results";
    }
}
