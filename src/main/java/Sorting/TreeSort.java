package Sorting;

import DataStructure.BinaryTree;
import DataStructure.TreeVisitor;

import java.util.ArrayList;
import java.util.List;

public class TreeSort extends SortingAlgorithm {
    @Override
    public void sortAscending(List<Comparable> values) {
        BinaryTree binaryTree = new BinaryTree(values, true);
        TreeVisitor treeVisitor = new TreeVisitor();
        binaryTree.traverse(treeVisitor);
        values.clear();
        values.addAll(treeVisitor.keys);
    }

    @Override
    public void sortDescending(List<Comparable> values) {
        BinaryTree binaryTree = new BinaryTree(values, false);
        TreeVisitor treeVisitor = new TreeVisitor();
        binaryTree.traverse(treeVisitor);
        values.clear();
        values.addAll(treeVisitor.keys);
    }
}
