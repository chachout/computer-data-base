package com.excilys.cbd.dao;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class ComputerDAO
{
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static String CREER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name,:introduced,:discontinued,:idCompany)";
	public static String TROUVERID = "SELECT computer.id as computer_id, computer.name as computer_name, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE computer.id=:id";
	public static String TROUVERNOM = "SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE :recherche OR LOWER(company.name) LIKE :recherche OR introduced LIKE :recherche OR discontinued LIKE :recherche;";
	public static String MODIFIER = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :idCompany WHERE id = :idComputer";
	public static String EFFACER = "DELETE FROM computer WHERE id = :id";
	public static String EFFACERPARCOMPA = "DELETE FROM computer WHERE company_id = :id";
	public static String SELECTION = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id";
	public static String LIMIT = " LIMIT :offset, :limit";
	public static String ASCENDANT = " ASC";
	public static String DESCENDANT = " DESC";
	public static String ORDER = " ORDER BY ";

	public ComputerDAO(DataSource dataSource) {
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ArrayList<Computer> toutComputer() {
		return (ArrayList)namedParameterJdbcTemplate.query(SELECTION,new ComputerMapper());
	}
	
	public int count() {
		List<Computer> ListComputer= toutComputer();
		return ListComputer.size();
	}
	
	public ArrayList<Computer> pageComputer(int tri, String colonne, int limit, int offset) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("limit",limit)
				.addValue("offset",offset);
		String requete;
		if (tri==0||colonne==""||colonne==null) {
			requete = SELECTION + LIMIT;
		} else if (tri==1) {
				requete = SELECTION + ORDER + colonne + ASCENDANT + LIMIT;
			} else {
				requete = SELECTION + ORDER + colonne + DESCENDANT + LIMIT;
			}
		return (ArrayList)namedParameterJdbcTemplate.query(requete, namedParameters, new ComputerMapper());
	}
	
	public Computer trouverid (long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.queryForObject(TROUVERID, namedParameters, new ComputerMapper());
	}
	
	public ArrayList<Computer> trouvernom (String recherche) {
		recherche=recherche.toLowerCase();
		recherche="%"+recherche+"%";
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("recherche",recherche);
		return (ArrayList)namedParameterJdbcTemplate.query(TROUVERNOM, namedParameters, new ComputerMapper());
	}
	
	public int modifier(Computer comp) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name",comp.getName())
				.addValue("introduced",comp.getIntroduced())
				.addValue("name",comp.getName())
				.addValue("discontinued",comp.getDiscontinued())
				.addValue("idComputer", comp.getId())
				.addValue("idCompany", comp.getCompany().getId());
		return namedParameterJdbcTemplate.update(MODIFIER, namedParameters);
	}
	
	public int effacer(long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.update(EFFACER, namedParameters);
	}
	
	public int effacerComputParCompa(long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.update(EFFACERPARCOMPA, namedParameters);
	}
	
	public int creer(Computer comp) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name",comp.getName())
				.addValue("introduced",comp.getIntroduced())
				.addValue("name",comp.getName())
				.addValue("discontinued",comp.getDiscontinued())
				.addValue("idCompany", comp.getCompany().getId());
		return namedParameterJdbcTemplate.update(CREER, namedParameters);
	}
}