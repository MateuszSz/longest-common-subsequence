package bioinfa.utils;

import bioinfa.algorithm.Cell;
import bioinfa.algorithm.DynamicProgramming;

public class TablePrinter {

    public static void printScoreTable(DynamicProgramming dynamicProgramming) {
        dynamicProgramming.ensureTableIsFilledIn();
        String sequence1 = dynamicProgramming.getSequence1();
        String sequence2 = dynamicProgramming.getSequence2();
        Cell[][] cellsScoreTable = dynamicProgramming.getCellsScoreTable();
        for (int i = 0; i < sequence2.length() + 2; i++) {
            for (int j = 0; j < sequence1.length() + 2; j++) {
                if (i == 0) {
                    if (j == 0 || j == 1) {
                        System.out.print("  ");
                    } else {
                        if (j == 2) {
                            System.out.print("     ");
                        } else {
                            System.out.print("   ");
                        }
                        System.out.print(sequence1.charAt(j - 2));
                    }
                } else if (j == 0) {
                    if (i == 1) {
                        System.out.print("  ");
                    } else {
                        System.out.print(" " + sequence2.charAt(i - 2));
                    }
                } else {
                    String toPrint;
                    Cell currentCell = cellsScoreTable[i - 1][j - 1];
                    Cell prevCell = currentCell.getPrevCell();
                    if (prevCell != null) {
                        if (currentCell.getCol() == prevCell.getCol() + 1
                                && currentCell.getRow() == prevCell.getRow() + 1) {
                            toPrint = "\\";
                        } else if (currentCell.getCol() == prevCell.getCol() + 1) {
                            toPrint = "-";
                        } else {
                            toPrint = "|";
                        }
                    } else {
                        toPrint = " ";
                    }
                    int score = currentCell.getScore();
                    String s = String.format("%1$3d", score);
                    toPrint += s;
                    System.out.print(toPrint);
                }

                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
