package Repository;

import Domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ai grija sa schimbi Entity!!!
public class JdbcRepository extends MemoryRepository<Tort> {
    private String JDBC_URL = "jdbc:sqlite:practic.sqlite";
    Connection connection;
    public JdbcRepository() {
        openConnection();
        createTable();
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
//            st.execute("CREATE TABLE IF NOT EXISTS cars(id int, marca nvarchar(50), model nvarchar(50));");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void initData(){
        //schimba Entity cu noua entitate!! TOT!!
        List<Tort> cars = new ArrayList<>();
//        cars.add(new Car(8, "kia", "a3"));
//        cars.add(new Car(9, "lexus", "10"));
//        cars.add(new Car(10, "alfa", "gulia"));

//        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO cars VALUES (?,?,?)")){
//            statement.execute("CREATE TABLE IF NOT EXISTS car(id int, marca nvarchar(50), model nvarchar(50));");
//            for(Car car: cars){
//                statement.setInt(1, car.getID());
//                statement.setString(2, car.getMarca());
//                statement.setString(3, car.getModel());
//                statement.executeUpdate();
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
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
        List<Tort> cars = new ArrayList<>();
//        cars.add(new Car(8, "kia", "a3"));
//        cars.add(new Car(9, "lexus", "10"));
//        cars.add(new Car(10, "alfa", "gulia"));

        //schimba cars!!!
//        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars")){
//            ResultSet rs = statement.executeQuery();
//            while(rs.next()){
//                cars.add(new Entity(rs.getInt(1), rs.getString(2), rs.getString(3)) {
//                });
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        return (ArrayList<Tort>) cars;
    }

    public void add(Tort e){
        try {
            super.add(e);
        } catch (DuplicateEntityException ex) {
            throw new RuntimeException(ex);
        }
//        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO cars VALUES (?,?,?)")){
//            statement.setInt(1, e.getID());
//            statement.setString(2, e.getMarca());
//            statement.setString(3, e.getModel());
//            statement.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
    }

    public void remove(int id){
        super.remove(id);
        try (final Statement stms = connection.createStatement()) {
           // stms.executeUpdate("DELETE FROM cars WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Tort e ){

        super.update(e);
        try (final Statement stms = connection.createStatement()) {
//            stms.executeUpdate("UPDATE cars SET marca = '" + e.getMarca() + "', model = " + e.getModel() +
//                    " WHERE id = " +  e.getID());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
