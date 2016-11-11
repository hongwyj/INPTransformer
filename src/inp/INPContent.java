package inp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class INPContent {
	private double shearModulus;
	private double poissonsRatio;
	private List<Node> nodes;
	private List<Element> elements;

	public INPContent() {
		nodes = new ArrayList<Node>();
		elements = new ArrayList<Element>();
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public Node getNode(int id) {
		return nodes.get(id - 1);
	}

	public void addElement(Element element) {
		elements.add(element);
	}

	public Element getElement(int id) {
		return elements.get(id - 1);
	}

	public double getShearModulus() {
		return shearModulus;
	}

	public void setYoungsModulus(double youngsModulus) {
		assert poissonsRatio != 0.0;
		this.shearModulus = youngsModulus / 2 / (1 + poissonsRatio);
	}

	public double getPoissonsRatio() {
		return poissonsRatio;
	}

	public void setPoissonsRatio(double poissonsRatio) {
		this.poissonsRatio = poissonsRatio;
	}

	public void output(String filename) {
		try {
			final String TAB = "    ";
			
			FileWriter out = new FileWriter(filename);
			BufferedWriter writer = new BufferedWriter(out);
			
			writer.write("No._material_props:    2");
			writer.newLine();
			writer.write(TAB + "Shear_modulus:" + TAB + shearModulus);
			writer.newLine();
			writer.write(TAB + "Poissons_ratio:" + TAB + poissonsRatio);
			writer.newLine();
			
			writer.write("No._coords_per_node:" + TAB + nodes.get(0).getCoordinates().size());
			writer.newLine();
			writer.write("No._DOF_per_node:" + TAB + nodes.get(0).getCoordinates().size());
			writer.newLine();
			writer.write("No._nodes:" + TAB + nodes.size());
			writer.newLine();
			
			writer.write("Nodal_coords:");
			writer.newLine();
			for (Node node : nodes) {
				final List<Double> coordinates = node.getCoordinates();
				for (int i = 0; i < coordinates.size(); i++) {
					if (i != 0) {
						writer.write(TAB);
					}
					writer.write(coordinates.get(i).toString());
				}
				writer.newLine();
			}
			
			writer.write("No._elements:" + TAB + elements.size());
			writer.newLine();
			writer.write("Max_no._nodes_on_any_one_element:" + TAB + elements.get(0).getNodes().size());
			writer.newLine();
			
			writer.write("element_identifier; no._nodes_on_element; connectivity:");
			writer.newLine();
			for (Element element : elements) {
				final List<Node> nodes = element.getNodes();
				writer.write(TAB + "1");
				writer.write(TAB + nodes.size());
				for (Node node : nodes) {
					writer.write(TAB + node.getId());
				}
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
