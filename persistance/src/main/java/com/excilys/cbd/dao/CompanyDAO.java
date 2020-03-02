package com.excilys.cbd.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cbd.model.Company;


@Repository
@Transactional
public class CompanyDAO {
	private ComputerDAO computerDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	SessionFactory sessionFactory;
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = :id";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = :name";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static String EFFACER = "DELETE FROM company WHERE id = :id";
	
	@PersistenceContext
	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;
	
	public CompanyDAO(EntityManager entityManager)
	{
		super();
		this.entityManager=entityManager;
	}
	
	public List<Company> toutCompany() {
		
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery("from Company", Company.class);
		return query.getResultList();
	}

	public Company trouverCompany (long id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery("from Company where id = :id", Company.class);
	    query.setParameter("id", id);
	    return (Company)query.getSingleResult();
	}
	
	public Company trouverCompany (String name) {
		Session session = entityManager.unwrap(Session.class);
		Query<Company> query = session.createQuery("from Company where name = :name", Company.class);
	    query.setParameter("name", name);
	    return (Company)query.getSingleResult();
	}
	
	public int effacer(long companyId) {
		computerDao.effacerComputParCompa(companyId);
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",companyId);
		return namedParameterJdbcTemplate.update(EFFACER,namedParameters);
	}
}