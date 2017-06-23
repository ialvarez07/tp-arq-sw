package com.tpasw.Dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class AbstractDao {
	@Autowired
	private EntityManager em;
	
	protected EntityManager getEm(){return em;}

	public void persist(Object entity) {
		getEm().persist(entity);
	}
	
	public void delete(Object entity) {
		getEm().remove(entity);
	}

}
