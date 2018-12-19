package bioinfa.algorithm.sequence;

public class Cell {
    private Cell prevCell;
    private int score;
    private int rowNumber;
    private int columnNumber;

    public Cell(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public boolean shouldInsertSpaceUsingColumn(){
        return this.columnNumber - this.prevCell.getColumnNumber() == 0;
    }

    public boolean shouldInsertSpaceUsingRow(){
        return this.rowNumber - this.prevCell.getRowNumber() == 0;
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

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
