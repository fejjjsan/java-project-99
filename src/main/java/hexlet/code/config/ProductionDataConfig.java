package hexlet.code.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("production")
public class ProductionDataConfig {
    @Bean
    public DataSource getProdDataSource() {
        System.out.println("***** POSTGRESQL DB *****");
        final DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://@dpg-cmv7cdn109ks73b9ga60-a:5432/projectdb_f9w1")
                .username("fejjsan")
                .password("H5ZITOBQ4oo5yeupMCIkNsBUxHVZt63u");
        return dataSourceBuilder.build();
    }
}
