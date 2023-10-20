package hu.szohrfe.noisegenerator.model;

import java.util.Random;

public class PerlinNoise {
    private record Vector2D(double x, double y) {
        public double dot(Vector2D other) {
                return x * other.x + y * other.y;
            }
    }

    private final long seed;
    private final int[] permutationTable;
    private static final int PERMUTATION_SIZE = 256;

    public PerlinNoise() {
        seed = Long.MAX_VALUE;
        permutationTable = generatePermutation();
    }

    public PerlinNoise(long seed) {
        this.seed = seed;
        permutationTable = generatePermutation();
    }

    private int[] shuffle(int[] arr) {
        Random rng = new Random(seed);
        for (int i = 0; i < PERMUTATION_SIZE - 1; i++) {
            int j = rng.nextInt(PERMUTATION_SIZE - i) + i;
            int temp = arr[i];
            arr[i] = arr[PERMUTATION_SIZE + i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    private int[] generatePermutation() {
        int[] permutation = new int[PERMUTATION_SIZE * 2];
        for (int i = 0; i < PERMUTATION_SIZE; i++) {
            permutation[i] = i;
        }
        return shuffle(permutation);
    }

    private Vector2D getConstantVector(int value) {
        int h = value % 4;
        if(h == 0)
            return new Vector2D(1.0, 1.0);
        else if(h == 1)
            return new Vector2D(-1.0, 1.0);
        else if(h == 2)
            return new Vector2D(-1.0, -1.0);
        else
            return new Vector2D(1.0, -1.0);
    }

    private double fade(double t) {
        return ((6*t - 15)*t + 10)*Math.pow(t, 3);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    public double map(double input, double inputStart, double inputEnd,
                    double outputStart, double outputEnd) {
        if (input < inputStart) return outputStart;
        if (input > inputEnd) return outputEnd;
        double inputRange = inputEnd - inputStart;
        double outputRange = outputEnd - outputStart;
        double x = input - inputStart;
        double y = outputRange / inputRange * x;
        return outputStart + y;
    }

    public double noise2D(double x, double y, int wrapX, int wrapY) {
        int xBase = (int) (Math.floor(x) % PERMUTATION_SIZE);
        int yBase = (int) (Math.floor(y) % PERMUTATION_SIZE);
        double xRem = x - Math.floor(x);
        double yRem = y - Math.floor(y);

        Vector2D topRight = new Vector2D(xRem - 1, yRem - 1);
        Vector2D topLeft = new Vector2D(xRem, yRem - 1);
        Vector2D bottomRight = new Vector2D(xRem - 1, yRem);
        Vector2D bottomLeft = new Vector2D(xRem, yRem);

        int wrappedXBase = wrapX == 0 ? xBase+1 : (xBase+1) % wrapX;
        int wrappedYBase = wrapY == 0 ? yBase+1 : (yBase+1) % wrapY;

        int valueTopRight = permutationTable[permutationTable[wrappedXBase] + wrappedYBase];
        int valueTopLeft = permutationTable[permutationTable[xBase] + wrappedYBase];
        int valueBottomRight = permutationTable[permutationTable[wrappedXBase] + yBase];
        int valueBottomLeft = permutationTable[permutationTable[xBase] + yBase];

        double dotTopRight = topRight.dot(getConstantVector(valueTopRight));
        double dotTopLeft = topLeft.dot(getConstantVector(valueTopLeft));
        double dotBottomRight = bottomRight.dot(getConstantVector(valueBottomRight));
        double dotBottomLeft = bottomLeft.dot(getConstantVector(valueBottomLeft));

        double u = fade(xRem);
        double v = fade(yRem);

        return lerp(u,
                lerp(v, dotBottomLeft, dotTopLeft),
                lerp(v, dotBottomRight, dotTopRight));
    }

    public long getSeed() {
        return seed;
    }
}
