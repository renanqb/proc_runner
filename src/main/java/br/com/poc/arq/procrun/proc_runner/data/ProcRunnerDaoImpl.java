package br.com.poc.arq.procrun.proc_runner.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLType;

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
        
        CallableStatement executor = 
            db.getConnection().prepareCall("{ CALL " + proc.getCommand() + " }");
        executor.setQueryTimeout(ConnectionFactory.queryTimeout);
        
        executor.setString("n1", "1");
        executor.setString("n2", "2");
        executor.registerOutParameter("res", java.sql.Types.INTEGER);
        executor.execute();

        System.out.println("OUTPUT: " + executor.getInt("res"));
    }
}
