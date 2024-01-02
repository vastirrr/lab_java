package Repository;

import Domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ai grija sa schimbi Entity!!!
public class TortJdbcRepository extends MemoryRepository<Tort> {
    private String JDBC_URL = "jdbc:sqlite:practic.sqlite";
    Connection connection;
    public TortJdbcRepository() {
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
            st.execute("CREATE TABLE IF NOT EXISTS torturi(id int, tip nvarchar(50));");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void initData(){
        //schimba Entity cu noua entitate!! TOT!!
        List<Tort> torturi = new ArrayList<>();
        torturi.add(new Tort(8, "ddd"));
        torturi.add(new Tort(9, "ffff"));
        torturi.add(new Tort(10, "ssss"));

        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO torturi VALUES (?,?)")){
            for(Tort tort: torturi){
                statement.setInt(1, tort.getID());
                statement.setString(2, tort.getTip());
                statement.executeUpdate();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void loadDataInMemory(){
        for (Tort c: getAll()){
            try {
                super.add(c);
            } catch (DuplicateEntityException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Tort> getAll(){
        List<Tort> torturi = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM torturi")){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                torturi.add(new Tort(rs.getInt(1), rs.getString(2)) {
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (ArrayList<Tort>) torturi;
    }

    public void add(Tort e){
        try {
            super.add(e);
        } catch (DuplicateEntityException ex) {
            throw new RuntimeException(ex);
        }
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO torturi VALUES (?,?)")){
            statement.setInt(1, e.getID());
            statement.setString(2, e.getTip());
            statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void remove(int id){
        super.remove(id);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("DELETE FROM torturi WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Tort e ){

        super.update(e);
        try (final Statement stms = connection.createStatement()) {
            stms.executeUpdate("UPDATE torturi SET marca = '" + e.getTip() + "' WHERE id = " +  e.getID());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
