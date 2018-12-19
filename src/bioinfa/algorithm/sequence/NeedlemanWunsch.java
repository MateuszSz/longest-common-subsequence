package bioinfa.algorithm.sequence;

public class NeedlemanWunsch extends SequenceAlignment {

    private static final int MISMATCH = -1;
    private static final int SPACE = -1;

    public NeedlemanWunsch(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        int rowSpaceScore = cellAbove.getScore() + SPACE;
        int colSpaceScore = cellToLeft.getScore() + SPACE;
        int score = cellAboveLeft.getScore();
        if (sequence2.charAt(currentCell.getRowNumber() - 1) != sequence1
                .charAt(currentCell.getColumnNumber() - 1)) {
            score += MISMATCH;
        }
        if (rowSpaceScore >= colSpaceScore) {
            setScore(currentCell, cellAbove, cellAboveLeft, rowSpaceScore, score);
        } else {
            setScore(currentCell, cellToLeft, cellAboveLeft, colSpaceScore, score);
        }
    }

    private void setScore(Cell currentCell, Cell cellAbove, Cell cellAboveLeft, int rowSpaceScore, int score) {
        if (score >= rowSpaceScore) {
            currentCell.setScore(score);
            currentCell.setPreviousCell(cellAboveLeft);
        } else {
            currentCell.setScore(rowSpaceScore);
            currentCell.setPreviousCell(cellAbove);
        }
    }

    @Override
    protected boolean computingIsNotFinished(Cell currentCell) {
        return currentCell.getPreviousCell() != null;
    }

    @Override
    protected Cell getAlignmentStartingCell() {
        return scoreTable[scoreTable.length - 1][scoreTable[0].length - 1];
    }

    protected int getInitialScore(int row, int col) {
        int initialScore = 0;
        if (col == 0 && row != 0) {
            initialScore = row * SPACE;
        } else if (row == 0 && col != 0) {
            initialScore = col * SPACE;
        }
        return initialScore;
    }

    protected Cell getInitialPointer(int row, int column) {
        Cell initialPointer = null;
        if (column == 0 && row != 0) {
            initialPointer = scoreTable[row - 1][column];
        } else if (row == 0 && column != 0) {
            initialPointer = scoreTable[row][column - 1];
        }
        return initialPointer;
    }
}
