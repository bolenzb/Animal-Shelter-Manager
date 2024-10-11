package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.AdoptionApp;
import com.techelevator.model.PendingUser;
import com.techelevator.model.dto.AdoptAppStatusChangeDto;
import com.techelevator.model.dto.AdoptionAppDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAdoptionDao implements AdoptionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAdoptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAdoptionApp(AdoptionAppDto app) {

        String sql = "INSERT INTO applicant (pet_id, first_name, last_name, email, phone_number, applicant_status, response_one, response_two, response_three, response_four) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING applicant_id";

        try {
            int newApplicantId = jdbcTemplate.queryForObject(sql, Integer.class,
                     app.getPetId(), app.getFirstName(), app.getLastName(), app.getEmail(),
                    app.getPhoneNumber(), 1, app.isResponseOne(), app.isResponseTwo(), app.isResponseThree(), app.isResponseFour());
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public AdoptionApp getAdoptionAppByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email cannot be null");
        AdoptionApp adoptionApp = null;
        String sql = "SELECT * FROM applicant WHERE email = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, email);
            if (rowSet.next()) {
                adoptionApp = mapRowToAdoptionApp(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return adoptionApp;
    }

    @Override
    public List<AdoptionApp> getPendingAdoptionApps() {
        List<AdoptionApp> apps = new ArrayList<>();
        String sql = "SELECT * FROM applicant WHERE applicant_status = 1;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while ( rowSet.next() ) {
                AdoptionApp adoptionApp = mapRowToAdoptionApp(rowSet);
                apps.add(adoptionApp);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return apps;
    }

    @Override
    public void updateAdoptionApp(AdoptAppStatusChangeDto status) {
        String sql = "UPDATE applicant SET applicant_status = ? WHERE applicant_id = ?;";
        try {
            jdbcTemplate.update(sql, status.getStatusId(), status.getApplicantId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private AdoptionApp mapRowToAdoptionApp(SqlRowSet rs) {
        AdoptionApp adoptionApp = new AdoptionApp();
        adoptionApp.setApplicantId(rs.getInt("applicant_id"));
        adoptionApp.setPetId(rs.getInt("pet_id"));
        adoptionApp.setFirstName(rs.getString("first_name"));
        adoptionApp.setLastName(rs.getString("last_name"));
        adoptionApp.setEmail(rs.getString("email"));
        adoptionApp.setApplicantStatus(rs.getInt("applicant_status"));
        adoptionApp.setPhoneNumber(rs.getString("phone_number"));
        adoptionApp.setResponseOne(rs.getBoolean("response_one"));
        adoptionApp.setResponseTwo(rs.getBoolean("response_two"));
        adoptionApp.setResponseThree(rs.getBoolean("response_three"));
        adoptionApp.setResponseFour(rs.getBoolean("response_four"));

        return adoptionApp;
    }
}
