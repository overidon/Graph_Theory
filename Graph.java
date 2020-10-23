public class Graph {
   
  static void displayAdjMatrix(boolean [][] arr) {

		System.out.println("***** Displaying the Adjacency Matrix *******\n\n");

		int W = arr[0].length;
		int H = arr.length;

		String header  = "[**]-";
		String header2 = " ||  ";
		for (int i = 0; i < W; i++) {
			header2 += " || ";
			if ( i < 10) {
				header  += "[0" + i +"]";

			} else {
				header += "[" + i +"]";
			}
		}

		System.out.println(header);
		System.out.println(header2);
		for (int r = 0; r < H; r++) {

			String line = "";
			// MARGIN for the column
			if ( r < 10) {
				line += "[0" + r +"]-";
			} else {
				line += "[" + r +"]-";
			}
			// data for the column
			for (int c = 0; c < W; c++) {

				boolean val = arr[r][c];
				line += val ? "[11]" : "[  ]";
			}
			System.out.println(line);
		}

	// end of the display method 
	}


   
   
     /***
      * @author Tyler Stansfield Jaggers - October 23rd, 2020
      * @see Node - This method is meant to work with Node class
      * Modifies and fills an ArrayList of nodes (@nodes) based on the
      * true values of a square adjacency matrix
      * <p>
      * This method will create new nodes when appropriate
      *
      * @param  adj  adjacency matrix - Should be square
      * @param  nodes ArrayList of nodes - should be passed as a reference
      * @param  L     adjacency matrix side length
     */
    void prepInitialNodes(boolean [][] adj, ArrayList<Node>nodes, int L) {
      for (int r = 0; r < L; r++) {

        Node node = new Node(r);

        for (int c = 0; c < L; c++) {

          boolean found = adj[r][c];
          if ( found ) node.addLink(c);
        }

        nodes.add(node);
      }
    // end of the prepInitialNodes method
    }
    
    
    
  // end of the Graph class 
  }
  

/***
 * @author Tyler Stansfield Jaggers - October 23rd, 2020
 * Basic Node structure for Graph Theory
 * <p>
 */
class Node {

	int index;

	ArrayList<Integer> links = new ArrayList<>();

	public Node (int index) {
		this.index = index;
	}


	public void addLink(int i) {

		if ( !links.contains(i))this.links.add(i);
	}

	public String toString() {
		return "*** Node: " + this.index + " *-> LINKAGES: " + this.links;
	}

// end of the Node class
}
