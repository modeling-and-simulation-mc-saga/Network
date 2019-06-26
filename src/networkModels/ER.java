package networkModels;

import java.io.IOException;
import network.*;

/**
 * Erdos-Renni random network
 *
 * @author tadaki
 */
public class ER extends AbstractNetwork {

    private final int numNodes;//頂点の数
    private final int numEdges;//辺の数

    /**
     * コンストラクタ
     *
     * @param numNodes
     * @param numEdges
     */
    public ER(int numNodes, int numEdges) {
        super("ER(" + String.valueOf(numNodes) + "," 
                + String.valueOf(numEdges) + ")");
        this.numNodes = numNodes;
        this.numEdges = numEdges;
    }

    @Override
    public void createNetwork() {
        createNodes();
        createEdges();
    }

    /**
     * numNodes 個の頂点を生成
     */
    private void createNodes() {
        for (int i = 0; i < numNodes; i++) {
            addNode(new Node(String.valueOf(i)));
        }
    }

    /**
     * random に辺を生成
     */
    private void createEdges() {
        for (int i = 0; i < numEdges; i++) {
            int x = (int) (numNodes * Math.random());
            int y = (int) (numNodes * Math.random());
            connectNodes(nodes.get(x), nodes.get(y));
        }
    }

    /**
     * サンプルを生成
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        int n = 100;
        int numEdges = 500;
        ER er = new ER(n, numEdges);
        er.createNetwork();
        NetworkFile.outputPajekData("er.net", er);
    }
}
