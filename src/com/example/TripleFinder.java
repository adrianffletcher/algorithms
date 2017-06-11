package com.example;

/**
 * The TripleFinder class searches through a sorted collection of items to find three items that are minimally under
 * (or equal to) a supplied gift card balance. The algorithm fixes the first item and then attempts to find the best
 * solution using the next least expensive item as the second item and then traverses this remaining two-dimension
 * solution space to find the best solution using the third cheapest item and so on. If at any point we find three items
 * whose sum equal the balance then we stop the search and return the result. Otherwise we move the first item to the
 * next most expensive and repeat until we have evaluated the last three items. We then return the solution we found
 * which is closest to the balance.
 */
public class TripleFinder {

    /**
     * The ordered array of names of the items
     */
    final private String[] names;
    /**
     * The ordered and sorted array of prices of the items
     */
    final private int[] prices;

    /**
     * Creates a new TripleFinder class. The names and prices arrays must be the same size such that names[i] is the
     * name of the ith item and prices[i] is the price of the ith item. The items must be sorted in price order.
     *
     * @param names  Array of item names corresponding to the items to search
     * @param prices Sorted array of prices (in cents) corresponding to the items to search
     */

    public TripleFinder(final String[] names, final int[] prices) {
        if (names.length != prices.length) {
            throw new IllegalArgumentException("Names and prices arrays must be the same length");
        }
        this.names = names;
        this.prices = prices;
    }

    /**
     * Finds the three items that are minimally under the supplied balance.
     *
     * @param balance the gift card balance that is the limit of how much can be spent
     * @return The remainder that occurs using the optimal three items or null if there are none
     */
    public Triple findTriple(final int balance) {
        Triple bestTriple = null;
        int firstIndex = 0;
        // search across all first indexes until we arrive at the last three items
        while (firstIndex <= prices.length - 3) {
            int secondIndex = firstIndex + 1;
            int thirdIndex = prices.length - 1;
            while (secondIndex < thirdIndex) {
                final int totalPrice = prices[firstIndex] + prices[secondIndex] + prices[thirdIndex];
                if (totalPrice == balance) {
                    // we have found an exact match so we are done
                    return new Triple(firstIndex, secondIndex, thirdIndex, 0);
                } else {
                    if (totalPrice > balance) {
                        // we are over our limit so move to a second item which will cost the same or less
                        thirdIndex--;
                    } else {
                        // we have a valid solution so see if this is the best solution so far
                        if ((bestTriple == null) || (balance - totalPrice) < bestTriple.getRemainder()) {
                            // this is our new optimal pair
                            bestTriple = new Triple(firstIndex, secondIndex, thirdIndex, balance - totalPrice);
                        }
                        // now move to a first item which will cost the same or more
                        secondIndex++;
                    }
                }
            }
            // now move the first index to the next more expensive item and search again
            firstIndex++;
        }
        // this will be null if we did not find a solution
        return bestTriple;
    }

    /**
     * Inner class representing a set of three distinct items and a remaining amount from an original balance
     */
    public static class Triple {

        final private int firstIndex;
        final private int secondIndex;
        final private int thirdIndex;
        final private int remainder;

        /**
         * Create a Triple object representing three distinct items referenced by index
         *
         * @param firstIndex  the index of the first item which must be the smallest index
         * @param secondIndex the index of the second item which must be greater than the first index
         * @param thirdIndex  the index of the third item which must be greater than the second index
         * @param remainder   the remaining (positive) amount on a gift card after the three items have been bought
         */
        public Triple(final int firstIndex, final int secondIndex, final int thirdIndex, final int remainder) {
            this.firstIndex = firstIndex;
            this.secondIndex = secondIndex;
            this.thirdIndex = thirdIndex;
            this.remainder = remainder;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        public int getSecondIndex() {
            return secondIndex;
        }

        public int getThirdIndex() {
            return thirdIndex;
        }

        public int getRemainder() {
            return remainder;
        }
    }
}
