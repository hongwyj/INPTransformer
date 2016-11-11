package inp;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private final int id;
	private List<Double> coordinates;
	
	public Node(int id) {
		this.id = id;
		coordinates = new ArrayList<Double>();
	}
	
	public void addCoordinate(double cod) {
		coordinates.add(cod);
	}

	public int getId() {
		return id;
	}
	
	public final List<Double> getCoordinates() {
		return coordinates;
	}
}
