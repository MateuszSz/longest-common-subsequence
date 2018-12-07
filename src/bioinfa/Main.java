package bioinfa;

import bioinfa.algorithm.seqalign.NeedlemanWunsch;
import bioinfa.algorithm.seqalign.SequenceAlignment;
import bioinfa.algorithm.seqalign.SmithWaterman;
import bioinfa.algorithm.sequence.LongestCommonSubsequence;
import bioinfa.utils.TablePrinter;

import javax.sound.midi.Sequence;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String u = "writers", w = "vintner";

        SequenceAlignment sq = new NeedlemanWunsch(u,w);
        SequenceAlignment sq2 = new SmithWaterman(u,w);
        LongestCommonSubsequence ls = new LongestCommonSubsequence(u,w);

        System.out.println("Najlepsze zestawienie globalne:");
        TablePrinter.printScoreTable(sq);
        System.out.println(Arrays.toString(sq.getAlignment()));
        System.out.println(sq.getAlignmentScore());

        System.out.println("Najlepsze zestawienie lokalne:");
        TablePrinter.printScoreTable(sq2);
        System.out.println(Arrays.toString(sq2.getAlignment()));
        System.out.println(sq2.getAlignmentScore());

        System.out.println("podobienstwo:");
        TablePrinter.printScoreTable(ls);
        System.out.println(ls.getLongestCommonSubsequence());
    }
}
