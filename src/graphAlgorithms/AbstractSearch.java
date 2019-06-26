/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphAlgorithms;

import java.util.List;
import network.AbstractNetwork;
import network.Edge;
import network.Node;

/**
 *
 * @author tadaki
 */
public abstract class AbstractSearch {
    
    protected List<Node> visitedNodes;
    protected List<Edge> tree;
    protected final AbstractNetwork network;

    public AbstractSearch(AbstractNetwork network) {
        this.network=network;
    }

    /**
     * 始点を指定
     * @param start
     * @return
     */
    public abstract List<Node> search(Node start);

    public List<Edge> getTree() {
        return tree;
    }
    
}
