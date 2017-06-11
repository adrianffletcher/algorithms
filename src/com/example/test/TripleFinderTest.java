package com.example.test;

import com.example.TripleFinder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test class for the PairFinder class.
 */
public class TripleFinderTest {

    private static String[] names = {"Candy Bar", "Paperback Book", "Detergent", "Headphones", "Earmuffs", "Bluetooth Stereo"};
    private static int[] prices = {500,700,1000,1400,2000,6000};
    private static int[] pricesWithDuplicates = {500,700,1000,1000,2000,6000};

    TripleFinder finder = new TripleFinder(names, prices);

    @Test
    public void test3200() throws Exception {
        assertEquals(0, finder.findTriple(3200).getRemainder());
    }

    @Test
    public void test3000() throws Exception {
        assertEquals(100, finder.findTriple(3000).getRemainder());
    }

    @Test
    public void test10000() throws Exception {
        assertEquals(600, finder.findTriple(10000).getRemainder());
    }

    @Test
    public void test2100() throws Exception {
        assertNull(finder.findTriple(2100));
    }

    @Test
    public void test4200WithDuplicates() throws Exception {
        TripleFinder pairFinder = new TripleFinder(names, pricesWithDuplicates);
        assertEquals(200, pairFinder.findTriple(4200).getRemainder());
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
        TripleFinder pairFinder = new TripleFinder(names, prices);
        assertEquals(6, pairFinder.findTriple(listSize * 5).getRemainder());
    }
}