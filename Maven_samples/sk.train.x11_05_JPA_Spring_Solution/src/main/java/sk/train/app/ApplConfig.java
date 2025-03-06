package sk.train.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sk.train.dao.EmpService;

@Configuration
@EnableTransactionManagement
public class ApplConfig {



	// Erweiterte Entity-Manager-Factory bereit stellen
	// LocalContainerEntityManagerFactoryBean ist eine Factory für die Factory!!
	// Erweiterung der LocalEntityManagerFactoryBean
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean fb = new LocalContainerEntityManagerFactoryBean();
		fb.setPersistenceUnitName("Muster_JPA_Hibernate_H2_Local");
		return fb;
	}

	
	// Transaktions-Unterstützung bereit stellen
	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(this.getEntityManagerFactory().getObject());
		return manager;
	}
	
	
//	@Bean
//	public TransactionTemplate transactionTemplate() {
//		return new TransactionTemplate(this.transactionManager());
//	}


	// EmpService bereit stellen
	@Bean
	public EmpService getEmpService() {
		return new EmpService();
	}
}
