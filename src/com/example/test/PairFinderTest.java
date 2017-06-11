package com.example.test;

import com.example.PairFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test class for the PairFinder class.
 */
public class PairFinderTest {

    private static String[] names = {"Candy Bar", "Paperback Book", "Detergent", "Headphones", "Earmuffs", "Bluetooth Stereo"};
    private static int[] prices = {500,700,1000,1400,2000,6000};
    private static int[] pricesWithDuplicates = {500,700,1000,1000,2000,6000};

    PairFinder finder = new PairFinder(names, prices);

    @Test
    public void test2500() throws Exception {
        assertEquals(0, finder.findPair(2500).getRemainder());
    }

    @Test
    public void test2300() throws Exception {
        assertEquals(200, finder.findPair(2300).getRemainder());
    }

    @Test
    public void test10000() throws Exception {
        assertEquals(2000, finder.findPair(10000).getRemainder());
    }

    @Test
    public void test1100() throws Exception {
        assertNull(finder.findPair(1100));
    }

    @Test
    public void test2300WithDuplicates() throws Exception {
        PairFinder pairFinder = new PairFinder(names, pricesWithDuplicates);
        assertEquals(300, pairFinder.findPair(2300).getRemainder());
    }

    @Test
    public void testLargeDataset() throws Exception {
        int listSize = 50000;
        String[] names = new String[listSize];
        int[] prices = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            names[i] = new Integer(i).toString();
            prices[i] = (i * 10)+ 1;
        }
        prices[0] = 2;
        PairFinder pairFinder = new PairFinder(names, prices);
        assertEquals(7, pairFinder.findPair(listSize * 5).getRemainder());
    }
}