package bioinfa.algorithm;

public abstract class DynamicProgramming {

    protected String sequence1;
    protected String sequence2;
    protected Cell[][] scoreTable;
    protected boolean tableIsFilledIn;
    protected boolean isInitialized;

    public DynamicProgramming(String sequence1, String sequence2) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        scoreTable = new Cell[sequence2.length() + 1][sequence1.length() + 1];
    }

    protected abstract Cell getInitialPointer(int row, int col);

    protected abstract int getInitialScore(int row, int col);

    protected abstract void fillInCell(Cell currentCell, Cell cellAbove,
                                       Cell cellToLeft, Cell cellAboveLeft);

    abstract protected Object getTraceback();

    protected void initialize() {
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[i].length; j++) {
                scoreTable[i][j] = new Cell(i, j);
                scoreTable[i][j].setScore(getInitialScore(i, j));
                scoreTable[i][j].setPrevCell(getInitialPointer(i, j));
            }
        }
        isInitialized = true;
    }

    public int[][] getScoreTable() {
        ensureTableIsFilledIn();
        int[][] matrix = new int[scoreTable.length][scoreTable[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scoreTable[i][j].getScore();
            }
        }
        return matrix;
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
        tableIsFilledIn = true;
    }

    public void ensureTableIsFilledIn() {
        if (!isInitialized) {
            initialize();
        }
        if (!tableIsFilledIn) {
            fillIn();
        }
    }

    public String getSequence1() {
        return sequence1;
    }

    public String getSequence2() {
        return sequence2;
    }

    public Cell[][] getCellsScoreTable() {
        return scoreTable;
    }
}
