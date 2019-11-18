package br.com.poc.arq.procrun.proc_runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.poc.arq.procrun.proc_runner.services.ProcRunnerService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private ProcRunnerService procRunnerService;

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		
		LOG.info("->procRunner.run_in");
  
		procRunnerService.execute(args);
		
		LOG.info("-<procRunner.run_out");
	}

}
