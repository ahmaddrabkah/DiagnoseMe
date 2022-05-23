package com.diagnoseme.DiagnoseMe.controllers;

import com.diagnoseme.DiagnoseMe.util.Predictor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DiabetesController {

    @PostMapping("/diabetes")
    public @ResponseBody boolean makePrediction(@RequestBody int[] data){
        String predictValue = new Predictor().predict("diabetes_model",data);
        return predictValue.equalsIgnoreCase("yes");
    }
}
