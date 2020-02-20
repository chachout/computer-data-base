package com.excilys.cbd.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

@Repository
@Transactional
public class ComputerDAO
{
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static String CREER = "insert into Computer (name, introduced, discontinued, company.id) values (:name,:introduced,:discontinued,:company.id)";
	public static String TROUVERID = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=:id";
	public static String TROUVERNOM = "SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE :recherche OR LOWER(company.name) LIKE :recherche OR introduced LIKE :recherche OR discontinued LIKE :recherche;";
	public static String MODIFIER = "update Computer set name = :name, introduced = :introduced, discontinued = :discontinued, company.id = :company.id WHERE id = :id";
	public static String EFFACER = "DELETE FROM computer WHERE id = :id";
	public static String EFFACERPARCOMPA = "DELETE FROM computer WHERE company_id = :id";
	public static String SELECTION = "from Computer";
	public static String ASCENDANT = " asc";
	public static String DESCENDANT = " desc";
	public static String ORDER = " order by ";
	@Autowired
	SessionFactory sessionFactory;
	
	@PersistenceContext
	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager entityManager;
	
	public ComputerDAO(EntityManager entityManager)
	{
		super();
		this.entityManager=entityManager;
	}

	public List<Computer> toutComputer() {
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer", Computer.class);
		return query.getResultList();
	}
	
	public int count() {
		List<Computer> ListComputer= toutComputer();
		return ListComputer.size();
	}
	
	public List<Computer> pageComputer(int tri, String colonne, int limit, int offset) {
		String requete;
		if (tri==0||colonne==""||colonne==null) {
			requete = SELECTION;
		} else if (tri==1) {
				requete = SELECTION + ORDER + colonne + ASCENDANT;
			} else {
				requete = SELECTION + ORDER + colonne + DESCENDANT;
			}
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery(requete, Computer.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List computerList = query.getResultList();
    	return computerList;
	}
	
	public Computer trouverid (long id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer where id = :id", Computer.class);
	    query.setParameter("id", id);
	    return (Computer)query.getSingleResult();
	}
	
	public List<Computer> trouvernom (String recherche) {
		recherche=recherche.toLowerCase();
		recherche="%"+recherche+"%";
		Session session = entityManager.unwrap(Session.class);
		Query<Computer> query = session.createQuery("from Computer where lower(name) like :recherche or lower(company.name) like :recherche or introduced like :recherche or discontinued like :recherche", Computer.class);
		query.setParameter("recherche", recherche);
		List computerList = query.getResultList();
    	return computerList;	
    }
	
	public int modifier(Computer comp) {
		Session session = entityManager.unwrap(Session.class);
//		Query<Computer> query = session.createQuery(MODIFIER, Computer.class);
//		query.setParameter("name", comp.getName());
//		query.setParameter("introduced", comp.getIntroduced());
//		query.setParameter("discontinued", comp.getDiscontinued());
//		query.setParameter("company.id", comp.getCompany().getId());
//		query.setParameter("id", comp.getId());
//		int result = query.executeUpdate();
		session.update(comp);
    	return 1;
	}
	
	public int effacer(long id) {
		Session session = entityManager.unwrap(Session.class);
		Computer comp = trouverid(id);
		session.delete(comp);
		return 1;
	}
	
	public int effacerComputParCompa(long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.update(EFFACERPARCOMPA, namedParameters);
	}
	
	public int creer(Computer comp) {
		Session session = entityManager.unwrap(Session.class);
		session.save(comp);
    	return 1;
	}
}