/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.antinori.life.gdx;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class LocationGraph implements IndexedGraph<Location> {

    protected Array<Location> nodes;

    public LocationGraph(Array<Location> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int getIndex(Location node) {
        return node.getIndex();
    }

    @Override
    public Array<Connection<Location>> getConnections(Location fromNode) {
        return fromNode.getConnections();
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }
}
