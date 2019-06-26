package network;

import java.util.List;
import myLib.utils.Utils;

/**
 * 弧のクラス
 *
 * @author tadaki
 */
public class Edge {

    public final boolean directed;//向きの有無
    public final String label;
    private Node from;
    private Node to;
    protected double weight;

    /**
     * コンストラクタ
     *
     * @param label 弧のラベル
     * @param directed 向きの有無
     */
    public Edge(String label, boolean directed) {
        this.directed = directed;
        this.label = label;
    }

    /**
     * コンストラクタ（無向グラフの場合）
     *
     * @param label 弧のラベル
     */
    public Edge(String label) {
        this(label, false);
    }

    /**
     * 両端の頂点を設定する。有向グラフでは順序に意味がある
     *
     * @param from
     * @param to
     */
    public void setEnd(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    /**
     * 指定した頂点と反対側の頂点を返す
     *
     * @param node
     * @return
     */
    public Node getAnotherEnd(Node node) {
        if (node.equals(from)) {
            return to;
        }
        if (node.equals(to)) {
            return from;
        }
        return null;
    }

    public List<Node> getEnds() {
        List<Node> nodes = Utils.createList();
        nodes.add(from);
        nodes.add(to);
        return nodes;
    }

    /**
     * 指定した頂点が辺の一方に在るかを判定
     *
     * @param node
     * @return
     */
    public boolean hasEnd(Node node) {
        return node.equals(from) || node.equals(to);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return label;
    }

}
