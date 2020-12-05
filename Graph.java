public class Graph {
	
  /*
  	12/5/2020 -> Added cleanMerge
	This function will merge patterns that share any connections in their HashSet of node indices
	The goal is to clean the list of patterns to only have complete structures. 
	After running this function the only patterns that should remain are ones that do not share any nodes. 
	Any other patterns would be merged into a pattern. This can either make a pattern larger of have the 
	effect of 'removing' duplicates. 
  */
  void cleanMerge(ArrayList<Pattern> patterns, int index) {

	boolean debug = false;

	int S = patterns.size(); 

	if ( index >= S) return;


	Pattern target = patterns.get(index);
	Pattern other = null; 


	if (debug)System.out.println("The size of the patterns list is: " + S);
	if (debug) System.out.println("Pattern Index is: " + index);

	HashSet<Integer> system = target.P; 

	// this is my list of connections.... for the pattern
	if (debug) System.out.println("#### SYSTEM: " + system);

	int tag = -1;

	for (int i = index + 1; i < S; i++) {

		Pattern otherPattern = patterns.get(i);
		HashSet<Integer> otherSystem = otherPattern.P; 

		for (Integer k: otherSystem) {
			if ( system.contains(k)) {
				tag = i;
				i = S;
				other = otherPattern;
				break;

			}
		}
	}

	if (debug) System.out.println("#### The tag is: " + tag);

	if ( tag != -1) {
		target.P.addAll(other.P);
		patterns.remove(tag);
		cleanMerge(patterns, index); 
	} else {
		cleanMerge(patterns, index + 1); 
	}

	// end of the cleanMerge method 
	}
	
void shiftSystemLeft(boolean[][] arr, ArrayList<Integer> crush){

    int L = arr.length;
    int count = 0;
    int S = crush.size();

    while (count < S) {
        int crusher = crush.get(count);

        for (int r = 0; r < L; r++) {
            for (int c = crusher - count; c < L -1; c++) {
                arr[r][c] = arr[r][c + 1];
                arr[r][c + 1] = false;
            }
        }
        count++;
    }
}

void shiftSystemUp(boolean[][] arr, ArrayList<Integer> crush){

    boolean debug = true;
    int L = arr.length;
    int count = 0;
    int S = crush.size();

    while (count < S) {
        int crusher = crush.get(count);

        if ( debug) System.out.println("The crusher is: " + crusher);
        for (int c = 0; c < L; c++) {
            for (int r = crusher - count; r < L -1; r++) {
                arr[r][c] = arr[r + 1][c];
                arr[r + 1][c] = false;
            }
        }
        count++;
    }
}

// this merges two rows to be one 
// and then makes the rowB all false 
void squishRow(boolean[][] arr, int rowA, int rowB) {

    int L = arr.length;
    for (int c = 0; c < L; c++){
        
        // what is the value below one unit!
        boolean valBot = arr[rowB][c];
        
        // any true value below will override the top value
        arr[rowA][c] |= valBot;
        
        if (c == rowA) arr[rowA][c] = false;
        
        arr[rowB][c] = false;
    }
    
}

void squishCol(boolean[][] arr, int colA, int colB) {

    int L = arr.length;
    for (int row = 0; row < arr.length; row++){
        boolean valBot = arr[row][colB];
        arr[row][colA] |= valBot;
        if (row == colA){
            arr[row][colA] = false;
        }
        arr[row][colB] = false;
    }

}
    
   
   
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
 * UPDATED - November 5th, 2020
 * Basic Node structure for Graph Theory
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

