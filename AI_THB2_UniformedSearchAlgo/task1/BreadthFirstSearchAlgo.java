package AI_THB2_UniformedSearchAlgo.task1;

import java.util.*;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        List<Node> exploredSet = new ArrayList<>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll(); //queue poll là removeFirst
            if (current.getLabel().equals(goal)) return current;
            exploredSet.add(current);
            List<Node> children = current.getChildrenNodes();
            for (Node child : children) {
                if (!frontier.contains(child) && !exploredSet.contains(child)) {
                    frontier.add(child); //queue add là addLast
                    child.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        List<Node> exploredSet = new ArrayList<>();
        boolean isStarted = false;
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal) && isStarted) return current;
            if (current.getLabel().equals(start)) {
                isStarted = true;
                frontier.clear();
                exploredSet.clear();
                current.setParent(null);
            }
            exploredSet.add(current);
            List<Node> children = current.getChildrenNodes();
            for (Node child : children) {
                if (!frontier.contains(child) && !exploredSet.contains(child)) {
                    frontier.add(child);
                    child.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node treeSearch(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            for (Edge child : current.getChildren()) {
                frontier.add(child.getEnd());
                if(child.getEnd().getParent()==null){
                    child.getEnd().setParent(child.getBegin());
                }
            }
        }
        return null;
    }

    @Override
    public Node treeSearch(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);
        boolean isStarted = false;
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal) && isStarted) return current;
            if(current.getLabel().equals(start)) {
                isStarted = true;
                frontier.clear();
                current.setParent(null);
            }
            for (Edge child : current.getChildren()) {
                frontier.add(child.getEnd());
                if(child.getEnd().getParent()==null){
                    child.getEnd().setParent(child.getBegin());
                }
            }
        }
        return null;
    }
}
