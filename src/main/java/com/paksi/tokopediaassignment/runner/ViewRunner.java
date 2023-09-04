package com.paksi.tokopediaassignment.runner;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class ViewRunner implements CommandLineRunner {
    public static final String VIEW_INIT_FILE = "after_hibernate_init.sql";
   @Autowired
   private DataSource dataSource;

   @Override
   public void run(String... arg) throws Exception {
      createSQLViews();
   }

   private void createSQLViews(){
      boolean IGNORE_FAILED_DROPS = true;
      ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, IGNORE_FAILED_DROPS , "UTF-8", new ClassPathResource(VIEW_INIT_FILE));
      resourceDatabasePopulator.execute(dataSource);
   }
}
