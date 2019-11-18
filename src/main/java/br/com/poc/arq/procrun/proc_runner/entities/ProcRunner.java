package br.com.poc.arq.procrun.proc_runner.entities;

import br.com.poc.arq.procrun.proc_runner.entities.enums.DatasourceEnum;
import br.com.poc.arq.procrun.proc_runner.entities.exceptions.CommandNotConfiguredException;
import br.com.poc.arq.procrun.proc_runner.entities.exceptions.DatasourceNotConfiguredException;

public class ProcRunner {

    private DatasourceEnum driver;
    private String command;
    private boolean silent;

    public ProcRunner(final String... args) {

        for (final String p : args) {

            final String[] keyValue = p.split("=");
            final String key = keyValue[0];
            final String value = keyValue.length < 2 ? null : keyValue[1].toUpperCase();

            switch (key) {

                case "-driver":
                    this.driver = DatasourceEnum.valueOf(value);
                    break;
                case "-command":
                    this.command = value;
                    break;
                case "-silent":
                    this.silent = Boolean.valueOf(value);
                    break;
            }
        }
    } 

    public DatasourceEnum getDriver() {
        return this.driver;
    }

    public String getCommand() {
        return this.command;
    }

    public boolean isSilent() {
        return this.silent;
    }

    public void validate() throws DatasourceNotConfiguredException, CommandNotConfiguredException {

        if (getDriver() == DatasourceEnum.NONE) {
            throw new DatasourceNotConfiguredException();
        }

        if (getCommand().isEmpty()) {
            throw new CommandNotConfiguredException();
        }
    }
}