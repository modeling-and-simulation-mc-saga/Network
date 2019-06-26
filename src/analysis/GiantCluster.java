package analysis;

import graphAlgorithms.BreadthFirstSearch;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import myLib.utils.FileIO;
import myLib.utils.Utils;
import network.*;

/**
 *
 * @author tadaki
 */
public class GiantCluster {

    private final AbstractNetwork network;
    private List<List<Node>> clusters;

    /**
     * 改行コード
     */
    public static final String NL = System.getProperty("line.separator");

    public GiantCluster(AbstractNetwork network) {
        this.network = network;
    }

    /**
     * clusterへの分割
     *
     * @return cluster数
     */
    public int findClusters() {
        clusters = Utils.createList();
        List<Node> nodes = Utils.createList();
        network.getNodes().stream().forEach(
                node -> nodes.add(node)
        );
        while (!nodes.isEmpty()) {
            Node start = nodes.get(0);
            List<Node> checked = new BreadthFirstSearch(network).search(start);
            checked.stream().forEach(
                    n -> nodes.remove(n)
            );
            clusters.add(checked);
        }
        return clusters.size();
    }

    /**
     * 最大のclusterを求める
     *
     * @return
     */
    public List<Node> findLargestCluster() {
        List<Node> largest = Utils.createList();
        for (List<Node> nodes : clusters) {
            if (nodes.size() > largest.size()) {
                largest = nodes;
            }
        }
        return largest;
    }

    /**
     * 最大のclusterを大きさを求める
     *
     * @return
     */
    public int findLargestClusterSize() {
        return findLargestCluster().size();
    }


    /**
     * クラスタの情報をファイルへ出力
     *
     * @param clusters
     * @param filename
     * @throws IOException
     */
    public static void showClusters(List<List<Node>> clusters, String filename)
            throws IOException {
        try (BufferedWriter out = FileIO.openWriter(filename)) {
            String str = clusters2String(clusters);
            out.write(str);
            out.newLine();
        }
    }

    /**
     * クラスタの情報を文字列へ変換
     *
     * @param clusters
     * @return
     */
    public static String clusters2String(List<List<Node>> clusters) {
        StringBuilder sb = new StringBuilder();
        for (List<Node> nodes : clusters) {
            sb.append("{");
            nodes.stream().forEach(
                    n -> sb.append(n.toString()).append(",")
            );
            int last = sb.length() - 1;
            sb.deleteCharAt(last);
            sb.append("}");
            sb.append(NL);
        }
        return sb.toString();
    }

    /**
     * clusterを返す
     *
     * @return
     */
    public List<List<Node>> getClusters() {
        return clusters;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ///テスト用のネットワークを定義
        AbstractNetwork network = new AbstractNetwork("network") {
            @Override
            public void createNetwork() {
                for (int i = 0; i < 10; i++) {
                    Node n = new Node(String.valueOf(i));
                    this.addNode(n);
                }

                connectNodes(nodes.get(0), nodes.get(1));
                connectNodes(nodes.get(0), nodes.get(2));
                connectNodes(nodes.get(0), nodes.get(3));
                connectNodes(nodes.get(1), nodes.get(4));
                connectNodes(nodes.get(5), nodes.get(6));
                connectNodes(nodes.get(6), nodes.get(7));
            }
        };
        network.createNetwork();
        GiantCluster gc = new GiantCluster(network);
        int k = gc.findClusters();
        System.out.println(k);
        GiantCluster.showClusters(gc.getClusters(), "GiantCluster.txt");
    }

}
