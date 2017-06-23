package com.tpasw.Dao;
import java.util.List;

import com.tpasw.Model.Measurement;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
@Repository("measurementDao")
public class MeasurementDaoImpl extends AbstractDao implements MeasurementDao {

    public void saveMeasurement(Measurement measurement) {
        persist(measurement);
    }

    @SuppressWarnings("unchecked")
    public List<Measurement> findAllMeasurement() {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Measurement> q = cb.createQuery(Measurement.class);
        Root<Measurement> m = q.from(Measurement.class);
        return (List<Measurement>) q.select(m);
    }

    public Measurement findById(String id){
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Measurement> q = cb.createQuery(Measurement.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        Root<Measurement> m = q.from(Measurement.class);
        return (Measurement) q.select(m).where(cb.equal(m.get(id), p));
    }


}
