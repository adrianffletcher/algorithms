package com.example;

import java.io.*;
import java.util.ArrayList;

    /*
     * FinderMain class invoked from the command line and used to load the specified file, search for either pairs or triples
     * of unique items and then print out the best solution found.
     */
public class FinderMain {

        /**
         * FinderMain method invoked from the command line. The first argument should be the name of the input file, the
         * second argument should be the balance to optimise for and the third optional argument is '-t' if three items
         * are required rather than two.
         * @param args
         * @throws IOException
         */
    public static void main(String[] args) throws IOException {

        ArrayList<String> names  = new ArrayList();
        ArrayList<Integer> prices  = new ArrayList();

        // check we have the correct number of arguments
        if(args.length < 2) {
            throw new IllegalArgumentException("Filename and balance must be specified as arguments");
        }

        // load arguments from command line
        String fileName = args[0];
        Integer balance;
        try {
            balance = new Integer(args[1]);
        } catch(NumberFormatException ex) {
            throw new IllegalArgumentException("Balance supplied must be an integer: " + args[1]);
        }
        String operation = null;
        if (args.length > 2) {
            operation = args[2];
        }

        // load the file specified
        InputStream is = FinderMain.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("Unable to locate prices file " + fileName + " please ensure it is on the classpath.");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String[] data;
            String row = br.readLine();
            while (row != null) {
                data = row.split(",");
                names.add(data[0]);
                prices.add(new Integer(data[1].trim()));
                row = br.readLine();

            }
        int[] pricesArray = new int[prices.size()];
        for (int i = 0; i < prices.size(); i++) {
            pricesArray[i] = prices.get(i);
        }

        // determine whether to search for two items or three items
        if (!"-t".equals(operation)) {
            runPairFinder(names, pricesArray, balance);
        } else {
            runTripleFinder(names, pricesArray, balance);
        }
    }

        /**
         * Invoke the PairFinder class for the given inputs and print out the solution found.
         * @param names the ordered array of names of items
         * @param pricesArray the ordered array of prices of items
         * @param balance the balance that we are optimising for
         */
    private static void runPairFinder(ArrayList<String> names, int[] pricesArray, Integer balance) {
        PairFinder pairFinder = new PairFinder(names.toArray(new String[names.size()]), pricesArray);
            PairFinder.Pair bestPair = pairFinder.findPair(balance);
            if (bestPair != null) {
                StringBuffer sb = new StringBuffer();
                sb.append(names.get(bestPair.getFirstIndex()));
                sb.append(" ");
                sb.append(pricesArray[bestPair.getFirstIndex()]);
                sb.append(", ");
                sb.append(names.get(bestPair.getSecondIndex()));
                sb.append(" ");
                sb.append(pricesArray[bestPair.getSecondIndex()]);
                System.out.println(sb.toString());
            } else {
                System.out.println("Not possible");
            }
    }

        /**
         * Invoke the TripleFinder class for the given inputs and print out the solution found.
         * @param names the ordered array of names of items
         * @param pricesArray the ordered array of prices of items
         * @param balance the balance that we are optimising for
         */
        private static void runTripleFinder(ArrayList<String> names, int[] pricesArray, Integer balance) {
        TripleFinder tripleFinder = new TripleFinder(names.toArray(new String[names.size()]), pricesArray);
        TripleFinder.Triple bestTriple = tripleFinder.findTriple(balance);
        if (bestTriple != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(names.get(bestTriple.getFirstIndex()));
            sb.append(" ");
            sb.append(pricesArray[bestTriple.getFirstIndex()]);
            sb.append(", ");
            sb.append(names.get(bestTriple.getSecondIndex()));
            sb.append(" ");
            sb.append(pricesArray[bestTriple.getSecondIndex()]);
            sb.append(", ");
            sb.append(names.get(bestTriple.getThirdIndex()));
            sb.append(" ");
            sb.append(pricesArray[bestTriple.getThirdIndex()]);
            System.out.println(sb.toString());
        } else {
            System.out.println("Not possible");
        }
    }
}
