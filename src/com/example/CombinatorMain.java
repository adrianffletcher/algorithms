package com.example;

import java.io.IOException;

/*
* CombinatorMain class invoked from the command line and used to run generate all the combinations for the supplied
* template.
*/
public class CombinatorMain {

    /**
     * CombinatorMain method invoked from the command line. The first argument should be the template of 1s, 0s, and Xs
     * for which the full set of combinations is required where the each X is replaced with both 0 and 1.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // load template from command line
        if (args.length == 0) {
            throw new IllegalArgumentException("find-combinations requires a template to be supplied containing 1s, 0s and Xs.");
        }
        String template = args[0];
        BinaryCombinator combinator = new BinaryCombinator(System.out);
        combinator.generateCombinations(template);
    }
}
