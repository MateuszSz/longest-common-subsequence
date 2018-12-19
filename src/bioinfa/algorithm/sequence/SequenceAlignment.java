package bioinfa.algorithm.sequence;

public abstract class SequenceAlignment {

    private static final int FIRST_POSITION = 0;
    protected String sequence1;
    protected String sequence2;
    protected Cell[][] scoreTable;
    protected String[] alignments;


    public SequenceAlignment(String sequence1, String sequence2) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.scoreTable = new Cell[sequence2.length() + 1][sequence1.length() + 1];
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
        Cell currentCell = getAlignmentStartingCell();
        StringBuilder alignment1 = new StringBuilder();
        StringBuilder alignment2 = new StringBuilder();
        while (computingIsNotFinished(currentCell)) {
            if (currentCell.shouldInsertSpaceUsingColumn()) {
                alignment1.insert(FIRST_POSITION, '-');
            } else {
                alignment1.insert(FIRST_POSITION, sequence1.charAt(currentCell.getColumnNumber() - 1));
            }
            if (currentCell.shouldInsertSpaceUsingRow()) {
                alignment2.insert(FIRST_POSITION, '-');
            } else {
                alignment2.insert(FIRST_POSITION, sequence2.charAt(currentCell.getRowNumber() - 1));
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
                Cell cellAboveLeft = scoreTable[row - 1][col - 1];
                Cell cellToLeft = scoreTable[row][col - 1];
                Cell cellAbove = scoreTable[row - 1][col];
                fillInCell(currentCell, cellAbove, cellToLeft, cellAboveLeft);
            }
        }
    }

    public String[] getAlignments() {
        return alignments;
    }

    public void printScoreTable(){
        for (Cell[] x : scoreTable)
        {
            for (Cell y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
}
