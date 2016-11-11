package inp;

import java.util.ArrayList;
import java.util.List;

public class Element {
	private final int id;
	private List<Node> nodes;

	public Element(int id) {
		this.id = id;
		nodes = new ArrayList<Node>();
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public int getId() {
		return id;
	}
	
	public final List<Node> getNodes() {
		return nodes;
	}
}
