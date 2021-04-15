package repository;

import config.DatabaseConfig;
import model.BankTableData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class AccountRepository implements Repository {
    private static AccountRepository INSTANCE;
    private AccountRepository(){}
    public static AccountRepository getInstance(){
        if( INSTANCE == null )
            INSTANCE = new AccountRepository();
        return INSTANCE;
    }

    @Override
    public void save(BankTableData row){
        Connection connection = null;
        Statement statement = null;

        Object[] values = row.entrySet().stream()
                .map(Map.Entry::getValue).toArray(Object[]::new);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            connection = DriverManager.getConnection(
                    DatabaseConfig.getDatabaseUrl(), DatabaseConfig.getUSER(), DatabaseConfig.getPASSWORD());
            statement = connection.createStatement();
            String sql = "INSERT INTO accounts " +
                    "VALUES (" + values[0] + ", " + values[1] + ", ' " + dateFormat.format(values[2]) + " ',"  + values[3] +")";
            statement.executeUpdate(sql);

            statement.close();
            connection.close();
        } catch(SQLException se){
            se.printStackTrace();
        }finally{
            try{
                if(statement!=null)
                    statement.close();
            }catch(SQLException se2){
            }
            try{
                if(connection!=null)
                    connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }


        System.out.println("SAVING:\t" + row);
    }
}
