package sportsclub;

import exceptions.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMemberTest {

    Section section;


    @BeforeEach
    void setUp() {
        try {
            section = new Section("Sport union");
        } catch (ValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExpectExceptionActivityLevel() {
        try {
            AbstractMember activeMember = new Trainer("Anna", 11);
        } catch (ValueException e) {
            System.out.println(e.getMessage() + " Invalid Value: " + e.getWhichValue());
        }

        try {
            AbstractMember activeMember = new Trainer("Anna", -1);
        } catch (ValueException e) {
            System.out.println(e.getMessage() + " Invalid Value: " + e.getWhichValue());
        }
    }

    @Test
    public void testExpectExceptionCompetenceLevel() {
        try {
            AbstractMember chairMember = new ChairMember("Kathi", 11);
        } catch (ValueException e) {
            System.out.println(e.getMessage() + " Invalid Value: " + e.getWhichValue());
        }

        try {
            AbstractMember chairMember = new ChairMember("Kathi", -1);
        } catch (ValueException e) {
            System.out.println(e.getMessage() + " Invalid Value: " + e.getWhichValue());
        }

    }

    @Test
    public void testInsertInvalid() {

        AbstractMember member = null;
        assertThrows(ValueException.class, () -> {
            section.addMember(member);
        });
    }

    @Test
    public void testFindInvalid() {

        AbstractMember member = null;
        assertThrows(ValueException.class, () -> {
            section.isMember(member);
        });
    }

    @Test
    public void testRemoveInvalid() {
        AbstractMember member = null;
        assertThrows(ValueException.class, () -> {
            section.removeMember(member);
        });
    }

    @Test
    public void testNameNull() {
        String name = null;
        try {
            AbstractMember member = new Trainer(name, 5);
            System.out.print(member.toString());
        } catch (ValueException e) {
            System.out.println(e.getMessage());
        }


    }


}
