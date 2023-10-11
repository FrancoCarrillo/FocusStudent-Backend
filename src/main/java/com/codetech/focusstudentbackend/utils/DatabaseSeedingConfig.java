package com.codetech.focusstudentbackend.utils;

import com.codetech.focusstudentbackend.core.repositories.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DatabaseSeedingConfig {

    private final RolRepository rolRepository;

    @Bean
    public ResourceDatabasePopulator resourceDatabasePopulator(DataSource dataSource) {

        if (rolRepository.existsByName("TEACHER"))
            return null;

        ResourceDatabasePopulator databasePopular = new ResourceDatabasePopulator();
        Resource dataScript = new ClassPathResource("data.sql");
        databasePopular.addScript(dataScript);
        databasePopular.execute(dataSource);
        return databasePopular;
    }
}
