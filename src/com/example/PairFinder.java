package com.example;

/**
 * The PairFinder class searches through a sorted collection of items to find two items that are minimally under
 * (or equal to) a supplied gift card balance. The algorithm firstly attempts to find the best solution using
 * the least expensive item and then traverses the two-dimension solution space to find the best solution using the
 * second cheapest item and so on. If at any point we find two items whose sum equal the balance then we stop the search
 * and return the result. Otherwise we return the solution we found which is closest to the balance.
 */
public class PairFinder {

    /**
     * The ordered array of names of the items
     */
    private final String[] names;
    /**
     * The ordered and sorted array of prices of the items
     */
    private final int[] prices;

    /**
     * Creates a new PairFinder class. The names and prices arrays must be the same size such that names[i] is the
     * name of the ith item and prices[i] is the price of the ith item. The items must be sorted in price order.
     *
     * @param names  Array of item names corresponding to the items to search
     * @param prices Sorted array of prices (in cents) corresponding to the items to search
     */

    public PairFinder(final String[] names, final int[] prices) {
        if (names.length != prices.length) {
            throw new IllegalArgumentException("Names and prices arrays must be the same length");
        }
        this.names = names;
        this.prices = prices;
    }

    /**
     * Finds the two items that are minimally under the supplied balance.
     *
     * @param balance the gift card balance that is the limit of how much can be spent
     * @return The remainder that occurs using the optimal two items or null if there are none
     */
    public Pair findPair(final int balance) {
        Pair bestPair = null;
        int firstIndex = 0;
        int secondIndex = prices.length - 1;
        while (firstIndex < secondIndex) {
            final int totalPrice = prices[firstIndex] + prices[secondIndex];
            if (totalPrice == balance) {
                // we have found an exact match so we are done
                return new Pair(firstIndex, secondIndex, 0);
            } else {
                if (totalPrice > balance) {
                    // we are over our limit so move to a second item which will cost the same or less
                    secondIndex--;
                } else {
                    // we have a valid solution so see if this is the best solution so far
                    if ((bestPair == null) || (balance - totalPrice) < bestPair.getRemainder()) {
                        // this is our new optimal pair
                        bestPair = new Pair(firstIndex, secondIndex, balance - totalPrice);
                    }
                    // now move to a first item which will cost the same or more
                    firstIndex++;
                }
            }
        }
        // this will be null if we did not find a solution
        return bestPair;
    }

    /**
     * Inner class representing a set of two distinct items and a remaining amount from an original balance
     */
    public static class Pair {

        private final int firstIndex;
        private final int secondIndex;
        private final int remainder;

        /**
         * Create a Pair object representing two distinct items referenced by index
         *
         * @param firstIndex  the index of the first item which must be the smallest index
         * @param secondIndex the index of the second item which must be greater than the first index
         * @param remainder   the remaining (positive) amount on a gift card after the two items have been bought
         */

        public Pair(final int firstIndex, final int secondIndex, final int remainder) {
            this.firstIndex = firstIndex;
            this.secondIndex = secondIndex;
            this.remainder = remainder;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        public int getSecondIndex() {
            return secondIndex;
        }

        public int getRemainder() {
            return remainder;
        }
    }
}
