/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.ArrayList;

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
        cube.randomize(500);
        
        IterativeDeepingSearchSolver solver = new IterativeDeepingSearchSolver(cube);
        solver.solve();
    }
    
}
