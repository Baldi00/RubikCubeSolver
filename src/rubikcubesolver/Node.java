/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

/**
 *
 * @author Andrea
 */
public class Node {
    private Cube cube;
    private int depthLevel;

    public Node(Cube cube, int depthLevel) {
        this.cube = cube;
        this.depthLevel = depthLevel;
    }

    public Cube getCube() {
        return cube;
    }

    public int getDepthLevel() {
        return depthLevel;
    }
}
