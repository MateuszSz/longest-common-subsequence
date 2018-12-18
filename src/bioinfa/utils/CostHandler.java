package bioinfa.utils;

import java.util.List;

public class CostHandler {
    private List<String> uniqueSpitedSequence;
    private List<List<Integer>> costList;

    public CostHandler(List<String> uniqueSpitedSequence1, List<List<Integer>> costList) {
        this.uniqueSpitedSequence = uniqueSpitedSequence1;
        this.costList = costList;
    }

    public int getAlignmentDistance(String[] alignments) {
        int score = 0;
        int alignmentsLength = alignments[0].length();
        for (int i = 0; i < alignmentsLength; i++) {
            char c1 = alignments[0].charAt(i);
            char c2 = alignments[1].charAt(i);
            if(c1 != c2)
                score += getCost(Character.toString(c1), Character.toString(c2));
        }
        return score;
    }
    public int getAlignmentSimilarity(String[] alignments) {
        int score = 0;
        int alignmentsLength = alignments[0].length();
        for (int i = 0; i < alignmentsLength; i++) {
            char c1 = alignments[0].charAt(i);
            char c2 = alignments[1].charAt(i);
            if(c1 == c2)
                score += getCost(Character.toString(c1), Character.toString(c2));
        }
        return score;
    }

    private int getCost(String a, String b){
        int i = uniqueSpitedSequence.indexOf(a);
        int j = uniqueSpitedSequence.indexOf(b);
        return costList.get(i).get(j);
    }
}
