package DataStructure;

import java.util.ArrayList;
import java.util.List;

public class TreeVisitor {
    public List<Comparable> keys;
    public TreeVisitor(){
        keys = new ArrayList<>();
    }
    public void visit(BinaryTree node) {
        keys.add(node.getKey());
    }
}
