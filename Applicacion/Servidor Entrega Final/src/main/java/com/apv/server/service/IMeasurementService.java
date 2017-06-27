package com.apv.server.service;

import com.apv.server.entity.Measurement;
import java.util.List;

public interface IMeasurementService {
    List<Measurement> getAllMeasurements();
    List<Measurement> getMeasurementsByDeviceNameFromDate(String devName, long dateTime);
    Measurement getMeasurementById(long measurementId);
    boolean addMeasurement(Measurement measurement);
    void updateMeasurement(Measurement measurement);
    void deleteMeasurement(long measurementId);
}
