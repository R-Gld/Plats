package fr.Rgld_.Plats.Storage;

import fr.Rgld_.Plats.Main;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Data {

    private final Main main;
    private final File file;
    private final File folder;

    public Data(Main main) {
        this.main = main;
        folder = new File("/etc/Plats");
        file = new File(folder, "data.sqlite");
    }

    private Connection connect() throws SQLException, ClassNotFoundException {
        try {
            if(folder.mkdirs() || file.createNewFile()) {
                System.out.println("An error occur during the connection to the local database.");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath().replace("\\", File.separator));
    }

    public File getFile() {
        return file;
    }

    private void createTable(String name) {
        try(Connection connection = connect()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + name + "(id integer PRIMARY KEY,ip text NOT NULL,stats text NOT NULL, date integer);";
            connection.createStatement().execute(sql);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void putStats(String table, String ip, String stats) {
        if(!isThisTableExists(table))
            createTable(table);

        try(Connection connection = connect()) {
            String sql;
            PreparedStatement psst;
            if(isThisServerIsInDB(ip)) {
                sql = MessageFormat.format("UPDATE {0} SET stats = ?, date= ? WHERE ip = ?", table);
                psst = connection.prepareStatement(sql);
                psst.setString(1, stats);
                psst.setLong(2, new Date().getTime());
                psst.setString(3, ip);
            } else {
                sql = MessageFormat.format("INSERT INTO {0}(ip, stats, date) VALUES(?,?,?);", table);
                psst = connection.prepareStatement(sql);
                psst.setString(1, ip);
                psst.setString(2, stats);
                psst.setLong(3, new Date().getTime());
            }
            psst.executeUpdate();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Collection<String> getAllTheTables() {
        try (Connection connection = connect()) {
            String sql = "SELECT name FROM sqlite_schema WHERE type='table' ORDER BY name;";
            PreparedStatement psst = connection.prepareStatement(sql);
            ResultSet rs = psst.executeQuery();
            ArrayList<String> tables = new ArrayList<>();
            while(rs.next()) {
                tables.add(rs.getString("name"));
            }
            return tables;
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean isThisTableExists(String table) {
        return getAllTheTables().contains(table);
    }

    public boolean isThisServerIsInDB(String ip) { // TODO fill the isThisServerIsInDB
        try (Connection connection = connect()) {
            String sql = "SELECT ";
            PreparedStatement psst = connection.prepareStatement(sql);
            ResultSet rs = psst.executeQuery();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

}
