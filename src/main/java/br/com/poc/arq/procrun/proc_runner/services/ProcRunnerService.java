package br.com.poc.arq.procrun.proc_runner.services;

public interface ProcRunnerService {

    int execute(String... args) throws Exception;
}