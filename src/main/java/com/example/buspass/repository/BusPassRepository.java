package com.example.buspass.repository;

import com.example.buspass.model.BusPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BusPassRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<BusPass> mapper = new RowMapper<>() {
        @Override
        public BusPass mapRow(ResultSet rs, int rowNum) throws SQLException {
            BusPass p = new BusPass();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setEmail(rs.getString("email"));
            p.setPassType(rs.getString("pass_type"));
            java.sql.Date sd = rs.getDate("start_date");
            java.sql.Date ed = rs.getDate("end_date");
            p.setStartDate(sd != null ? sd.toString() : null);
            p.setEndDate(ed != null ? ed.toString() : null);
            return p;
        }
    };

    public List<BusPass> findAll() {
        return jdbcTemplate.query("SELECT * FROM bus_pass ORDER BY id DESC", mapper);
    }

    public BusPass findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM bus_pass WHERE id = ?", mapper, id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public int save(BusPass p) {
        return jdbcTemplate.update(
                "INSERT INTO bus_pass(name, email, pass_type, start_date, end_date) VALUES (?, ?, ?, ?, ?)",
                p.getName(), p.getEmail(), p.getPassType(), p.getStartDate(), p.getEndDate()
        );
    }

    public int update(BusPass p) {
        return jdbcTemplate.update(
                "UPDATE bus_pass SET name = ?, email = ?, pass_type = ?, start_date = ?, end_date = ? WHERE id = ?",
                p.getName(), p.getEmail(), p.getPassType(), p.getStartDate(), p.getEndDate(), p.getId()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM bus_pass WHERE id = ?", id);
    }
}
