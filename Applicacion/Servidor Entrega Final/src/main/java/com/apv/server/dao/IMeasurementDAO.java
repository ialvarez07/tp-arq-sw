package com.apv.server.dao;

import com.apv.server.entity.Measurement;
import java.util.List;

public interface IMeasurementDAO {
    List<Measurement> getAllMeasurements();
    List<Measurement> getMeasurementsByDeviceNameFromDate(String devName, long dateTime);
    Measurement getMeasurementById(long measurementId);
    void addMeasurement(Measurement measurement);
    void updateMeasurement(Measurement measurement);
    void deleteMeasurement(long measurementId);
    boolean measurementExists(String devName, long dateTime);    
}
