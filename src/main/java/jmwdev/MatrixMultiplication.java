package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixMultiplication {
    static Logger logger = LogManager.getLogger(MatrixMultiplication.class);

    public static void main(String[] args) {
        double[][] firstMatrix = {
                new double[]{1, 2, 3},
                new double[]{4, 5, 6}
        };

        double[][] secondMatrix = {
                new double[]{7, 8},
                new double[]{9, 10},
                new double[]{11, 12}
        };

        double[][] expected = {
                new double[]{58, 64},
                new double[]{139, 154}
        };

        outputMatrix(firstMatrix,"a");
        outputMatrix(secondMatrix,"b");

        double[][] actual = multiplyMatrices(firstMatrix, secondMatrix);
        outputMatrix(actual,"result");
        outputMatrix(expected,"expected");
    }

    static void outputMatrix(double[][] matrix, String name) {
        for (double[] doubles : matrix) {
            StringBuilder sb = new StringBuilder();
            for (double aDouble : doubles) {
                sb.append(aDouble);
                sb.append("   ");
            }
            logger.info("{} - [   {}]", name, sb);

        }
    }

    static double[] getColumn(double[][] matrix, int index) {
        double[] column = new double[matrix.length];
        for(int i=0; i<matrix.length; i++) {
            column[i] = matrix[i][index];
        }
        return column;
    }

    private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        // size of resultant matrix is second matrix rows * first matrix columns
        double[][] result = new double[secondMatrix[0].length][firstMatrix.length];
        for(int i=0; i<result.length; i++) {
            for(int j=0; j< result[i].length; j++) {
                result[i][j] = dotProduct(firstMatrix[i], getColumn(secondMatrix, j));
            }
        }
        return result;
    }

    private static double dotProduct(double[] row, double[] column) {
        double result = 0;
        for (int i = 0; i < row.length ; i++) {
            result += row[i] * column[i];
        }
        return result;
    }
}
