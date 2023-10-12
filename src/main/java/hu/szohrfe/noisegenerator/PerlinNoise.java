package hu.szohrfe.noisegenerator;

import java.util.Random;

public class PerlinNoise {
    private record Vector2D(double x, double y) {
        public double dot(Vector2D other) {
                return x * other.x + y * other.y;
            }
    }

    private final int[] permutationTable;
    private final int permutationSize;

    public PerlinNoise(int permutationSize) {
        this.permutationSize = permutationSize;
        this.permutationTable = generatePermutation();
    }

    private int[] shuffle(int[] arr) {
        Random rng = new Random();
        for (int i = 0; i < arr.length-1; i++) {
            int j = rng.nextInt(arr.length - i) + i;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    private int[] generatePermutation() {
        int[] permutation = new int[permutationSize];
        for (int i = 0; i < permutationSize; i++) {
            permutation[i] = i;
        }
        int[] shuffledPermutation = shuffle(permutation);
        int[] finalPermutation = new int[permutationSize*2];
        System.arraycopy(shuffledPermutation, 0,
                finalPermutation, 0, shuffledPermutation.length);
        System.arraycopy(shuffledPermutation, 0,
                finalPermutation, shuffledPermutation.length, shuffledPermutation.length);
        return finalPermutation;
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
        int xBase = (int) (Math.floor(x) % permutationSize);
        int yBase = (int) (Math.floor(y) % permutationSize);
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
}
