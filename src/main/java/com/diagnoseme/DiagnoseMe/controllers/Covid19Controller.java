package com.diagnoseme.DiagnoseMe.controllers;
import com.diagnoseme.DiagnoseMe.util.Predictor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class Covid19Controller {

    @PostMapping(path = "/covid")
    public @ResponseBody boolean makePrediction(@RequestBody int[] data){
        String predictValue= new Predictor().predict("covid19_model", data);
        return predictValue.equalsIgnoreCase("yes");
    }

}
