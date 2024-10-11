package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PendingUser;
import com.techelevator.model.dto.SubmitPendingUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPendingUserDao implements PendingUserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPendingUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PendingUser getApplicantById(int pendingId) {
        PendingUser pendingUser = null;
        String sql = "SELECT * FROM pending_users WHERE pending_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pendingId);
            if (results.next()) {
                pendingUser = mapRowToPendingUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pendingUser;
    }

    @Override
    public PendingUser getApplicantByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email cannot be null");
        PendingUser pendingUser = null;
        String sql = "SELECT * FROM pending_users WHERE email = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, email);
            if (rowSet.next()) {
                pendingUser = mapRowToPendingUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return pendingUser;
    }

    @Override
    public List<PendingUser> getPendingApplicants() {
        List<PendingUser> applicantList = new ArrayList<>();

        String sql = "SELECT * FROM pending_users WHERE status = 1;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while ( rowSet.next() ) {
                PendingUser pendingUser = mapRowToPendingUser(rowSet);
                applicantList.add(pendingUser);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applicantList;
    }

    @Override
    public List<PendingUser> getAllApplicants() {
        List<PendingUser> applicantList = new ArrayList<>();

        String sql = "SELECT * FROM pending_users;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while ( rowSet.next() ) {
                PendingUser pendingUser = mapRowToPendingUser(rowSet);
                applicantList.add(pendingUser);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return applicantList;
    }

    @Override
    public void updateApplication(int statusId, int pendingId) {
        String sql = "UPDATE pending_users SET status = ? WHERE pending_id = ?;";
        try {
            jdbcTemplate.update(sql, statusId, pendingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public PendingUser createPendingUser(SubmitPendingUserDto applicant) {
        PendingUser newPendingUser = null;
        String insertPendingUserSql = "INSERT INTO pending_users (first_name, last_name, phone_number, " +
                "email, birthday, description, status) values (?, ?, ?, ?, ?, ?, ?) RETURNING pending_id";
        try {
            int newPendingId = jdbcTemplate.queryForObject(insertPendingUserSql, int.class,
                    applicant.getFirstName(), applicant.getLastName(), applicant.getPhoneNumber(),
                    applicant.getEmail(), applicant.getBirthday(), applicant.getDescription(), 1);
            newPendingUser = getApplicantById(newPendingId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPendingUser;
    }

    private PendingUser mapRowToPendingUser(SqlRowSet rs) {
        PendingUser pendingUser = new PendingUser();
        pendingUser.setPendingId(rs.getInt("pending_id"));
        pendingUser.setFirstName(rs.getString("first_name"));
        pendingUser.setLastName(rs.getString("last_name"));
        pendingUser.setPhoneNumber(rs.getString("phone_number"));
        pendingUser.setEmail(rs.getString("email"));
        pendingUser.setBirthday(rs.getDate("birthday"));
        pendingUser.setDescription(rs.getString("description"));
        pendingUser.setStatus(rs.getInt("status"));
        return pendingUser;
    }
}
