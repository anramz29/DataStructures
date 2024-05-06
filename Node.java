import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Node class representing nodes in the game tree
class Node implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    String data; // Data associated with the node
    Node yes; // Pointer to the "yes" child node
    Node no; // Pointer to the "no" child node

    // Constructor to initialize a node with data
    Node(String data) {
        this.data = data;
    }

    // Custom serialization method to write object state to ObjectOutputStream
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(data);  // Always write data
        out.writeBoolean(yes != null);  // Write a boolean indicating if 'yes' is not null
        if (yes != null) {
            out.writeObject(yes);  // Only serialize 'yes' if it's not null
        }
        out.writeBoolean(no != null);  // Write a boolean indicating if 'no' is not null
        if (no != null) {
            out.writeObject(no);  // Only serialize 'no' if it's not null
        }
    }

    // Custom deserialization method to read object state from ObjectInputStream
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        data = (String) in.readObject();  // Read the data
        if (in.readBoolean()) yes = (Node) in.readObject();  // Read 'yes' only if it was serialized
        if (in.readBoolean()) no = (Node) in.readObject();  // Read 'no' only if it was serialized
    }
}
