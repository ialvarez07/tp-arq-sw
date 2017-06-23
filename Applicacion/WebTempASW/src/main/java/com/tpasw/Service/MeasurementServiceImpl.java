package com.tpasw.Service;

import com.tpasw.Dao.MeasurementDao;
import com.tpasw.Model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ignacio on 22/06/17.
 */
@Service("measurementService")
@Transactional
public class MeasurementServiceImpl implements MeasurementService{
    @Autowired
    private MeasurementDao measurementDao;
    @Override
    public void saveMeasurement(Measurement measurement) {
        measurementDao.saveMeasurement(measurement);
    }

    @Override
    public List<Measurement> findAllMeasurement() {
        return measurementDao.findAllMeasurement();
    }

    @Override
    public Measurement findById(String id) {
        return measurementDao.findById(id);
    }
}
