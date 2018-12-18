package bioinfa;

import bioinfa.algorithm.sequence.NeedlemanWunsch;
import bioinfa.algorithm.sequence.SequenceAlignment;
import bioinfa.algorithm.sequence.SmithWaterman;
import bioinfa.utils.CostHandler;
import bioinfa.utils.CustomFileReader;
import bioinfa.utils.RnaAcidTranslator;

import java.util.Arrays;

public class Main {
    public static RnaAcidTranslator rnaAcidTranslator = new RnaAcidTranslator(3);

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

        System.out.println("Najlepsze zestawienie globalne:");
        String[] globalAlignments = sq.getAlignments();
        System.out.println(Arrays.toString(globalAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(globalAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(globalAlignments));

        System.out.println("Najlepsze zestawienie lokalne:");
        String[] localAlignments = sq2.getAlignments();
        System.out.println(Arrays.toString(localAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(localAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(localAlignments));
        System.out.println("---------------------------------------------------------");

        customFileReader = new CustomFileReader("src/blosum.txt");

        costHandler = new CostHandler(
                customFileReader.getSequences(),
                customFileReader.getCostList()
        );

        u = rnaAcidTranslator.translateRnaToAcid(customFileReader.getSequence1());
        w = rnaAcidTranslator.translateRnaToAcid(customFileReader.getSequence2());
        sq = new NeedlemanWunsch(u, w);
        sq2 = new SmithWaterman(u, w);


        System.out.printf("Dwie sekwencje po zamianie na aminokwasy: [%s] oraz [%s]%n", u, w);

        System.out.println("Najlepsze zestawienie globalne:");
        globalAlignments = sq.getAlignments();
        System.out.println(Arrays.toString(globalAlignments));
        String s = rnaAcidTranslator.translateAcidToRna(globalAlignments[0]);
        String s1 = rnaAcidTranslator.translateAcidToRna(globalAlignments[1]);
        System.out.printf("[%s, %s]%n", s, s1);
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(globalAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(globalAlignments));

        System.out.println("Najlepsze zestawienie lokalne:");
        localAlignments = sq2.getAlignments();
        System.out.println(Arrays.toString(localAlignments));
        System.out.println("Dystans: " + costHandler.getAlignmentDistance(localAlignments));
        System.out.println("Podobieństwo: " + costHandler.getAlignmentSimilarity(localAlignments));
        System.out.println("---------------------------------------------------------");
    }
}
