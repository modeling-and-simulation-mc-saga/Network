package networkModels;

import java.io.IOException;
import java.util.List;
import myLib.utils.Utils;
import network.*;

/**
 * Barabasi-Albert model
 *
 * @author tadaki
 */
public class BA extends AbstractNetwork {

    private final int numNodes;//頂点の数
    private final int numEdgesFromNewNode;//新規に生成する頂点から出る辺の数
    //このリスト中に頂点が次数回登場する
    //つまり、ある頂点vの次数がkならば、vがk回現れる
    private List<Node> targetList;

    /**
     * コンストラクタ
     *
     * @param numNodes
     * @param numEdgesFromNewNode
     */
    public BA(int numNodes, int numEdgesFromNewNode) {
        super("BA(" + String.valueOf(numNodes) + "," 
                + String.valueOf(numEdgesFromNewNode) + ")");
        this.numNodes = numNodes;
        this.numEdgesFromNewNode = numEdgesFromNewNode;
        if (numNodes < numEdgesFromNewNode) {
            throw new UnsupportedOperationException(
                    "Can not create such network.");
        }
        targetList = Utils.createList();
    }

    @Override
    public void createNetwork() {
        initialize();
        for (int i = numEdgesFromNewNode + 1; i < numNodes; i++) {
            addNewNode();
        }
    }

    /**
     * 一つの頂点を追加する
     */
    private void addNewNode() {
        Node node = new Node(String.valueOf(getNumNode() + 1));
        addNode(node);
        List<Node> target = Utils.createList();
        int nn = targetList.size();
        //接続先頂点を選び、targetへ追加
        for (int i = 0; i < numEdgesFromNewNode; i++) {
            int k = (int) (nn * Math.random());
            target.add(targetList.get(k));
        }
        //辺の生成
        for (Node t : target) {
            connectNodes(node, t);
            targetList.add(t);
            targetList.add(node);
        }
    }

    /**
     * 初期化
     *
     */
    private void initialize() {
        //m個の頂点を生成
        for (int i = 0; i <= numEdgesFromNewNode; i++) {
            addNode(new Node(String.valueOf(i)));
        }
        //頂点相互を二重に結ぶ
        for (int i = 0; i <= numEdgesFromNewNode; i++) {
            Node x = nodes.get(i);
            for (int j = 0; j <= numEdgesFromNewNode; j++) {
                Node y = nodes.get(j);
                connectNodes(x, y);
                targetList.add(x);
                targetList.add(y);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        int n = 100;
        int m = 1;
        BA ba = new BA(n, m);
        ba.createNetwork();
        NetworkFile.outputPajekData("ba.net", ba);
    }

}
