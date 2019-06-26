package graphAlgorithms;

import java.util.List;
import myLib.utils.Utils;
import network.AbstractNetwork;
import network.Edge;
import network.Node;

/**
 * 深さ優先探索
 * @author tadaki
 */
public class DepthFirstSearch extends AbstractSearch {

    public DepthFirstSearch(AbstractNetwork network) {
        super(network);
    }

    @Override
    public List<Node> search(Node start) {
        visitedNodes = Utils.createList();
        tree = Utils.createList();
        searchSub(start);
        return visitedNodes;
    }

    private void searchSub(Node v) {
        List<Edge> edges = network.getEdges(v);
        if (edges != null) {

        
        
        
        
        
        }

    }

    public static void main(String args[]) {
        AbstractNetwork network = new AbstractNetwork("network", true) {
            @Override
            public void createNetwork() {
                for (int i = 0; i < 10; i++) {
                    Node n = new Node(String.valueOf(i));
                    this.addNode(n);
                }

                connectNodes(nodes.get(0), nodes.get(1), "a0");
                connectNodes(nodes.get(0), nodes.get(2), "a1");
                connectNodes(nodes.get(0), nodes.get(3), "a2");
                connectNodes(nodes.get(1), nodes.get(3), "a3");
                connectNodes(nodes.get(1), nodes.get(5), "a4");
                connectNodes(nodes.get(1), nodes.get(6), "a5");
                connectNodes(nodes.get(3), nodes.get(2), "a6");
                connectNodes(nodes.get(4), nodes.get(1), "a7");
                connectNodes(nodes.get(5), nodes.get(4), "a8");
                connectNodes(nodes.get(6), nodes.get(2), "a9");
                connectNodes(nodes.get(6), nodes.get(8), "a10");
                connectNodes(nodes.get(7), nodes.get(4), "a11");
                connectNodes(nodes.get(8), nodes.get(4), "a12");
                connectNodes(nodes.get(8), nodes.get(5), "a13");
                connectNodes(nodes.get(8), nodes.get(7), "a14");
                connectNodes(nodes.get(8), nodes.get(9), "a15");
                connectNodes(nodes.get(9), nodes.get(2), "a16");
            }
        };
        network.createNetwork();
        DepthFirstSearch dfs = new DepthFirstSearch(network);
        List<Node> nodes = dfs.search(network.getNodes().get(0));
        List<Edge> edges = dfs.getTree();
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
}
