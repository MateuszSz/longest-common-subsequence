package bioinfa.utils;

import java.util.HashMap;
import java.util.Map;

public class RnaAcidTranslator {
    private int pointer;
    private  Map<String, String> table = new HashMap<>();

    public RnaAcidTranslator(int pointer)
    {
        this.pointer = pointer;
        table.put ("UUU", "F");
        table.put ("UUC", "F");
        table.put ("UUA", "L");
        table.put ("UUG", "L");
        table.put ("UCU", "S");
        table.put ("UCC", "S");
        table.put ("UCA", "S");
        table.put ("UCG", "S");
        table.put ("UAU", "Y");
        table.put ("UAC", "Y");
        table.put ("UGU", "C");
        table.put ("UGC", "C");
        table.put ("UGG", "W");
        table.put ("CUU", "L");
        table.put ("CUC", "L");
        table.put ("CUA", "L");
        table.put ("CUG", "L");
        table.put ("CCU", "P");
        table.put ("CCC", "P");
        table.put ("CCA", "P");
        table.put ("CCG", "P");
        table.put ("CAU", "H");
        table.put ("CAC", "H");
        table.put ("CAA", "Q");
        table.put ("CAG", "Q");
        table.put ("CGU", "R");
        table.put ("CGC", "R");
        table.put ("CGA", "R");
        table.put ("CGG", "R");
        table.put ("AUU", "I");
        table.put ("AUC", "I");
        table.put ("AUA", "I");
        table.put ("AUG", "M");
        table.put ("ACU", "U");
        table.put ("ACC", "U");
        table.put ("ACA", "U");
        table.put ("ACG", "U");
        table.put ("AAU", "N");
        table.put ("AAC", "N");
        table.put ("AAA", "K");
        table.put ("AAG", "K");
        table.put ("AGU", "S");
        table.put ("AGC", "S");
        table.put ("AGA", "R");
        table.put ("AGG", "R");
        table.put ("GUU", "V");
        table.put ("GUC", "V");
        table.put ("GUA", "V");
        table.put ("GUG", "V");
        table.put ("GCU", "A");
        table.put ("GCC", "A");
        table.put ("GCA", "A");
        table.put ("GCG", "A");
        table.put ("GAU", "D");
        table.put ("GAC", "D");
        table.put ("GAA", "E");
        table.put ("GAG", "E");
        table.put ("GGU", "G");
        table.put ("GGC", "G");
        table.put ("GGA", "G");
        table.put ("GGG", "G");
        table.put ("UAA", "STOP");
        table.put ("UAG", "STOP");
        table.put ("UGA", "STOP");
    }

    public String translateRnaToAcid(String temp)
    {
        StringBuilder finalreturn = new StringBuilder();
        String codon;
        for (int i = 0; i < temp.length() - 2; i+=pointer) {
            codon = temp.substring(i, i+3);
            String value = table.get(codon);
            if(!value.equals("STOP"))
                finalreturn.append(table.get(codon));
            else
                break;
        }
        return finalreturn.toString();
    }

    public String translateAcidToRna(String temp)
    {
        StringBuilder finalreturn = new StringBuilder();
        String codon;
        for (int i = 0; i < temp.length(); i+=1) {
            codon = temp.substring(i, i+1);
            if(!codon.equals("-")) {
                finalreturn.append(getKeyFromValue(table, codon));
            }
            else{
                finalreturn.append("-");
            }
        }
        return finalreturn.toString();
    }

    public static String getKeyFromValue(Map<String, String> table, String value) {
        for (String key : table.keySet()) {
            if (table.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
