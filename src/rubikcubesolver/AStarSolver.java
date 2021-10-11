/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Andrea
 */
public class AStarSolver {
    
    private final Cube cube;
    private PriorityQueue<Node> frontier;
    private int count;

    public AStarSolver(Cube cube) {
        this.cube = cube;
        this.count = 0;
        frontier = new PriorityQueue<>(10000, new NodeComparator());
    }
    
    public void solve() {
        frontier.add(new Node(cube, 0));
        while(true) {
            Node extracted = frontier.poll();
            System.out.println(count + " " + extracted.getCube().manhattanDistanceInRotationsSum());
            if(extracted.getCube().isSolved()){
                System.out.println("Solution can be made in " + extracted.getDepthLevel() + " moves");
                System.out.println("Solution found after " + count + " expansions");
                return;
            }else{
                for(int i=0; i<6; i++){
                    Cube c = new Cube(extracted.getCube());
                    Cube c2 = new Cube(extracted.getCube());
                    c.rotateFace(RubikOrientation.values()[i], true);
                    c2.rotateFace(RubikOrientation.values()[i], false);
                    frontier.add(new Node(new Cube(c), extracted.getDepthLevel()+1));
                    frontier.add(new Node(new Cube(c2), extracted.getDepthLevel()+1));
                }
                count++;
            }
        }
    }
}
