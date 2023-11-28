package in.srkr.project3.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SeatingOrder {
    private boolean[][] seats;
    private int numRows;
    private int numCols;

    public SeatingOrder(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        createSeats();
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    @Override
    public String toString() {
        return "SeatingOrder [seats=" + Arrays.toString(seats) + ", numRows=" + numRows + ", numCols=" + numCols + "]";
    }

    private void createSeats() {
        seats = new boolean[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                seats[row][col] = true; 
            }
        }
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (seats[row][col]) {
                    availableSeats.add(new Seat(row + 1, (char) (col + 'A')));
                }
            }
        }
        return availableSeats;
    }
    

    public boolean reserveSeat(int row, int col) {
        int rowIndex = row - 1;
        int colIndex = col- 'A';
        if (isValidSeat(rowIndex, colIndex) && seats[rowIndex][colIndex]) {
            seats[rowIndex][colIndex] = false;
            return true;
        }
        return false;
    }


    private boolean isValidSeat(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    public void printSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char seatLabel = (char) (col + 'A');
                System.out.print((row + 1) + "" + seatLabel + " ");
            }
            System.out.println();
        }
    }
    public void printSeatingStatus() {
        System.out.println("Seating Status:");
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char seatLabel = (char) (col + 'A');
                System.out.print((row + 1) + "" + seatLabel + " - " + (seats[row][col] ? "Available" : "Occupied"));
                System.out.print("   ");
            }
            System.out.println();
        }
    }
	public void freeSeat(int row, int col) {
		
	}
	
}
