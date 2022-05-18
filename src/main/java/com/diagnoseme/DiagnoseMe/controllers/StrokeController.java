package com.diagnoseme.DiagnoseMe.controllers;

import com.diagnoseme.DiagnoseMe.util.Predictor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StrokeController {

    @PostMapping("/stroke")
    public @ResponseBody boolean makePrediction(@RequestBody int[] data){
        String predictValue = new Predictor().predict("heart_stroke_model",data);
        return predictValue.equalsIgnoreCase("yes");
    }
}
