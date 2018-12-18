package bioinfa.algorithm.sequence;


public class SmithWaterman extends SequenceAlignment {

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
        int rowSpaceScore = cellAbove.getScore() + space;
        int colSpaceScore = cellToLeft.getScore() + space;
        int score = cellAboveLeft.getScore();
        if (sequence2.charAt(currentCell.getRow() - 1) == sequence1
                .charAt(currentCell.getColumn() - 1)) {
            score += match;
        } else {
            score += mismatch;
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
