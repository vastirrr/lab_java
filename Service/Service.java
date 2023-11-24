//package Service;
//import Domain.Car;
//import Domain.Inchiriere;
//import Repository.DuplicateEntityException;
//import Repository.IRepository;
//import Repository.RepositoryException;
//
//import java.util.Date;
//import java.util.Iterator;
//
//public class Service {
//    private IRepository<Car> repoCar;
//    private IRepository<Inchiriere> repoInchiriere;
//
//    public Service(IRepository<Car> carRepository, IRepository<Inchiriere> inchiriereRepository) throws DuplicateEntityException {
//        this.repoCar = carRepository;
//        this.repoInchiriere = inchiriereRepository;
//        initializeCars(); // Initialize some cars (if needed)
//    }
//
//    public Service(IRepository<Car> carRepo) {
//        this.repoCar = carRepo;
//    }
//
//    private void initializeCars() throws DuplicateEntityException {
//        // Add initial cars if needed
//        addCar(1, "BMW", "E60");
//        addCar(2, "BMW", "E46");
//        addCar(3, "Porsche", "Macan");
//        addCar(4, "Mercedes", "AMG GT 63");
//        addCar(5, "Dacia", "Logan");
//    }
//
//    public Iterator<Car> carIterator() {
//        return repoCar.iterator();
//    }
//
//    public Iterator<Inchiriere> inchiriereIterator() {
//        return repoInchiriere.iterator();
//    }
//
//    public void addCar(int ID, String marca, String model) throws DuplicateEntityException, RepositoryException {
//        Car car = new Car(ID, marca, model);
//        if (carExists(car))
//            throw new IllegalArgumentException("Car already exists!");
//        repoCar.add(car);
//    }
//
//    private Boolean carExists(Car car) {
//        return repoCar.findById(car.getID()) != null;
//    }
//
//    public void addInchiriere(int ID, int carID, Date startDate, Date endDate) throws DuplicateEntityException {
//        Car car = repoCar.findById(carID);
//        if (car == null)
//            throw new IllegalArgumentException("The specified car doesn't exist!");
//
//        Inchiriere inchiriere = new Inchiriere(ID, car, startDate, endDate);
//        validateInchiriere(inchiriere);
//        repoInchiriere.add(inchiriere);
//    }
//
//    private void validateInchiriere(Inchiriere inchiriere) {
//        if (repoInchiriere.findById(inchiriere.getID()) != null)
//            throw new IllegalArgumentException("There exists another rental with this ID!");
//        if (inchiriere.getStartDate().compareTo(inchiriere.getEndDate()) > 0)
//            throw new IllegalArgumentException("Start date cannot be after end date!");
//        if (!carIsFree(inchiriere.getStartDate(), inchiriere.getEndDate(), inchiriere.getCar()))
//            throw new IllegalArgumentException("Car is not free!");
//    }
//
//    private Boolean carIsFree(Date startDate, Date endDate, Car car) {
//        for (Inchiriere inchiriere : repoInchiriere.getAll()) {
//            if (inchiriere.getCar().equals(car)) {
//                if (inchiriere.getEndDate().compareTo(startDate) > 0)
//                    return false;
//                if (inchiriere.getStartDate().compareTo(endDate) < 0)
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    public Car readCar(int ID) {
//        Car car = repoCar.findById(ID);
//        if (car == null)
//            throw new IllegalArgumentException("Specified ID doesn't exist!");
//        return car;
//    }
//
//    public Inchiriere readInchiriere(int ID) {
//        Inchiriere inchiriere = repoInchiriere.findById(ID);
//        if (inchiriere == null)
//            throw new IllegalArgumentException("Specified ID doesn't exist!");
//        return inchiriere;
//    }
//
////    public void updateCar(int ID, String marca, String model) {
////        Car car = new Car(ID, marca, model);
////        if (!carExists(car))
////            throw new IllegalArgumentException("Car with specified ID doesn't exist!");
////        repoCar.update(car);
////    }
//
//    public void updateInchiriere(int ID, int carID, Date startDate, Date endDate) throws DuplicateEntityException {
//        Car car = repoCar.findById(carID);
//        if (car == null)
//            throw new IllegalArgumentException("The specified car doesn't exist!");
//
//        Inchiriere thisInchiriere = new Inchiriere(ID, car, startDate, endDate);
//        Inchiriere old = repoInchiriere.findById(ID);
//        if (old == null)
//            throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
//        repoInchiriere.remove(ID);
//
//        try {
//            validateInchiriere(thisInchiriere);
//            repoInchiriere.add(thisInchiriere);
//        } catch (IllegalArgumentException | DuplicateEntityException exception) {
//            repoInchiriere.add(old);
//            throw exception;
//        }
//    }
//
//    public void deleteCar(int ID) {
//        if (repoCar.findById(ID) == null)
//            throw new IllegalArgumentException("Car with specified ID doesn't exist!");
//        repoCar.remove(ID);
//    }
//
//    public void deleteInchiriere(int ID) {
//        if (repoInchiriere.findById(ID) == null)
//            throw new IllegalArgumentException("Rental with specified ID doesn't exist!");
//        repoInchiriere.remove(ID);
//    }
//}
//
