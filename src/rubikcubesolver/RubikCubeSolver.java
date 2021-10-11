/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Andrea
 */
public class RubikCubeSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cube cube = new Cube();
        /*cube.rotateFace(RubikOrientation.RIGHT, true);
        cube.rotateFace(RubikOrientation.LEFT, true);
        cube.rotateFace(RubikOrientation.LEFT, true);
        cube.rotateFace(RubikOrientation.UP, false);
        cube.printCube();*/
        cube.randomize(10);
        //cube.rotateFace(RubikOrientation.FRONT, true);
        /*PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
        
        frontier.add(new Node(new Cube(cube.rotateFace(RubikOrientation.FRONT, true)), 0));
        frontier.add(new Node(cube, 0));
        frontier.add(new Node(new Cube(cube.rotateFace(RubikOrientation.FRONT, true).rotateFace(RubikOrientation.FRONT, true)), 0));
        System.out.println(frontier.poll().getCube().manhattanDistanceInRotationsSum());
        System.out.println(frontier.poll().getCube().manhattanDistanceInRotationsSum());
        System.out.println(frontier.poll().getCube().manhattanDistanceInRotationsSum());*/

        
        
        IDAStarSolver solver = new IDAStarSolver(cube);
        solver.solve();
    }
    
}
