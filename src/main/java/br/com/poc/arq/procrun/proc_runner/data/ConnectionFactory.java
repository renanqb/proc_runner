package br.com.poc.arq.procrun.proc_runner.data;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.env.Environment;

import br.com.poc.arq.procrun.proc_runner.entities.enums.DatasourceEnum;

public class ConnectionFactory {

    public static int queryTimeout = 60;
    
    public static DataSource build(final DatasourceEnum driver, final Environment env) {

        final String driverProperty = String.format("%s.%s", driver.getDataSourceName(), "driverClassName");
        final String urlProperty = String.format("%s.%s", driver.getDataSourceName(), "url");
        final String usernameProperty = String.format("%s.%s", driver.getDataSourceName(), "username");
        final String passwordProperty = String.format("%s.%s", driver.getDataSourceName(), "password");
        final String timeoutProperty = String.format("%s.%s", driver.getDataSourceName(), "timeout");

        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty(driverProperty));
        dataSource.setUrl(env.getProperty(urlProperty));
        dataSource.setUsername(env.getProperty(usernameProperty));
        dataSource.setPassword(env.getProperty(passwordProperty));
        
        queryTimeout = Integer.parseInt(env.getProperty(timeoutProperty));

        return dataSource;
    }
}