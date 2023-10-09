package AI_THB2_UniformedSearchAlgo.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

    @Override
    public Node execute(Node root, String goal) {
        Stack<Node> frontier = new Stack<Node>();
        List<Node> exploredSet = new ArrayList<>();
        frontier.add(root);
        int index = 0;
        while (!frontier.isEmpty()){
            Node current = frontier.remove(0);
            if(current.getLabel().equals(goal)) return current;
            exploredSet.add(current);
            List<Node> children = current.getChildrenNodes();
            for (int i=0; i<children.size(); i++) {
                if(!frontier.contains(children.get(i)) && !exploredSet.contains(children.get(i))){
                    frontier.add(index++, children.get(i));
                    children.get(i).setParent(current);
                }
            }
            index = 0;
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Stack<Node> frontier = new Stack<Node>();
        List<Node> exploredSet = new ArrayList<>();
        frontier.add(root);
        int index = 0;
        boolean isStarted = false;
        while (!frontier.isEmpty()){
            Node current = frontier.remove(0);
            if(current.getLabel().equals(goal) && isStarted) return current;
            if(current.getLabel().equals(start)) {
                isStarted = true;
                frontier.clear();
                exploredSet.clear();
                current.setParent(null);
            }
            exploredSet.add(current);
            List<Node> children = current.getChildrenNodes();
            for (int i=0; i<children.size(); i++) {
                if(!frontier.contains(children.get(i)) && !exploredSet.contains(children.get(i))){
                    frontier.add(index++, children.get(i));
                    children.get(i).setParent(current);
                }
            }
            index = 0;
        }
        return null;
    }

    @Override
    public Node treeSearch(Node root, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        int index = 0;
        while (!frontier.isEmpty()){
            Node current = frontier.remove(0);
            if(current.getLabel().equals(goal)) return current;
            List<Node> children = current.getChildrenNodes();
            for (int i=0; i<children.size(); i++) {
               frontier.add(index++, children.get(i));
               children.get(i).setParent(current);
            }
            index = 0;
        }
        return null;
    }

    @Override
    public Node treeSearch(Node root, String start, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        int index = 0;
        boolean isStarted = false;
        while (!frontier.isEmpty()){
            Node current = frontier.remove(0);
            if(current.getLabel().equals(goal) && isStarted) return current;
            if(current.getLabel().equals(start)) {
                isStarted = true;
                frontier.clear();
                current.setParent(null);
            }
            List<Node> children = current.getChildrenNodes();
            for (int i=0; i<children.size(); i++) {
               frontier.add(index++, children.get(i));
               children.get(i).setParent(current);
            }
            index = 0;
        }
        return null;
    }
}