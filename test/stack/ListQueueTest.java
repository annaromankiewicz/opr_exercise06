package stack;

import exceptions.InvalidAccessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListQueueTest {
    ListQueue list = new ListQueue();

    @Test
    void dequeueEmpty() {
        assertThrows(InvalidAccessException.class, () -> {
            list.dequeue();
        });
    }

    @Test
    void peek() {
        assertThrows(InvalidAccessException.class, () -> {
            list.peek();
        });
    }
}