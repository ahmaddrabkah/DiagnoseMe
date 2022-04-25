package com.diagnoseme.DiagnoseMe.controllers;
import com.diagnoseme.DiagnoseMe.util.Predictor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class Covid19Controller {

    @PostMapping(path = "/covid")
    public @ResponseBody boolean getDataForPatient(@RequestBody int[] data){
        //int[] inputData = data.stream().mapToInt(Integer::new).toArray();
        String predictValue= new Predictor().predict("covid19_model", data);
        System.out.println(predictValue);
        if(predictValue.equalsIgnoreCase("yes"))
            return true;
        else
            return false ;
    }

}
