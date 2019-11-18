package br.com.poc.arq.procrun.proc_runner.data;

import java.sql.SQLException;

import br.com.poc.arq.procrun.proc_runner.entities.ProcRunner;

public interface ProcRunnerDao {

    void execute(final ProcRunner proc) throws SQLException;
}