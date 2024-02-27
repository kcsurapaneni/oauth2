package com.learn.authorization.repository;

import com.learn.authorization.domain.User;
import com.learn.authorization.domain.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

/**
 * @author Krishna Chaitanya
 */
@Component
public class UserRepository {

    private static final String ACTIVE_USER_BY_USERNAME = """
            SELECT id, username, password FROM user WHERE username = ? AND is_active = 1
            """;

    private static final String ACTIVE_ROLES_BY_USERID = """
            SELECT name FROM role WHERE id IN (SELECT id FROM user_role WHERE user_id = ?) AND is_active = 1
            """;

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(ApplicationRepository applicationRepository) {
        this.jdbcTemplate = applicationRepository.getJdbcTemplate();
    }

    public UserDetails loadUserByUsername(final String username) {
        return this.jdbcTemplate.query(ACTIVE_USER_BY_USERNAME, this::mapToUser, username).get(0);
    }

    private User mapToUser(ResultSet rs, int rowNum) throws SQLException {
        var user = new User();
        user.setId(rs.getLong(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));
        user.setActive(true);

        var roles = this.jdbcTemplate.query(ACTIVE_ROLES_BY_USERID, this::mapToRole, user.getId());
        user.setRoles(new HashSet<>(roles));
        return user;
    }

    private Role mapToRole(ResultSet rs, int rowNum) throws SQLException {
        var role = new Role();
        role.setName(rs.getString(1));
        return role;
    }

}
