import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Q7 {
    public static void main(String[] args) {
        int INTERS = 1000;
        solveProblem(INTERS);
        _findAverages(solveProblem(INTERS));
    }

    public static String flipRandomBits(String s, int numBits) {
        for (int i = 0; i < numBits; i++) {
            s = Main.flipRandomBit(s);
        }

        return s;
    }
    // Q7:
    public static int solveProblem(int iterations) {
        // Perform Random Mutation Hill Climbing
        String currentPoint = Main.randomStartingPoint();
        int currentFitness = Main.calculateFitness(currentPoint);

        int bestFitness = currentFitness;

        int MAX_FAILS = (int) (iterations * (iterations >= 1000 ? 0.08 : iterations >= 500 ? 0.07 : 0.1)); // Restart if no improvement after this many iterations
        int failedIterations = 0;

        for (int i = 0; i < iterations; i++) {
            String newPoint = flipRandomBits(currentPoint, 1);
            if (iterations > 250) {
                newPoint =  flipRandomBits(currentPoint, failedIterations >= MAX_FAILS * 0.5 ? 2 : 1);
            };

            int newFitness = Main.calculateFitness(newPoint);

            if (newFitness > currentFitness) {
                currentPoint = newPoint;
                currentFitness = newFitness;
                failedIterations = 0;
            } else {
                failedIterations++;
            }

            if (failedIterations >= MAX_FAILS) {
                currentPoint = Main.randomStartingPoint();
                currentFitness = Main.calculateFitness(currentPoint);
                failedIterations = 0; // Reset the counter after a restart
            }

            if (currentFitness > bestFitness) {
                bestFitness = currentFitness;
            }
        }

        return bestFitness;
    }


    public static void _findAverages(int iterations){
        Q7 q7_permutation_RRHC = new Q7();

        float averageSum = 0;
        final int numberOfTests = 10000;

        for (int i = 0; i < numberOfTests; i++) {
            final int fitness = q7_permutation_RRHC.solveProblem(iterations);
            averageSum += fitness;
        }

        float avg = (averageSum / numberOfTests);

        System.out.println("Iterations: " + iterations);
        System.out.println("Average fitness achieved: " + avg);
        System.out.println();

        return;
    }
}

