package UI;

import Domain.BMI;
import Domain.BP;
import Service.ServiceBMI;
import Service.ServiceBP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Console {
    private ServiceBMI serviceBMI;
    private ServiceBP serviceBP;
    private Scanner scanner;

    public Console(ServiceBP serviceBP, ServiceBMI serviceBMI) {
        this.serviceBP = serviceBP;
        this.serviceBMI = serviceBMI;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        while (true) {
            int option;
            menu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    addBP();
                    break;
                case 2:
                    addBMI();
                    break;
                case 3:
                    listBP();
                    break;
                case 4:
                    listBMI();
                    break;
                case 5:
                    isHealtyPerson();
                    break;
                case 6:

                        showMonth();

                    break;
//                case 7:
//                    deletBP();
//                    break;
//                case 8:
//                    deleteBMI();
                //break;
                case 0:
                    System.out.println("Exiting Health Care Management App.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void menu() {
        System.out.println("Health Care Management Menu:");
        System.out.println("1. Add BP");
        System.out.println("2. Add BMI");
        System.out.println("3. List all BP");
        System.out.println("4. List all BMI");
        System.out.println("5. List healty/unhealty persons");
        System.out.println("6. Show by month persons");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addBMI() {
        try {
            System.out.println("Add a new BMI:");
            System.out.print("Enter date: ");
            String date = scanner.nextLine();
            System.out.print("Enter value: ");
            Float value = scanner.nextFloat();

            serviceBMI.addBMI(date, value);
            System.out.println("BMI added successfully!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    public void addBP() {
        System.out.println("Add a new BP:");
        System.out.print("Enter date: ");
        String date = scanner.nextLine();
        System.out.print("Enter systolic value: ");
        Integer systolicValue = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter diastolic value: ");
        Integer diastolicValue = scanner.nextInt();

        try {
            serviceBP.addBP(date, systolicValue, diastolicValue);
            System.out.println("BP added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listBP() {
        List<BP> bps = serviceBP.getAll();
        System.out.println("List of Blood Pressure:");
        for (BP bp : bps) {
            System.out.println("Date: " + bp.getDate() + "\nValue of arterial tension: systolic value:  "
                    + bp.getSystolicValue() + " and diastolic value:" + bp.getDiastolicValue());
        }
    }

    public void listBMI() {
        List<BMI> bmis = serviceBMI.getAll();
        System.out.println("List of Body mass index:");
        for (BMI bmi : bmis) {
            System.out.println("Date: " + bmi.getDate() + " \nbody mass value:  " + bmi.getValue());
        }
    }

    public void isHealtyPerson() {
        List<BP> bps = serviceBP.getAll();
        List<BMI> bmis = serviceBMI.getAll();
        int ok = 0;


        for (int i = 0; i < bps.size(); i++)
            for (int j = 0; j < bmis.size(); j++) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date inputDateBP;
                Date inputDateBMI;
                try {
                    inputDateBP = sdf.parse(bps.get(i).getDate());
                    inputDateBMI = sdf.parse(bmis.get(j).getDate());

                    // Add two months to the parsed date
                    Calendar calendar = Calendar.getInstance();
                    //for BP
                    calendar.setTime(inputDateBP);
                    calendar.add(Calendar.MONTH, 2);
                    Date twoMonthsLaterBP = calendar.getTime();
                    //for BMI
                    calendar.setTime(inputDateBMI);
                    calendar.add(Calendar.MONTH, 2);
                    Date twoMonthsLaterBMI = calendar.getTime();

                    // Compare with the current date
                    Date currentDate = new Date();

                    if (currentDate.after(twoMonthsLaterBP) && currentDate.after(twoMonthsLaterBMI)) {
                        ok = 1;
                        System.out.println("Two months have passed.");
                    } else {
                        ok = 0;
                        System.out.println("Less than two months have passed.");
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                if (bps.get(i).isNormalValue() && bmis.get(j).isNormalValue()) {
                    if (ok == 1) {
                        System.out.println("\n\nThis person is healty!");
                        System.out.println("Date: " + bps.get(i).getDate() + "\nValue of arterial tension: systolic value:  "
                                + bps.get(i).getSystolicValue() + " and diastolic value:" + bps.get(i).getDiastolicValue());
                        System.out.println("\nDate: " + bmis.get(j).getDate() + " \nbody mass value:  " + bmis.get(j).getValue());
                    }
                } else {
                    System.out.println("\n\nThis person is not healty!");
                    System.out.println("Date: " + bps.get(i).getDate() + "\nValue of arterial tension: systolic value:  "
                            + bps.get(i).getSystolicValue() + " and diastolic value:" + bps.get(i).getDiastolicValue());
                    System.out.println("\nDate: " + bmis.get(j).getDate() + " \nbody mass value:  " + bmis.get(j).getValue());
                }
            }
    }

    public void showMonth() {
        List<BP> bps = serviceBP.getAll();
        List<BMI> bmis = serviceBMI.getAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter date (dd/MM/yyyy): ");
        String date = scanner.nextLine();

        try {
            Date introducedDate = sdf.parse(date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(introducedDate);
            int month = calendar.get(Calendar.MONTH) + 1;

            for (int i = 0; i < bps.size(); i++) {
                for (int j = 0; j < bmis.size(); j++) {
                    try {
                        Date inputDateBP = sdf.parse(bps.get(i).getDate());
                        Date inputDateBMI = sdf.parse(bmis.get(j).getDate());

                        Calendar calendarBP = Calendar.getInstance();
                        calendarBP.setTime(inputDateBP);
                        int monthBP = calendarBP.get(Calendar.MONTH) + 1;

                        Calendar calendarBMI = Calendar.getInstance();
                        calendarBMI.setTime(inputDateBMI);
                        int monthBMI = calendarBMI.get(Calendar.MONTH) + 1;

                        // Compare months from both lists
                        if (month == monthBP && month == monthBMI) {
                            System.out.println("Date: " + bps.get(i).getDate() + "\nValue of arterial tension: systolic value:  "
                                    + bps.get(i).getSystolicValue() + " and diastolic value:" + bps.get(i).getDiastolicValue());
                            System.out.println("\nDate: " + bmis.get(j).getDate() + " \nbody mass value:  " + bmis.get(j).getValue());
                        }

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please enter date in dd/MM/yyyy format.");
        }
    }
}

//    public void updateCar() {
//        System.out.print("Enter Car ID to update: ");
//        int ID = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter New Marca: ");
//        String marca = scanner.nextLine();
//        System.out.print("Enter New Model: ");
//        String model = scanner.nextLine();
//
//        try {
//            serviceCar.updateCar(ID, model);
//            System.out.println("Car updated successfully!");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void updateInchiriere() {
//        System.out.print("Enter Inchiriere ID to update: ");
//        int ID = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter New Car ID: ");
//        int carID = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter New Start Date (dd/MM/yyyy): ");
//        String startDateStr = scanner.nextLine();
//        System.out.print("Enter New End Date (dd/MM/yyyy): ");
//        String endDateStr = scanner.nextLine();
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date startDate = dateFormat.parse(startDateStr);
//            Date endDate = dateFormat.parse(endDateStr);
//
//            serviceInchiriere.updateInchiriere(ID, carID, startDate, endDate);
//            System.out.println("Inchiriere updated successfully!");
//        } catch (ParseException e) {
//            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());}
//    }

//    public void deleteCar() {
//        System.out.print("Enter Car ID to delete: ");
//        int ID = scanner.nextInt();
//        scanner.nextLine();
//        try {
//            serviceCar.deleteCar(ID);
//            System.out.println("Car deleted successfully!");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public void deleteInchiriere() {
//        System.out.print("Enter Inchiriere ID to delete: ");
//        int ID = scanner.nextInt();
//        scanner.nextLine();
//        try {
//            serviceInchiriere.deleteInchiriere(ID);
//            System.out.println("Inchiriere deleted successfully!");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }


