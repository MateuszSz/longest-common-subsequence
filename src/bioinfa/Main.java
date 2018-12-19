package bioinfa;

import bioinfa.algorithm.sequence.NeedlemanWunsch;
import bioinfa.algorithm.sequence.SequenceAlignment;
import bioinfa.algorithm.sequence.SmithWaterman;
import bioinfa.utils.CostHandler;
import bioinfa.utils.CustomFileReader;
import bioinfa.utils.RnaAcidTranslator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CustomFileReader customFileReader = new CustomFileReader("src/array.txt");
        CostHandler costHandler = new CostHandler(
                customFileReader.getSequences(),
                customFileReader.getCostList()
        );

        String u = customFileReader.getSequence1();
        String w = customFileReader.getSequence2();

        SequenceAlignment sq = new NeedlemanWunsch(u, w);
        SequenceAlignment sq2 = new SmithWaterman(u, w);
        System.out.printf("Dwie sekwencje: [%s] oraz [%s]%n", u, w);

        String[] globalAlignments = sq.getAlignments();
        System.out.println("Najlepsze zestawienie globalne:" + Arrays.toString(globalAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(globalAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(globalAlignments));

        String[] localAlignments = sq2.getAlignments();
        System.out.println("Najlepsze zestawienie lokalne:" + Arrays.toString(localAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(localAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(localAlignments));
        System.out.println("---------------------------------------------------------");

        // RNA -> AMINO
        customFileReader = new CustomFileReader("src/blosum.txt");
        RnaAcidTranslator rnaAcidTranslator = new RnaAcidTranslator(3);
        costHandler = new CostHandler(
                customFileReader.getSequences(),
                customFileReader.getCostList()
        );

        u = rnaAcidTranslator.translateRnaToAcid(customFileReader.getSequence1());
        w = rnaAcidTranslator.translateRnaToAcid(customFileReader.getSequence2());

        sq = new NeedlemanWunsch(u, w);
        sq2 = new SmithWaterman(u, w);

        globalAlignments = sq.getAlignments();
        System.out.printf("Dwie sekwencje po zamianie na aminokwasy: [%s] oraz [%s]%n", u, w);
        System.out.println("Najlepsze zestawienie globalne:" + Arrays.toString(globalAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(globalAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(globalAlignments));

//        String s = rnaAcidTranslator.translateAcidToRna(globalAlignments[0]);
//        String s1 = rnaAcidTranslator.translateAcidToRna(globalAlignments[1]);
//        System.out.printf("[%s, %s]%n", s, s1);

        localAlignments = sq2.getAlignments();
        System.out.println("Najlepsze zestawienie lokalne:" + Arrays.toString(localAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(localAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(localAlignments));
        System.out.println("---------------------------------------------------------");

        System.out.println("Przyklady z wykladu");
        u = "pqraxabcstvq";
        w = "xyaxbacsll";
        sq2 = new SmithWaterman(u, w);
        System.out.printf("Lokalne dla [%s] oraz [%s] = %s%n", u, w, Arrays.toString(sq2.getAlignments()));

        u = "writers";
        w = "vintner";
        sq2 = new NeedlemanWunsch(u, w);
        System.out.printf("Globalne dla [%s] oraz [%s] = %s%n", u, w, Arrays.toString(sq2.getAlignments()));
    }
}
