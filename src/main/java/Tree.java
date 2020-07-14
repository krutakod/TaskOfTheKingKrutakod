import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
This class describes a graph.
 */

class Tree<T> {

    //root - root of graph
    @Getter
    @Setter
    private Top<T> root;

    private final static String EXP_ADD_TOP = "Top is already exist";

    public Tree(@NonNull T rootData) {
        root = new Top<T>(rootData);
        root.setData(rootData);
        root.setChildren(new ArrayList<>());
    }


    public Top<T> getTop() {
        return root;
    }

    public List<Top<T>> iterate() {
        return root.getChildren();
    }

    public boolean remove(@NonNull Top<T> top) {
        return root.getChildren().remove(top);
    }

    //method for adding a vertex associated with a graph root
    public void add(@NonNull Top<T> top) throws TreeException {
        Top<T> finded = findTopByData(top.getData());
        if(finded==null){
            top.setParent(this.root);
            root.getChildren().add(top);
        } else {
            throw new TreeException(EXP_ADD_TOP);
        }
    }

    //method for finding the top of the graph in the entire graph
    public Top<T> findTopByData(@NonNull final T obj) {
        Top<T> result = null;
        final Queue<Top<T>> allFiles = new LinkedList<>();
        allFiles.add(getTop());
        while (!allFiles.isEmpty()) {
            final Top<T> localEntry = allFiles.poll();
            final List<Top<T>> files = localEntry.iterate();
            for (final Top<T> innerFile : files) {
                if (innerFile.getData().equals(obj)) {
                    result = innerFile;
                    break;
                }
                allFiles.add(innerFile);
            }
        }
        return result;
    }

    //method displays the entire graph on the screen
    public <T> void printCatalog() {
        printNode(this.getTop(), "");
        printKids(root.getChildren(), " ");
    }

    //method that uses printCatalog
    private <T> void printKids(@NonNull List<Top<T>> kids, @NonNull String space) {
        if (!kids.isEmpty()) {
            for (Top<T> kid : kids) {
                printNode(kid, space);
                printKids(kid.getChildren(), space + " ");
            }
        }
    }

    //method that uses printCatalog
    private <T> void printNode(@NonNull Top<T> kid, @NonNull String space) {
        System.out.println(space+kid.getData());

    }
}
