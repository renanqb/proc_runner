package br.com.poc.arq.procrun.proc_runner;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@TestConfiguration
public class HsqlDbSetup {

    @Autowired
    private Environment env;

    //tests specific beans
    @Bean
    void createDataSource() throws SQLException {
        createTablePerson();
    }

    public void createTablePerson() throws SQLException {

        System.out.println("-> HSQLDB In-Memory Database setup...");
        String createTablePerson = "CREATE TABLE PERSON(FIRST_NAME VARCHAR(50), LAST_NAME VARCHAR(50))";

        String createProcedureNewPerson = 
            "CREATE PROCEDURE SP_NEW_PERSON(FIRSTNAME VARCHAR(50), LASTNAME VARCHAR(50)) " 
            + "MODIFIES SQL DATA " 
                + "INSERT INTO PERSON VALUES FIRSTNAME, LASTNAME;";

        try (Connection connection = getDBConnection()) {
			
			connection.setAutoCommit(false);

            PreparedStatement tablePerson = connection.prepareStatement(createTablePerson);
            tablePerson.executeUpdate();
            tablePerson.close();

            System.out.println("\t -> HSQLDB Create table PERSON...");

            PreparedStatement procedureNewPerson = connection.prepareStatement(createProcedureNewPerson);
            procedureNewPerson.executeUpdate();
            procedureNewPerson.close();

            System.out.println("\t -> HSQLDB Create procedure SP_NEW_PERSON...");

            connection.commit();

            System.out.println("\t -> HSQLDB Commiting changes...");
        } catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
    }

	private Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(env.getProperty("mssql.driverClassName"));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {

            dbConnection = DriverManager.getConnection(
                env.getProperty("mssql.url"), 
                env.getProperty("mssql.username"), 
                env.getProperty("mssql.password"));

            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}