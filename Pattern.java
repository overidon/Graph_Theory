
/***
 * @author Tyler Stansfield Jaggers - October 24th, 2020
 * Basic Node structure for Graph Theory
 * convert to file class public class Pattern as necessary - 
 * This is intended to be used as part of another file
 * <p>
 */

class Pattern {

	boolean isStar = false;
	boolean shouldDelete = false;
	int index;

	HashSet<Integer> P = new HashSet<Integer>();
	/* @param Node node -> the node that should have the same index

	 */
	public Pattern (int index, Node node) {

		this.index = index;

		HashSet<Integer> rawPatternData = node.allConnections;

		for ( Integer i : rawPatternData) P.add(i);

	// end of the Pattern constructor
	}

	public void scanForDuplicates( ArrayList<Pattern> patterns) {
		boolean debug = true;

		// go through all the other patterns in the list remove the dupes!
		int S = patterns.size();
		for (int i = this.index + 1; i < S; i++) {
			HashSet<Integer> otherPat = patterns.get(i).P;
			boolean dupe = this.P.containsAll(patterns.get(i).P);

			if (debug) {
				System.out.println("\nMy pattern is: " + this.P);
				System.out.println("OTHER pattern is: " + patterns.get(i).P);
				System.out.println("DUPE? " + dupe);
			}

			if ( dupe) patterns.get(i).shouldDelete = true;
		}
	// end of the scanForDuplicates method
	}

	public void determineIfStar(ArrayList<Node> nodes) {

		// TODO -> check against the nodes and see if any of our
		// elements in this pattern correlates to nodes that
		// have 1 core or a single edge
		// if both criteria are met. this pattern is a star!

		int cores = 0;

		int S = nodes.size();

		for (Integer i : this.P) {

			if ( nodes.get(i).isCore)cores++;
			if ( cores >= 2) {
				this.isStar = false;
				return;
			}
		}

		if (this.P.size() >= 2) {
			this.isStar = true;
		}

	}


	public String toString() {
		return "PATTERN INDEX: " + index + " \n PATTERN: " +
				P + " \n IS STAR: " + isStar + " \n Tagged for deletion: " + shouldDelete;
	}
// end of the Pattern class
}
