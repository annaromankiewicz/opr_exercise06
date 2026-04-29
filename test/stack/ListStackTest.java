package stack;

import exceptions.InvalidAccessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {

    ListStack list = new ListStack();

    @Test
    void pop() {
        assertThrows(InvalidAccessException.class, () -> {
            list.pop();
        });
    }

    @Test
    void peek() {
        assertThrows(InvalidAccessException.class, () -> {
            list.peek();
        });
    }
}