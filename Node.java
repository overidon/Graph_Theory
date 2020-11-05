
/***
 * @author Tyler Stansfield Jaggers - October 23rd, 2020
 * UPDATED - November 5th, 2020
 * Basic Node structure for Graph Theory
 * This is just an inner or non-file class -> integrate as a public class Node if necessary
 * <p>
 */

class Node {

	int index;
	boolean isCore = false;
	boolean isCenter = false;

	ArrayList<Integer> links = new ArrayList<>();
	HashSet<Integer> allConnections = new HashSet<Integer>();

	public Node (int index) {
		this.index = index;
	}

	public void addLink(int i) {
		if ( !links.contains(i) && this.index != i ) this.links.add(i);


	}

	void findAllConnections(ArrayList<Node> nodes) {

		// for every link in this node
		for (Integer link : links) {
			// add that link to its allConnections system
			allConnections.add(link);

			// Then dive deeper into the permutations
			Node conn = nodes.get(link);

			// VERBOSE -> for (Integer connLink : conn.links) this.allConnections.add(connLink);
			allConnections.addAll(conn.links);
		}


		if ( links.size() + 1 == nodes.size()) this.isCenter = true;
		// end of the findAllConnections method
	}

	public String toString() {
		return "\n***Node: " + index + "\n LINKAGES: " + links +
				"\n isCore: " + isCore + "\n ALL CONNECTIONS: " + allConnections +
				"\n isCenter: " + isCenter;
	}
// end of the Node class
}
