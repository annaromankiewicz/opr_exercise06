package stack;

import exceptions.InvalidAccessException;
import mc.opr.dll.MyDoubleLinkedList;

public class ListStack extends MyDoubleLinkedList {

    /** Initializes an empty stack. */
    public ListStack() {
        super();
    }

    /** Copy constructor which initializes the stack with another stack.
     This constructor must COPY all elements of the other stack. */
    public ListStack(ListStack other) {
        super();
        if (other != null) {
            int index = 0;
            int size = other.elements();
            while (index < size) {
                super.pushBack(other.peekElementAt(index));
                index++;
            }
        }
    }


    /** Pushes an element onto the stack. */
    public void push(int val) {
        super.pushBack(val);
    }


    /** Returns the top element of the stack and removes it. */
    public int pop() throws InvalidAccessException {
        if (super.elements()==0) throw new  InvalidAccessException("Stack is empty");
        return super.popBack();
    }

    /** Returns the top element of the stack without removing it. */
    public int peek() throws InvalidAccessException{
        if (super.elements()==0) throw new InvalidAccessException("Stack is empty");
        return super.peekBack();
    }


}
