package com.learn.authorization.repository;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import javax.sql.*;

/**
 * @author Krishna Chaitanya
 */
@Component
public final class ApplicationRepository {

    private JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    public ApplicationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate == null ? new JdbcTemplate(dataSource) : this.jdbcTemplate;
    }

}
