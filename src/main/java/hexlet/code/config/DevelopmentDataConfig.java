package hexlet.code.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("development")
public class DevelopmentDataConfig {

    @Bean
    public DataSource getDevDataSource() {
        System.out.println("***** IN MEMORY DB *****");
        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1")
                .password("sa")
                .password("");
        return dataSourceBuilder.build();
    }
}
