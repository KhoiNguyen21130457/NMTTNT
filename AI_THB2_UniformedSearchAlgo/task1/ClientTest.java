package AI_THB2_UniformedSearchAlgo.task1;

import java.util.List;

public class ClientTest {

    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeJ = new Node("J");

        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);

        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);

        nodeD.addEdge(nodeH, 7);

        nodeE.addEdge(nodeG, 6);
        nodeE.addEdge(nodeJ, 2);

        nodeG.addEdge(nodeJ, 5);

        nodeB.addEdge(nodeA, 2);
        nodeB.addEdge(nodeG, 6);

        nodeC.addEdge(nodeB, 3);
        nodeC.addEdge(nodeF, 2);

        nodeF.addEdge(nodeG, 1);
        nodeF.addEdge(nodeJ, 3);

        ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
        ISearchAlgo algo2 = new DepthFirstSearchAlgo();
        Node result = algo2.treeSearch(nodeS, "C","J");
        List<String> path = NodeUtils.printPath(result);
        for (int i = 0; i < path.size(); i++) {
           if (i== path.size()-1) System.out.print(path.get(i));
           else System.out.print(path.get(i) + " -> ");
        }
        System.out.println();
    }
}