package com.example.test;

import com.example.BinaryCombinator;
import com.sun.tools.internal.xjc.util.NullStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;

/**
 * JUnit test class for the BinaryCombinator class. Uses a MockPrintStream to capture the output of BinaryCombinator for
 * various inputs.
 */
public class BinaryCombinatorTest {

    private BinaryCombinator combinator;
    private MockPrintStream mockPrintStream;

    @Before
    public void setUp() throws Exception {
        mockPrintStream = new MockPrintStream(System.out);
        combinator = new BinaryCombinator(mockPrintStream);
    }

    @Test
    public void testGenerateCombinationsXX() throws Exception {
        combinator.generateCombinations("XX");
        Assert.assertEquals(4, mockPrintStream.getNumberOfCombinations());
        Assert.assertTrue(mockPrintStream.containsCombination("00"));
        Assert.assertTrue(mockPrintStream.containsCombination("01"));
        Assert.assertTrue(mockPrintStream.containsCombination("10"));
        Assert.assertTrue(mockPrintStream.containsCombination("11"));
    }

    @Test
    public void testGenerateCombinationsX0() throws Exception {
        combinator.generateCombinations("X0");
        Assert.assertEquals(2, mockPrintStream.getNumberOfCombinations());
        Assert.assertTrue(mockPrintStream.containsCombination("00"));
        Assert.assertTrue(mockPrintStream.containsCombination("10"));
        Assert.assertFalse(mockPrintStream.containsCombination("11"));

    }

    @Test
    public void testGenerateCombinations10X10X0() throws Exception {
        combinator.generateCombinations("10X10X0");
        Assert.assertEquals(4, mockPrintStream.getNumberOfCombinations());
        Assert.assertTrue(mockPrintStream.containsCombination("1001000"));
        Assert.assertTrue(mockPrintStream.containsCombination("1001010"));
        Assert.assertTrue(mockPrintStream.containsCombination("1011000"));
        Assert.assertTrue(mockPrintStream.containsCombination("1011010"));
    }

    @Test
    public void testLargeTemplate() throws Exception {
        StringBuffer sb = new StringBuffer();
        int numberOfVariables = 20;
        for (int i = 0; i < numberOfVariables; i++) {
            sb.append("10X10");
        }
        mockPrintStream.suppressOutput();
        combinator.generateCombinations(sb.toString());
        Assert.assertEquals((int)Math.pow(2, numberOfVariables), mockPrintStream.getNumberOfCombinations());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testLargeNumberOfXs() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 63; i++) {
            sb.append("X");
        }
        combinator.generateCombinations(sb.toString());
    }

    /**
     * Inner class to allow us to test the output of the BinaryCombinator class
     */
    private static class MockPrintStream extends PrintStream {

        private PrintStream outputStream;
        private boolean suppressOutput = false;
        private Collection<String> combinations = new HashSet<String>();
        private StringBuffer sb = new StringBuffer();

        public MockPrintStream(PrintStream outputStream) {
            super(new NullStream());
            this.outputStream = outputStream;
        }

        public void print(char c) {
            sb.append(c);
        }

        public void println() {
            String combination = sb.toString();
            combinations.add(combination);
            if (!suppressOutput) {
                outputStream.println(combination);
            }
            sb = new StringBuffer();
        }

        public int getNumberOfCombinations() {
            return combinations.size();
        }

        public boolean containsCombination(String combination) {
            return combinations.contains(combination);
        }

        public void suppressOutput() {
            suppressOutput = true;
        }
    }
}