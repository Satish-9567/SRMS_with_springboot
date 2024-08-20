package com.example.SRMS.Service;

import com.example.SRMS.Entity.Result;
import com.example.SRMS.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    public void addResult(Result result){
        resultRepository.save(result);
    }
    public Result getResult(Integer roll){
       Result result= resultRepository.findById(roll).get();
       return result;
    }
    public List<Result> getResultList(){
        return resultRepository.findAll();
    }
    public void deleteResult(Integer id){
        resultRepository.deleteById(id);
    }

}
