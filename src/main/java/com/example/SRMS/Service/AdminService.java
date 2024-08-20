package com.example.SRMS.Service;

import com.example.SRMS.Entity.Admin;
import com.example.SRMS.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public boolean isValid(String user,String pass){
        List<Admin> admins =adminRepository.findAll();
        for (Admin admin:admins){
            if (admin.getName().equals(user)&&admin.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
}
