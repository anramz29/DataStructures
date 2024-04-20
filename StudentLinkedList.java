public class StudentLinkedList {
    private StudentNode head;
    private StudentNode tail;

    // intialize a constructor
    public StudentLinkedList() {
        head = null;
        tail = null;
    }
    // we need this method in order to obtain the node head that will be used for some of
    // the action listeners and update display methods
    public StudentNode getHead() {
        return head;
    }

    public void insertNode(Student student) {
        // this is was done in class and is self explanitory
        StudentNode node = new StudentNode(student);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    public void removeAfter(StudentNode node) {
        //  if the given node or its next is null do nothing, use the try catch blocks in the gui script
        if (node == null || node.getNext() == null) {
            return;
        }
        // obtain the next node with the get next function
        StudentNode nodeToRemove = node.getNext();
        // Link the current node to the node after the one being removed
        node.setNext(nodeToRemove.getNext());
    }

    public StudentNode searchNode(String name) {
        // same as below, get the head and iterate until the final node
        StudentNode current = head;
        while (current != null) {
            if (current.getStudent().getName().equals(name)) {
                // return specified node
                return current;
            }
            current = current.getNext();
        }
        // if Node isn't found nothing is returned
        return null;
    }

    public StudentLinkedList copy() {
        // create a new StudentLinkedList
        StudentLinkedList newList = new StudentLinkedList();
        // Obtain the current Head
        StudentNode current = head;
        // while statment until the final node
        while (current != null) {
            // use the insertNode Function for each student
            newList.insertNode(current.getStudent());
            // continue
            current = current.getNext();
        }
        return newList;
    }
    public int max(StudentNode head) {
        // If the list is empty
        if (head == null) {
            return 0;
        }
        //
        int maxAge = head.getStudent().getAge();
        StudentNode current = head.getNext();
        while (current != null) {
            // Use the Math Module with the Max function to obtain oldest Student
            maxAge = Math.max(maxAge, current.getStudent().getAge());
            // get the next student
            current = current.getNext();
        }
        return maxAge;
    }

}
