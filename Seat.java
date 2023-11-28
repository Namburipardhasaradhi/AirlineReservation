package in.srkr.project3.com;


public class Seat {
    private int row;
    private char col;
    private boolean isAvailable; 

    public Seat(int row, char col) {
        this.row = row;
        this.col = col;
        this.isAvailable = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public char getCol() {
        return col;
    }

    public void setCol(char col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Seat [row=" + row + ", col=" + col + "]";
    }
}
