package bioinfa.algorithm.sequence;

public abstract class SequenceAlignment {

    protected String sequence1;
    protected String sequence2;
    protected Cell[][] scoreTable;
    protected String[] alignments;
    protected int match;
    protected int mismatch;
    protected int space;

    public SequenceAlignment(String sequence1, String sequence2) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.scoreTable = new Cell[sequence2.length() + 1][sequence1.length() + 1];
        this.match = 1;
        this.mismatch = -1;
        this.space = -1;
        this.initialize();
        this.fillIn();
        this.computeAlignment();
    }

    protected abstract Cell getInitialPointer(int row, int col);

    protected abstract int getInitialScore(int row, int col);

    protected abstract void fillInCell(Cell currentCell, Cell cellAbove,
                                       Cell cellToLeft, Cell cellAboveLeft);

    protected abstract boolean computingIsNotFinished(Cell currentCell);

    protected abstract Cell getAlignmentStartingCell();

    private void computeAlignment() {
        StringBuilder alignment1 = new StringBuilder();
        StringBuilder alignment2 = new StringBuilder();
        Cell currentCell = getAlignmentStartingCell();
        while (computingIsNotFinished(currentCell)) {
            if (currentCell.getColumn() - currentCell.getPreviousCell().getColumn() == 1) {
                alignment1.insert(0, sequence1.charAt(currentCell.getColumn() - 1));
            } else {
                alignment1.insert(0, '-');
            }
            if (currentCell.getRow() - currentCell.getPreviousCell().getRow() == 1) {
                alignment2.insert(0, sequence2.charAt(currentCell.getRow() - 1));
            } else {
                alignment2.insert(0, '-');
            }
            currentCell = currentCell.getPreviousCell();
        }

        alignments = new String[]{alignment1.toString(),
                alignment2.toString()};
    }

    protected void initialize() {
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[i].length; j++) {
                Cell cell = new Cell(i, j);
                cell.setScore(getInitialScore(i, j));
                cell.setPreviousCell(getInitialPointer(i, j));
                scoreTable[i][j] = cell;
            }
        }
    }

    protected void fillIn() {
        for (int row = 1; row < scoreTable.length; row++) {
            for (int col = 1; col < scoreTable[row].length; col++) {
                Cell currentCell = scoreTable[row][col];
                Cell cellAbove = scoreTable[row - 1][col];
                Cell cellToLeft = scoreTable[row][col - 1];
                Cell cellAboveLeft = scoreTable[row - 1][col - 1];
                fillInCell(currentCell, cellAbove, cellToLeft, cellAboveLeft);
            }
        }
    }

    public String[] getAlignments() {
        return alignments;
    }
}
