package com.example.flowerscalculatorapi.config.db;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@EnableAutoConfiguration
public class DataInitializer {

    private final DataSource dataSource;

    public DataInitializer(final DataSource dataSource){
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
                false,
                false,
                "UTF-8",
                new ClassPathResource("populate_inventory_db.sql")
        );
        resourceDatabasePopulator.execute(dataSource);
    }
}
