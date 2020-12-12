/***
 * @author Tyler Stansfield Jaggers - October 23rd, 2020
 * UPDATED - November 5th, 2020
 * UPDATE - November 13th, 2020
 *  Added a system for a node to create a clean copy
 *  itself
 *  Update -> December 12th, 2020 -> System now incorporates its own index per node
 * Basic Node structure for Graph Theory
 * <p>
 */

class Node {

    int index;
    boolean isCore = false;
    boolean isCenter = false;

    boolean tagged = false;

    ArrayList<Integer> links = new ArrayList<>();
    HashSet<Integer> allConnections = new HashSet<Integer>();

    HashSet<Integer> system = new HashSet<Integer>();

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

        this.system.addAll(this.links);
        this.system.add(this.index);


        if ( links.size() + 1 == nodes.size()) this.isCenter = true;
        // end of the findAllConnections method
    }

    // Getters and setters for primitive properties
    public boolean getCoreStatus() {
        return this.isCore;
    }

    public boolean getCenterStatus() {
        return this.isCenter;
    }

    public void setCoreStatus(boolean status) {
        this.isCore = status;
    }

    public void setCenterStatus(boolean status) {
        this.isCenter = status;
    }

    /*
    public String toString() {
        return "\n\n***Node: " + index + "\n LINKAGES: " + links +
                "\n isCore: " + isCore + "\n ALL CONNECTIONS: " + allConnections +
                "\n isCenter: " + isCenter;
    }
    */

    /*
    public String toString() {
        return "\n\n***Node: " + index + "\n LINKAGES: " + links +
                "\n isCore: " + isCore ;
    }
    */
    public String toString() {
        return "***Node: " + index + " -> SYSTEM: " + system;
    }
// end of the Node class
}
