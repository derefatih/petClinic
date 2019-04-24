package com.javaegitimleri.petclinic.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.model.Owner;

@Repository
public class OwnerRepositoryJdbcImpl implements OwnerRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Owner> rowMapper = new RowMapper<Owner>() {

		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Owner owner = new Owner();
			owner.setId(rs.getLong("id"));
			owner.setFirstName(rs.getString("first_name"));
			owner.setLastName(rs.getString("last_name"));

			return owner;
		}
	};
	
	@Override
	public List<Owner> findAll() {
		String sql = "select id, first_name, last_name from t_owner";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Owner findById(Long id) {
		String sql = "select id, first_name, last_name from t_owner where id=?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper, id));
	}

	@Override
	public List<Owner> findByLastname(String lastName) {
		String sql = "select id,first_name,last_name from t_owner where last_name like :lastName";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lastName", "%"+lastName+"%");
		return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
	}

	@Override
	public List<Owner> findByFirstname(String firstName) {
		String sql = "select id,first_name,last_name from t_owner where upper(first_name) like upper(?)";
		return jdbcTemplate.query(sql, rowMapper, "%" + firstName + "%");
	}

	@Override
	public void create(Owner owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		String sql="delete from t_owner where id =?";
		
		jdbcTemplate.update(sql, id);

	}

}
