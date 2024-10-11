package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.PetImage;
import com.techelevator.model.dto.PetIdDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetImageJdbcDao implements PetImageDao {

    private final JdbcTemplate jdbcTemplate;

    public PetImageJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(PetImage petImage) {
        String sql = "INSERT INTO pet_image(pet_id, name, type, data) VALUES (?,?,?,?);";
        this.jdbcTemplate.update(sql, petImage.getPetId(), petImage.getName(), petImage.getType(), petImage.getData());
    }

    @Override
    public PetImage findByName(String name) {

        final String sql = "SELECT image_id, pet_id, name, type, data from pet_image WHERE name = ?";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new PetImage(
                        rs.getInt("image_id"),
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBytes("data")
                ),name
        ).get(0);

    }

    public List<String> retrieveImageNames() {

        final String sql = "SELECT name from pet_image;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<String> imageNames = new ArrayList<>();

        while(results.next()) {
            imageNames.add(results.getString("name"));
        }

        return imageNames;
    }

    @Override
    public List<String> retrieveAPetsImages(PetIdDto petId) {
        final String sql = "SELECT name from pet_image WHERE pet_id = ? ORDER BY name;";
        List<String> imageNames = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId.getPetId());
            while (results.next()) {
                imageNames.add(results.getString("name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return imageNames;
    }

    public void deleteImageByName( String name ) {
        String sql = "DELETE from pet_image WHERE name = ?;";

        try {
            jdbcTemplate.update(sql, name);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

}
