package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import myLib.utils.FileIO;

/**
 *
 * @author tadaki
 */
public class NetworkFile {
    //改行文字（OSによる差を考慮）

    static final private String NL = System.getProperty("line.separator");

    //******    pajek用    *********************
    /**
     * pajek 用のデータを作成
     *
     * @param network
     * @return
     */
    public static String generatePajekData(AbstractNetwork network) {
        List<Node> nodes = network.getNodes();
        StringBuilder sb = new StringBuilder();
        //頂点一覧を文字列化
        sb.append("*Vertices ").append(nodes.size()).append(NL);
        nodes.stream().forEach((node) -> {
            int i = nodes.indexOf(node) + 1;
            sb.append(i).append(" ").append("\"").append(node.label).
                    append("\"").append(NL);
        });
        if (network.directed) {
            sb.append("*Arcs").append(NL);
            for (Node from : nodes) {
                List<Edge> edges = network.getEdges(from);
                for (Edge edge : edges) {
                    Node to = edge.getTo();              
                    int i = nodes.indexOf(from);
                    int j = nodes.indexOf(to);
                    sb.append(i+1).append(" ");
                    sb.append(j+1).append(NL);
                }
            }
        } else {
            sb.append("*Edges").append(NL);
            for (Node from : nodes) {
                List<Edge> edges = network.getEdges(from);
                for (Edge edge : edges) {
                    Node to = edge.getAnotherEnd(from);
                    int i = nodes.indexOf(from);
                    int j = nodes.indexOf(to);
                    if (j > i) {
                    sb.append(i+1).append(" ");
                    sb.append(j+1).append(NL);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * pajek 用のデータを出力
     *
     * @param filename
     * @param network
     * @throws java.io.IOException
     */
    public static void outputPajekData(
            String filename, AbstractNetwork network) throws IOException {
        String str = generatePajekData(network);
        try (BufferedWriter writer = FileIO.openWriter(filename)) {
            writer.write(str);
            writer.flush();
        }
    }
}
