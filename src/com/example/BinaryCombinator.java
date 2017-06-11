package com.example;

import java.io.PrintStream;

/**
 * The BinaryCombinator class takes String template of '0's, '1's and 'X's and generates the full set of combinations
 * where the each X is replaced with both 0 and 1. For example the template "X1" should produce "01" and "00" and the
 * template "10X10X0' should produce "1001000","1001010","1011000" and "1011010".
 * All output is sent to the supplied PrintStream.
 */
public class BinaryCombinator {

    // the stream that we will output all generated combinations to
    private PrintStream outStream;

    /**
     * Constructor which supplies the stream to output combinations to.
     *
     * @param outStream the PrintStream that all generated combinations will be output to
     */
    public BinaryCombinator(PrintStream outStream) {
        this.outStream = outStream;
    }

    /**
     * Generates all the combinations of the supplied template where the 'X's are replaced with both 0 and 1. Clearly
     * there will be 2^k combinations where k is the number of 'X' characters in the template. As this number scales
     * exponentially with respect to k we need to be careful not to use recursion which may cause us to run out of
     * memory for large k. Our approach here is to simply count from 0 up to 2^k and for each number overlay the binary
     * representation of that number onto the original template.
     *
     * @param template String containing '0's, '1's and 'X's.

     */
    public void generateCombinations(final String template) {
        // find the number of variable bits ('X's) by looping over the template
        int numberOfVariables = 0;
        for (int i = 0; i < template.length(); i++) {
            if ('X' == template.charAt(i)) {
                numberOfVariables++;
            }
        }
        // we will use a long to store the number of combinations which can only support numbers up to 2^63-1
        if (numberOfVariables >= 63) {
            throw new IllegalArgumentException("Only templates containing less than 63 'X's are supported.");
        }
        // calculate the number of combinations required
        final long combinations = (long) Math.pow(2, numberOfVariables);
        // simply count up to the number of combinations required and process each one
        for (long i = 0; i < combinations; i++) {
            // print out the number in binary
            printCombinationUsingTemplate(template, numberOfVariables, i);
        }
    }

    private void printCombinationUsingTemplate(final String template, final int numberOfVariables, final long combination) {
        // the first bit we will need is the left most bit
        int bitPositionNeeded = numberOfVariables - 1;
        // loop over the template and when we find an X just use the next bit from the int
        for (int i = 0; i < template.length(); i++) {
            final char currentCharacter = template.charAt(i);
            if ('X' == currentCharacter) {
                // output the next bit from the long
                long bit = (combination >> bitPositionNeeded) & 1;
                outStream.print(Long.toBinaryString(bit).charAt(0));
                bitPositionNeeded--;
            } else {
                // this must be a '0' or a '1' so simply output this character
                outStream.print(currentCharacter);
            }
        }
        outStream.println();
    }
}
