import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
This class describes a vertex of the graph.
 */

@Getter
@Setter
public class Top<T> {

    //data - the object field describing the value of the graph vertex
    private T data;
    //parent - the reference on parent vertex
    private Top<T> parent;
    //children - the list of all vertices of the graph associated with this vertex
    private List<Top<T>> children;


    public Top(@NonNull T data) {
        this.data = data;
        this.children = new ArrayList<Top<T>>();
    }

    //adding a vertex associated with this vertex
    public void add(@NonNull Top<T> top) {
        top.setParent(this);
        children.add(top);
    }

    public T getData() {
        return data;
    }

    public List<Top<T>> iterate() {
        return children;
    }

    public Top<T> getRoot() {
        if (parent == null) return null;

        Top<T> localParent = parent;
        while (localParent.parent != null) {
            localParent = parent.parent;
        }
        return localParent;
    }

    @Override
    public String toString() {
        return "Top{" +
                "data=" + data +
                ", children=" + children +
                '}';
    }
}