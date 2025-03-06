package app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import app.dao.EmpService;
import app.model.Employee;

@SpringBootTest
class AccessingDataJpaApplicationTests {

	@Test
	void contextLoads() {
	}


}