package org.example.hw_12.config;

import org.example.hw_12.exceptions.NotFoundException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Optional;

public class JdbcUserDetailsService extends MappingSqlQuery<UserDetails>
        implements UserDetailsService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with name %s not found!";

    public JdbcUserDetailsService(DataSource ds) {
        super(ds,
                    """
                    select 
                    u.user_name,
                    u.password 
                    from hw_12.users u
                    where u.user_name = :username
                    """);
        this.declareParameter(new SqlParameter("username", Types.VARCHAR));
        this.compile();
    }

    @Override
    protected UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .username(rs.getString("user_name"))
                .password(rs.getString("password"))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(this.findObjectByNamedParam(Map.of("username", username)))
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE.formatted(username)));
    }
}
