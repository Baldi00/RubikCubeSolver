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
public class IterativeDeepingSearchSolver {
    private final int MAX_ITERATIONS = 30;
    
    private Cube cube;
    private Stack<Node> stack;
    private int count;
    private HashMap<String, Cube> alreadyPassedStates;
    private Node[] pool;

    public IterativeDeepingSearchSolver(Cube cube) {
        this.cube = cube;
        this.stack = new Stack<>();
        this.count = 0;
        alreadyPassedStates = new HashMap<>();
        pool = new Node[MAX_ITERATIONS];
        for(int i=0; i<MAX_ITERATIONS; i++){
            pool[i] = new Node(new Cube(),i);
        }
    }
    
    public void solve() {
        Node solution = iterativeDeepingSearch();
        System.out.println("Solution can be made in " + solution.getDepthLevel() + " moves");
        System.out.println("Solution found after " + count/12 + " expansions and " + count + " rotations");
    }
    
    private Node iterativeDeepingSearch() {
        for(int iterations=0; iterations<MAX_ITERATIONS; iterations++) {
            stack.clear();
            stack.add(new Node(cube,0));
            pool[0].getCube().setCube(cube);
            //alreadyPassedStates.clear();
            //alreadyPassedStates.put(cube.getHash(), cube);
            System.out.println(iterations);
            Node solution = dephtLimitedFirstSearchRecursive(new Node(cube,0), iterations);
            if(solution != null){
                return solution;
            }
            
            if(iterations==5){
                System.out.println(count + " rotations");
                System.exit(0);
            }
        }
        return null;
    }
    
    private Node dephtLimitedFirstSearch(int limit) {
        while(!stack.empty()) {
            Node n = stack.pop();
            if(n.getCube().isSolved()) {
                return n;
            } else if(n.getDepthLevel()<=limit) {
                expand(n);
            }
        }
        return null;
    }
    
    private Node dephtLimitedFirstSearchRecursive(Node n, int limit) {
        if(n.getCube().isSolved()){
            return n;
        }else if(n.getDepthLevel()<=limit){
            for(int i=0; i<12; i++){
                boolean clockwise;
                clockwise = i%2==0;
                pool[n.getDepthLevel()+1].getCube().setCube(cube.rotateFace(RubikOrientation.values()[i/2], clockwise));
                count++;
                Node result = dephtLimitedFirstSearchRecursive(pool[n.getDepthLevel()+1], limit);
                cube.rotateFace(RubikOrientation.values()[i/2], !clockwise);
                if(result != null){
                    return result;
                }
            }
            return null;
        }else{
            return null;
        }
    }
    
    private void expand(Node n) {
        for(int i=0; i<6; i++){
            Cube c = new Cube(n.getCube());
            c.rotateFace(RubikOrientation.values()[i], true);
            //if(!alreadyPassedStates.containsKey(c.getHash())){
                stack.push(new Node(c, n.getDepthLevel()+1));
                count++;
            //}
            Cube c2 = new Cube(n.getCube());
            c2.rotateFace(RubikOrientation.values()[i], false);
            //if(!alreadyPassedStates.containsKey(c2.getHash())){
                stack.push(new Node(c2, n.getDepthLevel()+1));
                count++;
            //}
        }
    }
}
