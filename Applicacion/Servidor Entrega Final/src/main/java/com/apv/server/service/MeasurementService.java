
package com.apv.server.service;

import com.apv.server.dao.IMeasurementDAO;
import com.apv.server.entity.Measurement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService implements IMeasurementService {
    @Autowired
    private IMeasurementDAO measurementDAO;

    @Override
    public List<Measurement> getAllMeasurements() {
        return measurementDAO.getAllMeasurements();
    }

    @Override
    public List<Measurement> getMeasurementsByDeviceNameFromDate(String devName, long dateTime) {
        return measurementDAO.getMeasurementsByDeviceNameFromDate(devName, dateTime);
    }

    @Override
    public Measurement getMeasurementById(long measurementId) {
        return measurementDAO.getMeasurementById(measurementId);
    }

    @Override
    public synchronized boolean addMeasurement(Measurement measurement) {
        if (measurementDAO.measurementExists(measurement.getDevName(), measurement.getDateTime())) {
            return false;
        } else {
            measurementDAO.addMeasurement(measurement);
            return true;
        }
    }

    @Override
    public void updateMeasurement(Measurement measurement) {
        measurementDAO.updateMeasurement(measurement);
    }

    @Override
    public void deleteMeasurement(long measurementId) {
        measurementDAO.deleteMeasurement(measurementId);
    }    
}
