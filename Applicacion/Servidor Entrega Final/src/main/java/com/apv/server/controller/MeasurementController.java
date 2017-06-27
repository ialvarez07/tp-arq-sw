
package com.apv.server.controller;

import com.apv.server.entity.Measurement;
import com.apv.server.service.IMeasurementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("measurement")
public class MeasurementController {
    @Autowired
    private IMeasurementService measurementService;

    @GetMapping("measurement/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable("id") Integer id) {
        Measurement measurement = measurementService.getMeasurementById(id);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }
    
    @GetMapping("measurements")
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        List<Measurement> list = measurementService.getAllMeasurements();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @PostMapping("measurement")
    public ResponseEntity<Void> addMeasurement(@RequestBody Measurement measurement, UriComponentsBuilder builder) {
        boolean flag = measurementService.addMeasurement(measurement);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/measurement/{id}").buildAndExpand(measurement.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping("measurement")
    public ResponseEntity<Measurement> updateMeasurement(@RequestBody Measurement measurement) {
        measurementService.updateMeasurement(measurement);
        return new ResponseEntity<>(measurement, HttpStatus.OK);
    }
    
    @DeleteMapping("measurement/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable("id") Integer id) {
        measurementService.deleteMeasurement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("deltameasurements")
    public ResponseEntity<List<Measurement>> getMeasurementsByDevNameFromDate
        (@RequestParam("devName") String devName, @RequestParam("dateTime") Long dateTime) {
            List<Measurement> list = measurementService.getMeasurementsByDeviceNameFromDate(devName, dateTime);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
}
