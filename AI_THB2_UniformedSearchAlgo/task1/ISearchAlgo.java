package AI_THB2_UniformedSearchAlgo.task1;

public interface ISearchAlgo {
	public Node execute(Node root, String goal);// find the path from root node to the goal node

	public Node execute(Node root, String start, String goal); // find the path from start node to the goal node

	public Node treeSearch(Node root, String goal);

	public Node treeSearch(Node root, String start, String goal);
}
