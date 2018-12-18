package bioinfa.algorithm.sequence;

public class Cell {
    private Cell prevCell;
    private int score;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPreviousCell(Cell prevCell) {
        this.prevCell = prevCell;
    }

    public Cell getPreviousCell() {
        return prevCell;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
