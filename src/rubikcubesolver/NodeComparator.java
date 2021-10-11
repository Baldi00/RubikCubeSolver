/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.Comparator;

/**
 *
 * @author Andrea
 */
public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node t, Node t1) {
        int costT = t.getDepthLevel();
        int heurT = t.getCube().manhattanDistanceInRotationsSum();
        int costT1 = t1.getDepthLevel();
        int heurT1 = t1.getCube().manhattanDistanceInRotationsSum();
        
        if(costT+heurT < costT1+heurT1) {
            return -1;
        }
        if(costT+heurT > costT1+heurT1) {
            return 1;
        }
        return 0;
    }
    
}
