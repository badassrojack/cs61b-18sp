package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int s) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        LinkedList<Integer> q = new LinkedList<>();
        marked[s] = true;
        announce();
        q.add(s);

        while(!q.isEmpty()) {
            int v = q.removeFirst();

            for (int w : maze.adj(v)) {
                if (w == t) {
                    targetFound = true;
                }
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    announce();
                    q.add(w);
                }
                if (targetFound == true) {
                    return;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

