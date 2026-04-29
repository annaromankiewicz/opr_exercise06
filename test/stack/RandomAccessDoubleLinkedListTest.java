package stack;

import exceptions.InvalidAccessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomAccessDoubleLinkedListTest {

    RandomAccessDoubleLinkedList list = new RandomAccessDoubleLinkedList();

    @Test
    void insertAtInvalidIndex() {
        assertThrows(InvalidAccessException.class, () -> {
            list.insertAt(-1, 10);
        });
    }

    @Test
    void removeAt() {
        assertThrows(InvalidAccessException.class, () -> {
            list.removeAt(-1);
        });
    }

    @Test
    void elementAt() {
        assertThrows(InvalidAccessException.class, () -> {
            list.elementAt(-1);
        });
    }
}