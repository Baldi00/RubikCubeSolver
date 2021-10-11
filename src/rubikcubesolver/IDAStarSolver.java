/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Andrea
 */
public class IDAStarSolver {
    private final int MAX_ITERATIONS = 30;
    
    private Cube cube;
    private Cube goal;
    private int count;
    private Node[] pool;
    private int cutoff;
    private int nextCutoff;

    public IDAStarSolver(Cube cube) {
        this.cube = cube;
        this.count = 0;
        goal = new Cube();
        pool = new Node[MAX_ITERATIONS];
        for(int i=0; i<MAX_ITERATIONS; i++){
            pool[i] = new Node(new Cube(),i);
        }
    }
    
    public void solve() {
        cutoff = cube.manhattanDistanceInRotationsSum();
        System.out.println(cutoff);
        nextCutoff = Integer.MAX_VALUE;
        while(true){
            Node solution = dephtFirstSearchRecursive(new Node(cube,0));
            if(solution != null) {
                System.out.println("Solution can be made in " + solution.getDepthLevel() + " moves");
                System.out.println("Solution found after " + count + " rotations");
                return;
            } else {
                cutoff = nextCutoff;
                System.out.println(cutoff);
                nextCutoff = Integer.MAX_VALUE;
            }
        }
    }
    
    private Node dephtFirstSearchRecursive(Node n) {
        int costOfTheNode = costOfTheNode(n);
        //System.out.println(n.getDepthLevel());
        if(n.getCube().isSolved()){
            return n;
        }else if(costOfTheNode <= cutoff){
            for(int i=0; i<12; i++){
                boolean clockwise;
                clockwise = i%2==0;
                pool[n.getDepthLevel()+1].getCube().setCube(cube.rotateFace(RubikOrientation.values()[i/2], clockwise));
                cube.rotateFace(RubikOrientation.values()[i/2], !clockwise);
                count++;
                Node result = dephtFirstSearchRecursive(pool[n.getDepthLevel()+1]);
                if(result != null){
                    return result;
                }
            }
            return null;
        }else{
            if(costOfTheNode < nextCutoff){
                nextCutoff = costOfTheNode;
            }
            return null;
        }
    }
    
    private int costSoFar(Node n) {
        return n.getDepthLevel();
    }
    
    private int heuristicFunction(Node n) {
        return n.getCube().manhattanDistanceInRotationsSum();
    }
    
    private int costOfTheNode(Node n) {
        return costSoFar(n) + heuristicFunction(n);
    }
}
