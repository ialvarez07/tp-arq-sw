package com.apv.server.dao;

import com.apv.server.entity.Measurement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class MeasurementDAO implements IMeasurementDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Measurement> getAllMeasurements() {
        String hql = "FROM Measurement";
        return (List<Measurement>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<Measurement> getMeasurementsByDeviceNameFromDate(String devName, long dateTime) {
        String hql = "FROM Measurement WHERE devName = ? AND dateTime > ?";
        return (List<Measurement>) entityManager.createQuery(hql)
                .setParameter(1, devName)
                .setParameter(2, dateTime)
                .getResultList();
    }

    @Override
    public Measurement getMeasurementById(long measurementId) {
        return entityManager.find(Measurement.class, measurementId);
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        entityManager.persist(measurement);
    }

    @Override
    public void updateMeasurement(Measurement measurement) {
        Measurement m = getMeasurementById(measurement.getId());
        m.setTemp(measurement.getTemp());
        m.setDateTime(measurement.getDateTime());
        m.setDevName(measurement.getDevName());
        entityManager.flush();
    }

    @Override
    public void deleteMeasurement(long measurementId) {
        entityManager.remove(getMeasurementById(measurementId));
    }

    @Override
    public boolean measurementExists(String devName, long dateTime) {
        //String hql = "FROM Measurement WHERE devName = ? AND dateTime >= ?";
        String hql = "FROM Measurement WHERE devName = ? AND dateTime > ?";
        int count = entityManager.createQuery(hql)
                .setParameter(1, devName)
                .setParameter(2, dateTime)
                .getResultList().size();
        return count > 0;
    }

}
