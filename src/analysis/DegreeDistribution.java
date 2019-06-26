package analysis;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;
import myLib.utils.Utils;
import network.*;

/**
 * 次数分布
 *
 * @author tadaki
 */
public class DegreeDistribution {

    private final AbstractNetwork network;//分析対象のネットワーク

    /**
     * コンストラクタ
     *
     * @param network 分析対象
     */
    public DegreeDistribution(AbstractNetwork network) {
        this.network = network;
    }

    /**
     * 次数分布を取得
     *
     * @return
     */
    public List<Point2D.Double> getDistribution() {
        List<Point2D.Double> points = Utils.createList();
        List<Point> hist = getHistgram();
        int sum = 0;
        for (Point p : hist) {
            sum += p.y;
        }
        for (Point p : hist) {
            points.add(new Point2D.Double(
                    (double) p.x, (double) p.y / sum));
        }
        return points;
    }

    public void printDistribution(String filename) throws IOException {
        try (BufferedWriter writer = FileIO.openWriter(filename)) {
            List<Point2D.Double> points = getDistribution();
            for (Point2D.Double p : points) {
                FileIO.writeSSV(writer, p.x, p.y);
            }
        }
    }

    /**
     * ヒストグラム生成
     *
     * @return
     */
    public int[] getHistgramArray() {
        int hist[] = new int[network.getNumNode()];
        for (Node node : network.getNodes()) {
            int k = network.getEdges(node).size();
            hist[k]++;
        }
        return hist;
    }

    public List<Point> getHistgram() {
        int h[] = getHistgramArray();
        List<Point> hist = Utils.createList();
        for (int i = 0; i < h.length; i++) {
            if (h[i] > 0) {
                hist.add(new Point(i, h[i]));
            }
        }
        return hist;
    }

    static public void main(String args[]) {
        AbstractNetwork network = new AbstractNetwork("testNetwork") {
            @Override
            public void createNetwork() {
                int n = 10;
                for (int i = 0; i < n; i++) {
                    addNode(new Node(String.valueOf(i)));
                }
                connectNodes(nodes.get(0), nodes.get(1));
                connectNodes(nodes.get(0), nodes.get(2));
                connectNodes(nodes.get(0), nodes.get(3));
                connectNodes(nodes.get(1), nodes.get(2));
                connectNodes(nodes.get(1), nodes.get(4));
                connectNodes(nodes.get(1), nodes.get(5));
                connectNodes(nodes.get(1), nodes.get(6));
                connectNodes(nodes.get(2), nodes.get(6));
                connectNodes(nodes.get(3), nodes.get(8));
                connectNodes(nodes.get(4), nodes.get(5));
                connectNodes(nodes.get(4), nodes.get(7));
                connectNodes(nodes.get(6), nodes.get(9));
            }

        };
        network.createNetwork();
        DegreeDistribution distribution = new DegreeDistribution(network);
        int hist[] = distribution.getHistgramArray();
        for (int i = 1; i < hist.length; i++) {
            System.out.println(i + " " + hist[i]);
        }
    }
}
