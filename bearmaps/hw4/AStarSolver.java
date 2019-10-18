package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private int numDeque;
    private boolean hasTimedOut = false;
    private boolean isUnsolvable = false;
    private boolean isSolved = false;
    private List<Vertex> solution;
    private double solutionWeight;
    private double solutionTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        double startTime = (double) System.nanoTime() / 1000000000;

        numDeque = 0;

        DoubleMapPQ<Vertex> heap = new DoubleMapPQ<>();
        HashMap<Vertex, Double> distanceTo = new HashMap<>();
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
        HashSet<Vertex> visited = new HashSet<>();

        heap.add(start, input.estimatedDistanceToGoal(start, end));
        distanceTo.put(start, 0.0);

        while (Math.abs(((double) (System.nanoTime() / 1000000000))) - startTime <= timeout
                && heap.size() != 0
                && !heap.getSmallest().equals(end)) {
            Vertex smallest = heap.removeSmallest();
            numDeque += 1;

            if (!visited.contains(smallest)) {
                List<WeightedEdge<Vertex>> list = input.neighbors(smallest);
                for (WeightedEdge e : list) {
                    Vertex p = (Vertex) e.from();
                    Vertex q = (Vertex) e.to();
                    double w = e.weight();
                    double dist = distanceTo.get(p) + w;
                    if (!distanceTo.containsKey(q) || dist < distanceTo.get(q)) {
                        distanceTo.put(q, dist);
                        edgeTo.put(q, p);
                        if (heap.contains(q)) {
                            heap.changePriority(q, dist + input.estimatedDistanceToGoal(q, end));
                        }
                        if (!heap.contains(q)) {
                            heap.add(q, dist + input.estimatedDistanceToGoal(q, end));
                        }
                    }
                }
                visited.add(smallest);
            }
        }
        if (((double) (System.nanoTime() / 1000000000)) - startTime > timeout) {
            hasTimedOut = true;
            solution = new ArrayList<>();
            solutionTime = Math.abs(((double) System.nanoTime() / 1000000000) - startTime);
            solutionWeight = 0.0;
            return;
        }
        if (heap.size() == 0) {
            isUnsolvable = true;
            solution = new ArrayList<>();
            solutionTime = Math.abs(System.nanoTime() / 1000000000 - startTime);
            solutionWeight = 0.0;
            return;
        }
        if (heap.getSmallest().equals(end)) {
            isSolved = true;
            solution = new ArrayList<>();
            solutionWeight = distanceTo.get(end);
            Vertex vert = end;
            while (vert != null && !vert.equals(start)) {
                solution.add(vert);
                vert = edgeTo.get(vert);
            }
            solution.add(vert);
            Collections.reverse(solution);
            solutionTime = Math.abs(System.nanoTime() / 1000000000 - startTime);
        }
    }


    @Override
    public SolverOutcome outcome() {
        if (hasTimedOut) {
            return SolverOutcome.TIMEOUT;
        }
        if (isUnsolvable) {
            return SolverOutcome.UNSOLVABLE;
        }
        return SolverOutcome.SOLVED;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numDeque;
    }

    @Override
    public double explorationTime() {
        return solutionTime;
    }
}
