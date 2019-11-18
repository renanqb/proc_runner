package br.com.poc.arq.procrun.proc_runner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.poc.arq.procrun.proc_runner.data.ProcRunnerDao;
import br.com.poc.arq.procrun.proc_runner.entities.ProcRunner;

@Component
public class ProcRunnerServiceImpl implements ProcRunnerService {

    @Autowired
    private ProcRunnerDao procRunnerDao;

    public int execute(String... args) throws Exception {

        ProcRunner proc = new ProcRunner(args);

        try {

            proc.validate();
            procRunnerDao.execute(proc);
            
            return 0;
        } catch (Exception ex) {

            // LOG EXCEPTION IN SPLUNK
            System.out.println(ex.getMessage());

            if (proc.isSilent()) {
                return 0;
            }
            return -1;
        }
    }
}
