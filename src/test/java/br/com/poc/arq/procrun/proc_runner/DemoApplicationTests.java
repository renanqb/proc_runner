package br.com.poc.arq.procrun.proc_runner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.poc.arq.procrun.proc_runner.data.ProcRunnerDao;
import br.com.poc.arq.procrun.proc_runner.data.ProcRunnerDaoImpl;
import br.com.poc.arq.procrun.proc_runner.entities.ProcRunner;

@SpringBootTest
@Import(HsqlDbSetup.class)
class DemoApplicationTests {

	@Autowired
	ProcRunnerDao dao;

	@Test
	void contextLoads() {
		
	}

	@Test
	void executeProcedureSP_NEWPERSON_Test() throws SQLException {

		String[] args = new String[3];
		args[0] = "-driver=mssql";
		args[1] = "-command=SP_NEW_PERSON(?, ?)";
		args[2] = "-silent=false";

		ProcRunner parms = new ProcRunner(args);

		dao.execute(parms);

		assertTrue(true);
	}
}
