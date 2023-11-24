package UI;

import Domain.Car;
import Domain.CarFactory;
import Domain.Inchiriere;
import Repository.BinaryRepository;
import Repository.FileRepository;
import Service.ServiceCar;
import Service.ServiceInchiriere;
import Repository.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
class ConsoleTest {
    Car car;
    Inchiriere inchiriere;
    MemoryRepository<Car> repoCar;
    MemoryRepository<Inchiriere> repoInchiriere;

    @BeforeEach
    void setUp() {
        repoCar = new MemoryRepository<>();
        repoInchiriere = new MemoryRepository<>();
    }

    @Test
    void creareEntitate() {
        CarFactory pf = new CarFactory();
        Car p = pf.createEntity("1,bmw,e30");
        assert p.getID() == 1;
        assert Objects.equals(p.getMarca(), "bmw");
        assert Objects.equals(p.getModel(), "e30");
    }

    @Test
    void getMarca() {
        Car p = new Car(1, "bmw", "e30");
        assert Objects.equals(p.getMarca(), "bmw");
    }

    @Test
    void getModel() {
        Car p = new Car(1, "bmw", "e30");
        assert Objects.equals(p.getModel(), "e30");
    }


    @Test
    void getCar() throws ParseException {

        car = new Car(1, "bmw", "e30");
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse("11/11/2011");
        Date date2 = new SimpleDateFormat("dd/mm/yyyy").parse("12/11/2011");

        Inchiriere p = new Inchiriere(1, car, date1, date2);
        assert Objects.equals(p.getCar(), car);
    }

    @Test
    void getStartDate() throws ParseException {

        car = new Car(1, "bmw", "e30");
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse("11/11/2011");
        Date date2 = new SimpleDateFormat("dd/mm/yyyy").parse("12/11/2011");

        Inchiriere p = new Inchiriere(1, car, date1, date2);
        assertEquals(date1, p.getStartDate());
    }

    @Test
    void getEndDate() throws ParseException {

        car = new Car(1, "bmw", "e30");
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse("11/11/2011");
        Date date2 = new SimpleDateFormat("dd/mm/yyyy").parse("13/11/2011");

        Inchiriere p = new Inchiriere(1, car, date1, date2);
        assertEquals(date2, p.getEndDate());
    }

    @Test
    void getModeli() {
        Car p = new Car(1, "bmw", "e30");
        assert Objects.equals(p.getModel(), "e30");
    }

    @Test
    void setMarcai() {
        Car p = new Car(1, "bmw", "e30");

        p.setMarca("bmw");
        assert Objects.equals(p.getMarca(), "bmw");
    }

    @Test
    void setModel() {
        Car p = new Car(1, "bmw", "e30");

        p.setModel("e30");
        assert Objects.equals(p.getModel(), "e30");
    }

    @Test
    void addC() throws DuplicateEntityException, RepositoryException {
        assert (repoCar.getAll().isEmpty());
        Car car1 = new Car(1, "BMW", "E60");
        Car car2 = new Car(2, "BMW", "E60");
        repoCar.add(car1);
        assert (repoCar.getAll().size() == 1);
        repoCar.add(car2);
        assert (repoCar.getAll().size() == 2);
    }

    @Test
    void addInchiriere() throws DuplicateEntityException, RepositoryException {
        assert (repoCar.getAll().isEmpty());
        Car car1 = new Car(1, "BMW", "E60");
        Car car2 = new Car(2, "BMW", "E60");
        repoCar.add(car1);
        repoCar.add(car2);

        assert (repoInchiriere.getAll().isEmpty());
        Date startDate = new Date(2003, 03, 10);
        Date endDate = new Date(2003, 03, 13);
        repoInchiriere.add(new Inchiriere(1, car1, startDate, endDate));
        assert (repoInchiriere.getAll().size() == 1);
    }

    @Test
    void updateInchiriere() throws DuplicateEntityException, RepositoryException {
        Car car1 = new Car(1, "BMW", "E60");
        repoCar.add(new Car(1, "BMW", "E60"));
        repoCar.add(new Car(2, "BM", "E60"));
        repoCar.update(1, "BMW", "E90");

        Date startDate = new Date(2003, 03, 10);
        Date endDate = new Date(2003, 03, 13);
        repoInchiriere.add(new Inchiriere(1, car1, startDate, endDate));
        assert (repoInchiriere.getAll().size() == 1);
        startDate = new Date(2003, 03, 1);
        assert (repoInchiriere.getAll().size() == 1);
    }

    @Test
    void deleteCar() throws DuplicateEntityException, RepositoryException {
        Car car1 = new Car(1, "BMW", "E60");
        repoCar.add(new Car(1, "BMW", "E60"));
        repoCar.add(new Car(2, "BMW", "E60"));
        repoCar.remove(1);
        assert repoCar.getAll().size() == 1;
        repoCar.remove(1);
        assert (repoCar.getAll().size() == 1);
    }

    @Test
    void deleteInchiriere() throws DuplicateEntityException, RepositoryException {
        Car car1 = new Car(1, "BMW", "E60");
        repoCar.add(new Car(1, "BMW", "E60"));
        repoCar.add(new Car(2, "BMW", "E60"));

        Date startDate = new Date(2003, 03, 10);
        Date endDate = new Date(2003, 03, 13);
        repoInchiriere.add(new Inchiriere(1, car1, startDate, endDate));
        repoInchiriere.remove(2);
        assert (repoInchiriere.getAll().size() == 1);
        repoInchiriere.remove(1);
        assert (repoInchiriere.getAll().isEmpty());
    }


    @Before
    public void setUp2() throws DuplicateEntityException, FileNotFoundException, RepositoryException {
    }

    @Test
    public void testAddCar() throws DuplicateEntityException, RepositoryException {
        try {
            MemoryRepository<Car> repo = new MemoryRepository<>();
            ServiceCar serviceCar = new ServiceCar(repo);

            Car c = new Car(9, "bmw", "e30");
            serviceCar.addCar(9, "bmw", "e30");
            ArrayList<Car> cars = (ArrayList<Car>) serviceCar.repoCar.getAll();
            assert cars.get(0).getID() == c.getID();
        } catch (DuplicateEntityException | RepositoryException e) {
            assert false;
        }
    }
}

//    @Test
//    public void testAddDuplicateCar() throws DuplicateEntityException,RepositoryException {
//
//        serviceCar.addCar(2, "Honda", "Accord");
//        serviceCar.addCar(2, "Honda", "Accord");
//    }
//
//    @Test
//    public void testReadCar() throws DuplicateEntityException, RepositoryException {
//        serviceCar.addCar(3, "Ford", "Fusion");
//        assertNotNull(serviceCar.readCar(3));
//    }
//
//    @Test
//    public void testReadNonexistentCar() {
//        serviceCar.readCar(999);
//    }
//
//    @Test
//    public void testUpdateCar() throws DuplicateEntityException, RepositoryException {
//        serviceCar.addCar(4, "Chevrolet", "Malibu");
//        serviceCar.updateCar(4, "Chevrolet", "Impala");
//        Car updatedCar = serviceCar.readCar(4);
//        assertEquals("Impala", updatedCar.getModel());
//    }
//
//    @Test
//    public void testUpdateNonexistentCar() {
//        serviceCar.updateCar(999, "Unknown", "Model");
//    }
//
//    @Test
//    public void testDeleteCar() throws DuplicateEntityException, RepositoryException {
//        serviceCar.addCar(5, "Subaru", "Outback");
//        serviceCar.deleteCar(5);
//        assertNull(serviceCar.readCar(5));
//    }
//
//    @Test
//    public void testDeleteNonexistentCar() {
//        serviceCar.deleteCar(999);
//    }
//}
//
//
//
//
//
//
//
//
