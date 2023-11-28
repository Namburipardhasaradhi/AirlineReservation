package in.srkr.project3.com;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class AirlineReservation {
    private static final int TravelPrice = 0;
    private static SeatingOrder seatingOrder;
    private static Flight flight;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To BKP Air Line Reservation");
        
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (AuthenticationManager.authenticate(username, password)) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
        
        flight = getFlightDetails(scanner);
        seatingOrder = new SeatingOrder(9,9);
        Booking booking = createBooking(scanner, seatingOrder, flight);
        printBookingSummary(booking);
        printSeatingStatus(seatingOrder);
        while (true) {
            try {
                System.out.println("Choose an option:");
                System.out.println("1. View Available Seats");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        viewAvailableSeats();
                        break;
                    case 2:
                        cancelReservation(scanner, booking);
                        break;
                    case 3:
                        System.out.println("Thank you for using BKP Air Line Reservation.Have A Nice Day!");
                        scanner.close();
                        System.exit(0);
                    default:
                        throw new InvalidOptionException("Invalid option. Please try again.");
                }
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static Flight getFlightDetails(Scanner scanner) {
    	 
            LocalTime LT = LocalTime.now();
            System.out.println("current time is-" + LT);
            LocalDate LD = LocalDate.now();
            System.out.println("current Date is:" + LD);
            while (true) { 
    	        try {
    	        	
    	        	System.out.println("Enter the Airport Name:");
    	            String airportName = getValidNameInput(scanner);
    	            
            System.out.println("Enter Flight Number:");
            int flightNO = scanner.nextInt();

            System.out.println("Enter Flight Name:");
            String flightName = getValidNameInput(scanner);

            System.out.println("FromCity:");
            String fromCity = getValidNameInput(scanner);

            System.out.println("ToCity:");
            String toCity = getValidNameInput(scanner);

            LocalDate date = getDateInput(scanner, "Enter Date (yyyy-MM-dd):");

            

            return new Flight(flightNO, flightName, fromCity, toCity, date, LT, airportName);
    	        } catch (InputMismatchException e) {
    	            System.out.println("Invalid input for flight number. Please enter a valid number.");
    	            scanner.nextLine(); 
    	        } catch (DateTimeParseException | NumberFormatException e) {
    	            System.out.println("Invalid input for other flight details. Please check your input and try again.");
    	            scanner.nextLine(); 
    	        }
    	    }
    	}
    private static DetailsOfPassenger getPassengerDetails(Scanner scanner) {
        try {
            System.out.println("Enter First Name:");
            String FirstName = getValidNameInput(scanner);

            System.out.println("Enter Last Name:");
            String LastName = getValidNameInput(scanner);
            
            System.out.println("Enter Passport Id:");
            int passportId = scanner.nextInt();

            System.out.println("Enter Nationality:");
            String nationality = getValidNameInput(scanner);

            System.out.println("Please choose your gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Other");

            int choice = scanner.nextInt();
            String gender;
            switch (choice) {
                case 1:
                    gender = "Male";
                    break;
                case 2:
                    gender = "Female";
                    break;
                case 3:
                    gender = "Other";
                    break;
                default:
                    gender = "Unknown";
                    break;
            }
            System.out.println("your gender is:" + gender);

            System.out.print("Enter your date of birth (YYYY-MM-DD): ");
            String dateOfBirthStr = scanner.next();
            int age = calculateAge(dateOfBirthStr);
            if (age >= 0) {
                System.out.println("Your age is: " + age);
            } else {
                System.out.println("Invalid date format. Please use the format: YYYY-MM-DD");
                return null; 
            }
            String emailaddress = getEmailInput(scanner);

            System.out.println("Please enter your Contact No:");
            String contactNo = scanner.next();
            if (!isValidContactNo(contactNo)) {
                System.out.println("Invalid Contact number: " + contactNo);
                return null; 
            }
            return new DetailsOfPassenger(FirstName,LastName, passportId, nationality, gender, contactNo, TravelPrice);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidOptionException("Invalid input for passenger details. Please check your input.");
        }
    }
    public static int calculateAge(String dateOfBirthStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOfBirth = sdf.parse(dateOfBirthStr);
            Date currentDate = new Date();
            long timeDifference = currentDate.getTime() - dateOfBirth.getTime();
            int years = (int) (timeDifference / (1000L * 60 * 60 * 24 * 365));
            return years;
        } catch (ParseException e) {
            throw new InvalidOptionException("Invalid date format for age calculation. Please use the format: YYYY-MM-DD");
        }
    }
    private static LocalDate getDateInput(Scanner scanner, String prompt) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.println(prompt);
                String dateInput = scanner.next();
                date = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        }
        return date;
    }
    private static boolean isValidContactNo(String contactNo) {
        return contactNo.matches("[0-9]{10}");
    }
    private static void viewAvailableSeats() {
        List<Seat> availableSeats = seatingOrder.getAvailableSeats();
        char lastColumn = availableSeats.get(availableSeats.size() - 1).getCol();
        System.out.println("Available Seats:");
        for (Seat seat : availableSeats) {
            int row = seat.getRow();
            char col = seat.getCol();
            System.out.print(row + "" + col + " - Available   ");
            if (col == lastColumn) {
                System.out.println(); 
            }
        }
    }
    private static Booking createBooking(Scanner scanner, SeatingOrder seatingOrder, Flight flight) {
        Booking booking = new Booking(flight);

        System.out.print("Enter the number of passengers: ");
        int numPassengers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= numPassengers; i++) {
            DetailsOfPassenger passenger = getPassengerDetails(scanner);
            if (passenger != null) { 
                booking.addPassenger(passenger);
                reserveSeat(scanner, seatingOrder, i);
            } else {
                System.out.println("Passenger details are not valid. Please try again.");
                i--; 
            }
        }
        return booking;
    }
    private static void reserveSeat(Scanner scanner, SeatingOrder seatingOrder, int passengerNumber) {
        seatingOrder.printSeatingPlan();

        System.out.print("Enter the row number for Passenger " + passengerNumber + ": ");
        int row = scanner.nextInt();
        System.out.print("Enter the column (A, B, C, etc.) for Passenger " + passengerNumber + ": ");
        char col = scanner.next().charAt(0);

        if (seatingOrder.reserveSeat(row, col)) {
            System.out.println("Seat reserved successfully for Passenger " + passengerNumber + "!");
        } else {
            System.out.println("Seat is already occupied or not available for Passenger " + passengerNumber + ".");
        }
    }
    private static void printSeatingStatus(SeatingOrder seatingOrder) {
        System.out.println("Seating Status:");
        seatingOrder.printSeatingStatus();
    }
    private static void cancelReservation(Scanner scanner, Booking booking) {
        System.out.print("Enter the passenger name to cancel reservation: ");
        String passengerName = scanner.next();
        boolean found = false;
        List<DetailsOfPassenger> passengers = booking.getPassengers();
        Iterator<DetailsOfPassenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            DetailsOfPassenger passenger = iterator.next();
            if (passenger.getFirstName().equalsIgnoreCase(passengerName)) {
                Seat seat = passenger.getSeat();
                if (seat != null) {
                    int row = seat.getRow();
                    char col = seat.getCol();
                    seatingOrder.freeSeat(row, col);
                    passenger.setSeat(null);
                }
                iterator.remove();
                System.out.println("Reservation canceled successfully for passenger: " + passengerName);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Passenger not found or no reservation found for passenger: " + passengerName);
        }
    }
    private static void printBookingSummary(Booking booking) {
        System.out.println("Booking Summary:");
        System.out.println("Flight Details: " + booking.getFlight().toString());
        System.out.println("Passenger Details: " + booking.getPassengers().toString());
        System.out.println("Number of Passengers: " + booking.getNumPassengers());
        System.out.println("Total Price: " + booking.getTotalPrice());
    }
    private static boolean containsNumbers(String str) {
        return str.matches(".*\\d+.*");
    }
    private static String getValidNameInput(Scanner scanner) {
        String name;
        do {
            System.out.print(" ");
            name = scanner.next();
            if (containsNumbers(name)) {
                System.out.println("Invalid input. Name should not contain numbers.");
            }
        } while (containsNumbers(name));
        return name;
    }
    private static String getEmailInput(Scanner scanner) {
        String email;
        boolean isValid = false;
        do {
            System.out.print("Enter your email address: ");
            email = scanner.next();
            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

            if (Pattern.matches(emailPattern, email)) {
                isValid = true;
            } else {
                System.out.println("Invalid email address format. Please enter a valid email address.");
            }
        } while (!isValid);
        return email;
    }
    private static class InvalidOptionException extends RuntimeException {
        public InvalidOptionException(String message) {
            super(message);
        }
    }
}
