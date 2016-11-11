package inp;

import java.io.BufferedReader;
import java.io.FileReader;

public class INPTransformer {
	private final String filename;

	public INPTransformer(String filename) {
		this.filename = filename;
	}

	public void transform(String output) {
		try {
			FileReader in = new FileReader(filename);
			BufferedReader reader = new BufferedReader(in);

			INPContent content = new INPContent();

			String line = reader.readLine();
			while (!line.startsWith("*Node")) {
				line = reader.readLine();
			}

			// Read nodes
			line = reader.readLine();
			int numNodes = 0;
			while (!line.startsWith("*")) {
				numNodes++;
				line = line.trim();
				String[] strs = line.split(",*\\s+");
				assert numNodes == Integer.parseInt(strs[0]);

				Node node = new Node(numNodes);
				for (int i = 1; i < strs.length; i++) {
					node.addCoordinate(Double.parseDouble(strs[i]));
				}
				content.addNode(node);

				line = reader.readLine();
			}

			while (!line.startsWith("*Element")) {
				line = reader.readLine();
			}

			// Read elements
			line = reader.readLine();
			int numElements = 0;
			while (!line.startsWith("*")) {
				numElements++;
				line = line.trim();
				String[] strs = line.split(",*\\s+");
				assert numElements == Integer.parseInt(strs[0]);

				Element element = new Element(numElements);
				for (int i = 1; i < strs.length; i++) {
					element.addNode(content.getNode(Integer.parseInt(strs[i])));
				}
				content.addElement(element);
				
				line = reader.readLine();
			}
			
			while (!line.startsWith("*Material")) {
				line = reader.readLine();
			}
			
			line = reader.readLine();
			assert line.startsWith("*");
			line = reader.readLine();
			{
				line = line.trim();
				String[] strs = line.split(",*\\s+");
				content.setPoissonsRatio(Double.parseDouble(strs[1]));
				content.setYoungsModulus(Double.parseDouble(strs[0]));
			}

			reader.close();
			
			content.output(output);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
