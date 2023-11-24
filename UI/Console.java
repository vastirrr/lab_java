package UI;

import Domain.Car;
import Domain.Inchiriere;
import Repository.DuplicateEntityException;
import Repository.RepositoryException;
import Service.ServiceCar;
import Service.ServiceInchiriere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Console {
    private ServiceCar serviceCar;
    private ServiceInchiriere serviceInchiriere;
    private Scanner scanner;

    public Console(ServiceCar serviceCar, ServiceInchiriere serviceInchiriere) {
        this.serviceCar = serviceCar;
        this.serviceInchiriere = serviceInchiriere;
        this.scanner = new Scanner(System.in);
    }

    public void start() throws DuplicateEntityException {

        while (true) {
                int option;
                menu();
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        try {
                            addCar();
                        } catch (DuplicateEntityException e) {
                            throw new RuntimeException(e);
                        } catch (RepositoryException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        addInchiriere();
                        break;
                    case 3:
                        listAllCars();
                        break;
                    case 4:
                        listAllInchirieri();
                        break;
                    case 5:
                        updateCar();
                        break;
                    case 6:
                        updateInchiriere();
                        break;
                    case 7:
                        deleteCar();
                        break;
                    case 8:
                        deleteInchiriere();
                        break;
                    case 0:
                        System.out.println("Exiting the Car Management App.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
    }

    private void menu() {
        System.out.println("Car Management Menu:");
        System.out.println("1. Add Car");
        System.out.println("2. Add Inchiriere");
        System.out.println("3. List all Cars");
        System.out.println("4. List all Inchirieri");
        System.out.println("5. Update Car");
        System.out.println("6. Update Inchiriere");
        System.out.println("7. Delete Car");
        System.out.println("8. Delete Inchiriere");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addCar() throws DuplicateEntityException, RepositoryException {
        try{
            System.out.println("Add a new car:");
            System.out.print("Enter ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();

            serviceCar.addCar(ID, marca, model);
            System.out.println("Car added successfully!");
        }
            catch (DuplicateEntityException e) {
        System.out.println("Error: Duplicate car ID. " + e.getMessage());}
            catch (RepositoryException e) {
        System.out.println("Error adding car: " + e.getMessage());}
            catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());}
    }



    public void addInchiriere() {
        System.out.println("Add a new Inchiriere:");
        System.out.print("Enter ID: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Start Date (dd/MM/yyyy): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter End Date (dd/MM/yyyy): ");
        String endDateStr = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            serviceInchiriere.addInchiriere(ID, carID, startDate, endDate);
            System.out.println("Inchiriere added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public void listAllCars() {
        Iterator<Car> cars = serviceCar.carIterator();
        System.out.println("List of Cars:");
        while (cars.hasNext()) {
            Car car = cars.next();
            System.out.println(car.getID() + ". " + car.getMarca() + " " + car.getModel());
        }
    }

    public void listAllInchirieri() {
        Iterator<Inchiriere> inchirieri = serviceInchiriere.inchiriereIterator();
        System.out.println("List of Inchirieri:");
        while (inchirieri.hasNext()) {
            Inchiriere inchiriere = inchirieri.next();
            System.out.println(
                    inchiriere.getID() + ". Car ID: " + inchiriere.getCar().getID() +
                            ", Start Date: " + inchiriere.getStartDate() +
                            ", End Date: " + inchiriere.getEndDate()
            );
        }
    }

    public void updateCar() {
        System.out.print("Enter Car ID to update: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter New Model: ");
        String model = scanner.nextLine();

        try {
            serviceCar.updateCar(ID, marca, model);
            System.out.println("Car updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateInchiriere() {
        System.out.print("Enter Inchiriere ID to update: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Car ID: ");
        int carID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Start Date (dd/MM/yyyy): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter New End Date (dd/MM/yyyy): ");
        String endDateStr = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            serviceInchiriere.updateInchiriere(ID, carID, startDate, endDate);
            System.out.println("Inchiriere updated successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCar() {
        System.out.print("Enter Car ID to delete: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        try {
            serviceCar.deleteCar(ID);
            System.out.println("Car deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteInchiriere() {
        System.out.print("Enter Inchiriere ID to delete: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        try {
            serviceInchiriere.deleteInchiriere(ID);
            System.out.println("Inchiriere deleted successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
