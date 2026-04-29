package stack;

import exceptions.ValueException;

public class ChainingHashSet {

    /**
     * Array which stores overflow lists that are indexed by the hash
     * code of their elements.
     */
    private RandomAccessDoubleLinkedList[] array;

    /**
     * Calculates the hashcode for every val with values in [Integer.MIN_VALUE; Integer.MAX_VALUE]
     * Allows positive values
     */
    private int hash(int val) throws ValueException {
        // inner round bracket: result negative, second round bracket: result is positive
        if (val < 0) throw new ValueException(val);
        return (val % array.length);
    }

    /**
     * Initializes an empty hashtable with the given number of overflow
     * lists. Allows indexSizes > 0 because an array with length 0 can't
     * store values and would cause a division by zero in hash(T elem)!
     */
    public ChainingHashSet(int indexSize) throws ValueException {
        if (indexSize == 0) {
            throw new ValueException(indexSize);
        }
        array = new RandomAccessDoubleLinkedList[indexSize]; // array stays null because it is not initialized
    }

    /**
     * Inserts a new element val into the hashtable (hashcode = val %
     * array.length), if it did not exist in the table before. Returns true
     * if a new element was inserted, false if it already existed.
     */
    public boolean insert(int val) throws ValueException {
        int hashcode = hash(val);
        if (array[hashcode] == null) {
            array[hashcode] = new RandomAccessDoubleLinkedList();
            array[hashcode].add(val);
            return true;
        } else if (!array[hashcode].contains(val)) {
            array[hashcode].add(val);
            return true;
        }
        return false;
    }


    /**
     * Returns true if the given value is already stored in the
     * hashtable, false otherwise.
     */
    public boolean contains(int val) throws ValueException {
        int hashcode = hash(val);
        if (array[hashcode] == null) return false; // List of given val with hashcode is empty
        return (array[hashcode].contains(val));
    }


    /**
     * Removes the given element from the hashtable if it exists.
     * Returns true if an element was removed, false if no such
     * element existed.
     */
    public boolean remove(int val) throws ValueException {
        if (this.contains(val)) {
            int hashcode = hash(val);
            array[hashcode].remove((Integer) val); // remove(Object o) -> casting necessary to call function with Integer
            return true;
        }
        return false;
    }

    /**
     * Counts the amount of values, which are stored in the same
     * overflow list
     */
    public int getOverflowCount(int hashVal) throws ValueException {
        if (hashVal < 0 || hashVal >= array.length) { // invalid input for hashVal
            throw new ValueException(hashVal);
        }
        if (array[hashVal] == null) return 0; // Overflowlist is not initialized
        return array[hashVal].size();
    }

    /**
     * Counts the amount of elements in the hashtable by adding the
     * amount of elements in each overflow list.
     */
    public int elements() {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sum += array[i].size();
            }
        }
        return sum;
    }
}
