package DataStructure;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private BinaryTree left, right;
    private Comparable key;
    private boolean ascending;
    public BinaryTree(){
    }
    public BinaryTree(Comparable key, boolean ascending) {
        this.key = key;
        this.ascending = ascending;
    }
    public BinaryTree(List<Comparable> keys, boolean ascending){
        this.ascending = ascending;
        for (int i = 0; i < keys.size(); i++)
            insert(keys.get(i));
    }

    public void insert(Comparable key){
        insert(new BinaryTree(key, this.ascending));
    }

    public void insert(BinaryTree tree) {
        if (this.key == null) {
            setKey(tree.getKey());
            return;
        }
        if (tree.key.compareTo(key) < 0) {
            if (left != null)
                left.insert(tree);
            else left = tree;
        }
        else
            if (right != null)
                right.insert(tree);
            else right = tree;
    }

    public void traverse(TreeVisitor visitor) {
        if (ascending) {
            if (left != null)
                left.traverse(visitor);
            visitor.visit(this);
            if (right != null)
                right.traverse(visitor);
        }
        else {
            if (right != null)
                right.traverse(visitor);
            visitor.visit(this);
            if (left != null)
                left.traverse(visitor);
        }
    }

    public Comparable getKey() {
        return key;
    }
    public void setKey(Comparable key){
        this.key =  key;
    }
}
