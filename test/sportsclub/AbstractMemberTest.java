package sportsclub;

import exceptions.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMemberTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testExpectException() {
        assertThrows(ValueException.class, () -> {
            AbstractMember member = new Trainer("Anna", 11);
        });
    }
}