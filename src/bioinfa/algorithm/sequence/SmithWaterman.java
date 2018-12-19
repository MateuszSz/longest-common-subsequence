package bioinfa.algorithm.sequence;


public class SmithWaterman extends SequenceAlignment {

    private static final int MATCH = 1;
    private static final int MISMATCH = -1;
    private static final int SPACE = -1;
    private Cell highScoreCell;

    public SmithWaterman(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    protected void initialize() {
        super.initialize();
        highScoreCell = scoreTable[0][0];
    }

    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        int rowSpaceScore = cellAbove.getScore() + SPACE;
        int colSpaceScore = cellToLeft.getScore() + SPACE;
        int score = cellAboveLeft.getScore();
        if (sequence2.charAt(currentCell.getRowNumber() - 1) == sequence1
                .charAt(currentCell.getColumnNumber() - 1)) {
            score += MATCH;
        } else {
            score += MISMATCH;
        }
        if (rowSpaceScore >= colSpaceScore) {
            setScore(currentCell, cellAbove, cellAboveLeft, rowSpaceScore, score);
        } else {
            setScore(currentCell, cellToLeft, cellAboveLeft, colSpaceScore, score);
        }
        if (currentCell.getScore() > highScoreCell.getScore()) {
            highScoreCell = currentCell;
        }
    }

    private void setScore(Cell currentCell, Cell cellToLeft, Cell cellAboveLeft, int colSpaceScore, int score) {
        if (score >= colSpaceScore) {
            if (score > 0) {
                currentCell.setScore(score);
                currentCell.setPreviousCell(cellAboveLeft);
            }
        } else {
            if (colSpaceScore > 0) {
                currentCell.setScore(colSpaceScore);
                currentCell.setPreviousCell(cellToLeft);
            }
        }
    }

    @Override
    protected boolean computingIsNotFinished(Cell currentCell) {
        return currentCell.getScore() != 0;
    }

    @Override
    protected Cell getAlignmentStartingCell() {
        return highScoreCell;
    }

    @Override
    protected Cell getInitialPointer(int row, int col) {
        return null;
    }

    @Override
    protected int getInitialScore(int row, int col) {
        return 0;
    }
}
