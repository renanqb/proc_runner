package br.com.poc.arq.procrun.proc_runner.entities.exceptions;

public class CommandNotConfiguredException extends Exception {

    private static final long serialVersionUID = -5344156066631371242L;

    public CommandNotConfiguredException() {
        super("-command não foi encontrado na parametrização");
    }
}