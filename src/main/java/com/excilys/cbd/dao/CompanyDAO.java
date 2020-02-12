package com.excilys.cbd.dao;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.cbd.mapper.CompanyMapper;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

@Repository
public class CompanyDAO 
{
	private ComputerDAO computerDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = :id";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = :name";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static String EFFACER = "DELETE FROM company WHERE id = :id";
	
	public CompanyDAO(DataSource dataSource,ComputerDAO computerDao) {
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
		this.computerDao=computerDao;
	}
	
	public ArrayList<Company> toutCompany() {
		return (ArrayList)namedParameterJdbcTemplate.query(TOUTCOMPA,new CompanyMapper());
	}
	
	public Company trouverCompany (long id) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",id);
		return namedParameterJdbcTemplate.queryForObject(TROUVERCOMPAID, namedParameters, new CompanyMapper());
	}
	
	public Company trouverCompany(String name) {
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("name",name);
		return namedParameterJdbcTemplate.queryForObject(TROUVERCOMPANOM, namedParameters, new CompanyMapper());
	}
	
	public int effacer(long companyId) {
		computerDao.effacerComputParCompa(companyId);
		SqlParameterSource namedParameters  = new MapSqlParameterSource().addValue("id",companyId);
		return namedParameterJdbcTemplate.update(EFFACER,namedParameters);
	}
}