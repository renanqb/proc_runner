package br.com.poc.arq.procrun.proc_runner.entities.exceptions;

public class DatasourceNotConfiguredException extends Exception {

    private static final long serialVersionUID = 3627738394303246287L;

    public DatasourceNotConfiguredException() {
        super("-driver não foi encontrado na parametrização");
    }
}