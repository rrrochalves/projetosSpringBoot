package com.alves.stw.adapters.out;

import com.alves.stw.domain.model.Champion;
import com.alves.stw.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Champion> rowMapper;

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = ((resultSet, rowNum) ->
            new Champion(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("role"),
                resultSet.getString("lore"),
                resultSet.getString("image_url")
            )
        );
    }

    @Override
    public List<Champion> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
    }

    @Override
    public Optional<Champion> findById(Long id) {
        String sql = "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List<Champion> champions = jdbcTemplate.query(sql, rowMapper, id);
        return champions.stream().findFirst();
    }
}
