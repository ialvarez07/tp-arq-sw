package com.tpasw.Dao;

import com.tpasw.Model.Measurement;

import java.util.List;


/**
 * Created by ignacio on 22/06/17.
 */
public interface MeasurementDao {

    void saveMeasurement(Measurement measurement);

    List<Measurement> findAllMeasurement();

    Measurement findById(String id);

}