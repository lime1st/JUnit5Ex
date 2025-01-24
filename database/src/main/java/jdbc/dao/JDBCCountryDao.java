package jdbc.dao;

import jdbc.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jdbc.ConnectionManager.closeConnection;
import static jdbc.ConnectionManager.openConnection;

public class JDBCCountryDao {

    private static final String GET_ALL_COUNTRIES_SQL =
            "select * from country";
    private static final String GET_COUNTRIES_BY_NAME_SQL =
            "select * from country where name like ?";

    public List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<>();

        try {
            Connection connection = openConnection();
            PreparedStatement statement =
                    connection.prepareStatement(GET_ALL_COUNTRIES_SQL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                countryList.add(new Country(rs.getString(2), rs.getString(3)));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return countryList;
    }

    public List<Country> getCountryListStartWith(String name) {
        List<Country> countryList = new ArrayList<>();

        try {
            Connection connection = openConnection();
            PreparedStatement statement =
                    connection.prepareStatement(GET_COUNTRIES_BY_NAME_SQL);
            statement.setString(1, name + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                countryList.add(new Country(rs.getString(2), rs.getString(3)));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return countryList;
    }
}