package appl;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource("classpath:db.properties")
public class ApplConfig {
	
	@Value("classpath:create.sql")
	private Resource schemaScript;

//	@Value("classpath:populate.sql")
//	private Resource dataScript;
	
	@Value("classpath:drop.sql")
	private Resource dropScript;

	// einfache DriverManagerDataSource nutzen
	@Bean
	public DataSource dataSource(@Value("${db.driver}") String driver, @Value("${db.url}") String url,
								 @Value("${db.user}") String user, @Value("${db.password}") String pass)
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pass);
		return dataSource;
	}
	
	@Bean
	@Autowired
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
	    final DataSourceInitializer initializer = new DataSourceInitializer();
	    initializer.setDataSource(dataSource);
	    initializer.setDatabasePopulator(databasePopulator());
	    initializer.setDatabaseCleaner(databaseCleaner());
	    return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(schemaScript);
	    //populator.addScript(dataScript);
	    return populator;
	}
	
	private DatabasePopulator databaseCleaner() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(dropScript);
	    populator.setIgnoreFailedDrops(true);  //somit könnte man mit einem Skript auskommen
	    return populator;
	}

}
