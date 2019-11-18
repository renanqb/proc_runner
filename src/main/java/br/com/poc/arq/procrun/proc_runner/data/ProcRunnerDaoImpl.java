package br.com.poc.arq.procrun.proc_runner.data;

import java.sql.CallableStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.poc.arq.procrun.proc_runner.entities.ProcRunner;

@Component
public class ProcRunnerDaoImpl implements ProcRunnerDao {

    @Autowired
    private Environment env;

    public void execute(final ProcRunner proc) throws SQLException {

        DataSource db = ConnectionFactory.build(proc.getDriver(), this.env);
        CallableStatement executor = db.getConnection().prepareCall(proc.getCommand());
        executor.setQueryTimeout(ConnectionFactory.queryTimeout);
        executor.execute();
    }
}
