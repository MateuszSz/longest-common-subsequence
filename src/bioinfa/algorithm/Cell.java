package bioinfa.algorithm;

public class Cell {
    private Cell prevCell;
    private int score;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPrevCell(Cell prevCell) {
        this.prevCell = prevCell;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getPrevCell() {
        return prevCell;
    }

    @Override
    public String toString() {
        return "Cell(" + row + ", " + col + "): score=" + score + ", prevCell="
                + prevCell + "]";
    }
}
