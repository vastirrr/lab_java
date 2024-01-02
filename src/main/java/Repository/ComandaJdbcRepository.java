package Repository;

import Domain.Comanda;
import Domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ComandaJdbcRepository extends MemoryRepository<Comanda> {
    private String JDBC_URL = "jdbc:sqlite:practic.sqlite";
    Connection connection;
    public ComandaJdbcRepository() {
        openConnection();
        createTable();
        //      initData();
        loadDataInMemory();
    }

    private void openConnection(){
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try{

            if(connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createTable() {
        try(final Statement st = connection.createStatement()){
            st.execute("CREATE TABLE IF NOT EXISTS comenzi(id int, listId nvarchar(50), dataComanda Date);");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void initData(){
        //schimba Entity cu noua entitate!! TOT!!
        List<Comanda> comandas = new ArrayList<>();
        comandas.add(new Comanda(1, new ArrayList<Integer>(Arrays.asList(8,9,10)), new Date(2023-1900,11,11)));
        comandas.add(new Comanda(2,  new ArrayList<Integer>(Arrays.asList(9,10)), new Date(2023-1900,11,11)));
        comandas.add(new Comanda(3,  new ArrayList<Integer>(Arrays.asList(8)), new Date(2023-1900,10,13)));

        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO comenzi VALUES (?,?,?)")){
            for(Comanda comanda: comandas){
                statement.setInt(1, comanda.getID());
                String torturiId = "";
                for(Integer tortId : comanda.getTorturi()){
                    torturiId = torturiId + tortId + ",";
                }
                if(torturiId.length() > 0){
                    torturiId = torturiId.substring(0,torturiId.length()-1);
                }
                statement.setString(2, torturiId);
                statement.setDate(3, new java.sql.Date(comanda.getData().getTime()));
                statement.executeUpdate();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void loadDataInMemory(){
        for (Comanda c: getAll()){
            try {
                super.add(c);
            } catch (DuplicateEntityException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Comanda> getAll(){
        List<Comanda> comandas = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM comenzi")){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                List<Integer> torturiId = new ArrayList<>();
                for(int i = 0; i < rs.getString(2).split(",").length; i++){
                    torturiId.add(Integer.parseInt(rs.getString(2).split(",")[i]));
                }
                comandas.add(new Comanda(rs.getInt(1), torturiId, rs.getDate(3)) {
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (ArrayList<Comanda>) comandas;
    }

    public void add(Comanda e){
        try {
            super.add(e);
        } catch (DuplicateEntityException ex) {
            throw new RuntimeException(ex);
        }
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO comenzi VALUES (?,?,?)")){
            statement.setInt(1, e.getID());
            String torturiId = "";
            for(Integer tortId : e.getTorturi()){
                torturiId = torturiId + tortId + ",";
            }
            if(torturiId.length() > 0){
                torturiId = torturiId.substring(0,torturiId.length()-1);
            }
            statement.setString(2, torturiId);
            statement.setDate(3, new java.sql.Date(e.getData().getTime()));

            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void remove(int id){
        super.remove(id);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("DELETE FROM comenzi WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Comanda e ){

        super.update(e);
        try (final Statement stms = connection.createStatement()) {
            String torturiId = "";
            for(Integer tortId : e.getTorturi()){
                torturiId = torturiId + tortId + ",";
            }
            if(torturiId.length() > 0){
                torturiId = torturiId.substring(0,torturiId.length()-1);
            }
            java.sql.Date date =  new java.sql.Date(e.getData().getTime());
            stms.executeUpdate("UPDATE comenzi SET torturi = '" + torturiId + "', data = '" + date + "'+ WHERE id = " +  e.getID());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

