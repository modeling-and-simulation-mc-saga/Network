package graphAlgorithms;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import myLib.utils.Utils;
import network.*;

/**
 * 幅優先探索
 * @author tadaki
 */
public class BreadthFirstSearch extends AbstractSearch {


    Queue<Node> queue;

    /**
     * 対象ネットワークを指定
     * @param network 
     */
    public BreadthFirstSearch(AbstractNetwork network) {
        super(network);
    }

    /**
     * 始点を指定
     * @param start
     * @return 
     */
    @Override
    public List<Node> search(Node start) {
        visitedNodes = Utils.createList();
        tree = Utils.createList();
        queue = new ConcurrentLinkedQueue<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            List<Edge> edges = network.getEdges(v);
            if (edges!=null) {
                for (Edge e : edges) {
                    Node w = e.getAnotherEnd(v);
                    if (!visitedNodes.contains(w) && !queue.contains(w)) {
                        queue.add(w);
                        tree.add(e);
                    }
                }
            }
            visitedNodes.add(v);
        }
        return visitedNodes;
    }


    public static void main(String args[]){
                AbstractNetwork network = new AbstractNetwork("network",true) {
            @Override
            public void createNetwork() {
                for (int i = 0; i < 10; i++) {
                    Node n = new Node(String.valueOf(i));
                    this.addNode(n);
                }

                connectNodes(nodes.get(0), nodes.get(1),"a0");
                connectNodes(nodes.get(0), nodes.get(2),"a1");
                connectNodes(nodes.get(0), nodes.get(3),"a2");
                connectNodes(nodes.get(1), nodes.get(3),"a3");
                connectNodes(nodes.get(1), nodes.get(5),"a4");
                connectNodes(nodes.get(1), nodes.get(6),"a5");
                connectNodes(nodes.get(3), nodes.get(2),"a6");
                connectNodes(nodes.get(4), nodes.get(1),"a7");
                connectNodes(nodes.get(5), nodes.get(4),"a8");
                connectNodes(nodes.get(6), nodes.get(2),"a9");
                connectNodes(nodes.get(6), nodes.get(8),"a10");
                connectNodes(nodes.get(7), nodes.get(4),"a11");
                connectNodes(nodes.get(8), nodes.get(4),"a12");
                connectNodes(nodes.get(8), nodes.get(5),"a13");
                connectNodes(nodes.get(8), nodes.get(7),"a14");
                connectNodes(nodes.get(8), nodes.get(9),"a15");
                connectNodes(nodes.get(9), nodes.get(2),"a16");
            }
        };
        network.createNetwork();
        BreadthFirstSearch bfs = new BreadthFirstSearch(network);
        List<Node> nodes = bfs.search(network.getNodes().get(0));
        List<Edge> edges = bfs.getTree();
        for(Edge e:edges){
            System.out.println(e);
        }
    }
}
