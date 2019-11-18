package br.com.poc.arq.procrun.proc_runner.entities.enums;

public enum DatasourceEnum {
    NONE("none"),
    MSSQL("mssql"),
    SAPHANA("saphana");

    private String dataSourceName;
 
    DatasourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
 
    public String getDataSourceName() {
        return dataSourceName;
    }
}