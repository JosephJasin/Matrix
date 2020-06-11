package matrix;

import java.util.Scanner;

public class Matrix {

    //height : the number of rows in the matrix.
    //width  : the number of columns in the matrix.
    final private int height, width;

    //the element of the matrix.
    final private double[][] element;

    public Matrix() {
        Scanner scanner = new Scanner(System.in);
        int numberOfRows, numberOfColumns;
        while (true) {
            try {
                System.out.println("Please choose the order of the matrix");
                System.out.print("Number of rows: ");
                numberOfRows = scanner.nextInt();

                System.out.print("Number of columns: ");
                numberOfColumns = scanner.nextInt();

                if (numberOfRows >= 1 && numberOfColumns >= 1)
                    break;
                else
                    System.out.println("\nWrong order of matrix (the number of rows and the number of columns must be greater than 0)");

            } catch (Exception e) {
                System.out.println("\nWrong order of matrix (the number of rows and the number of columns must be greater than 0)");
                scanner = new Scanner(System.in);
            }
        }

        height = numberOfRows;
        width = numberOfColumns;

        //Enter the elements of the matrix.
        element = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width;) {
                System.out.print("Please enter the element a" + (i + 1) + (j + 1) + ": ");
                try {
                    element[i][j] = scanner.nextDouble();
                    j++;
                } catch (Exception e) {
                    System.out.println("\nThe element must be a real number.");
                    scanner = new Scanner(System.in);
                }
            }
        }
    }

    public Matrix(int _height, int _width) {
        height = _height;
        width = _width;
        element = new double[height][width];
    }

    //Print all the elements of the array
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(element[i][j] + "  ");

            System.out.println("");
        }
    }

    //get the ith row of the matrix.
    public double[] getRow(int ith) {
        return element[ith];
    }

    //get the ith column of the matrix.
    public double[] getColumn(int ith) {
        double[] result = new double[height];

        for (int i = 0; i < result.length; i++)
            result[i] = element[i][ith];

        return result;
    }

    public static Matrix getIdentityMatrixOfOrder(int order) {
        Matrix identity = new Matrix(order, order);

        for (int i = 0; i < order; i++)
            for (int j = 0; j < order; j++)
                if (i == j)
                    identity.element[i][j] = 1;
                else
                    identity.element[i][j] = 0;

        return identity;
    }

    //return an array of the diagonal elements (only if the matrix is square)
    //otherwise return null.
    public double[] vistDiagonal() {
        double[] diagonal = new double[width];

        if (isSquare()) {
            for (int i = 0; i < height; i++)
                diagonal[i] = element[i][i];
            return diagonal;
        } else
            return null;
    }

    //return an array of the upper diagonal elements (only if the matrix is square)
    //otherwise return null.
    public double[] vistUpperDiagonal() {
        //the number of elements in the upper diagonal array is n(n-1)/2 for an n * n matirx.
        double[] upperDiagonal = new double[width * (width - 1) / 2];

        int index = 0;

        if (isSquare()) {
            for (int i = 0; i < height; i++)
                for (int j = i + 1; j < width; j++)
                    upperDiagonal[index++] = element[i][j];

            return upperDiagonal;
        } else
            return null;
    }

    //return an array of the lower diagonal elements (only if the matrix is square)
    //otherwise return null.
    public double[] vistLowerDiagonal() {
        //the number of elements in the lower diagonal array is n(n-1)/2 for an n * n matirx.
        double[] lowerDiagonal = new double[width * (width - 1) / 2];

        int index = 0;

        if (isSquare()) {
            for (int i = 1; i < height; i++)
                for (int j = 0; j < i; j++)
                    lowerDiagonal[index++] = element[i][j];

            return lowerDiagonal;
        } else
            return null;

    }

    //check if the matrix is a column matrix (vector) (height * 1 matrix). 
    public boolean isColumn() {
        return width == 1;
    }

    //check if the matrix is a row matrix (vector) (1 * width matrix). 
    public boolean isRow() {
        return height == 1;
    }

    //check if the matrix is a rectangular matrix number of rows != number of columns. 
    public boolean isRectangular() {
        return height != width;
    }

    //check if the matrix is a square matrix number of rows == number of columns. 
    public boolean isSquare() {
        return height == width;
    }

    //check if the matrix is a diagonal matrix (must be square).
    //for all i != j , aij = 0 
    public boolean isDiagonal() {
        if (isSquare()) {
            for (int i = 0; i < vistUpperDiagonal().length; i++)
                if (vistUpperDiagonal()[i] != 0.0)
                    return false;

            for (int i = 0; i < vistLowerDiagonal().length; i++)
                if (vistLowerDiagonal()[i] != 0.0)
                    return false;
        } else
            return false;

        return true;

    }

    //check if the matrix is a scalar matrix(must be a diagonal matrix).
    //all the elements in diagonal must be the same.
    public boolean isScalar() {
        if (isDiagonal()) {
            double firstElement = vistDiagonal()[0];
            for (int i = 1; i < vistDiagonal().length; i++)
                if (vistDiagonal()[i] != firstElement)
                    return false;
        } else
            return false;

        return true;
    }

    //check if the matrix is an identity(unit) matrix(must be scalar).
    //all the elements in diagonal == 1.0 .
    public boolean isIdentity() {
        if (isScalar()) {
            for (int i = 0; i < vistDiagonal().length; i++)
                if (vistDiagonal()[i] != 1.0)
                    return false;
        } else
            return false;

        return true;
    }

    //check if the matrix is a zero matrix.
    //all the elements == 0.0.
    public boolean isZero() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (element[i][j] != 0.0)
                    return false;

        return true;
    }

    //check if the matrix is an upper triangular matrix(must be a square matrix larger than 1 * 1).
    //all the elements below the diagonal == 0.0 .
    public boolean isUpperTriangular() {
        if (isSquare() && width >= 2) {
            for (int i = 0; i < vistLowerDiagonal().length; i++)
                if (vistLowerDiagonal()[i] != 0.0)
                    return false;
        } else
            return false;

        return true;
    }

    public boolean isLowerTriangular() {
        if (isSquare() && width >= 2) {
            for (int i = 0; i < vistUpperDiagonal().length; i++)
                if (vistUpperDiagonal()[i] != 0.0)
                    return false;
        } else
            return false;

        return true;
    }

    //Find the matrix type (square, Rectangular, Scalar… etc).
    public String type() {
        String result = "";

        if (isColumn())
            result += "Column(Vector) , ";

        if (isRow())
            result += "Row(Vector) , ";

        if (isRectangular())
            result += "Rectangular , ";

        if (isSquare())
            result += "Square , ";

        if (isDiagonal())
            result += "Diagonal , ";

        if (isIdentity())
            result += "Identity(Unit) , ";

        if (isZero())
            result += "Zero , ";

        if (isUpperTriangular())
            result += "Upper Triangular , ";

        if (isLowerTriangular())
            result += "Lower Triangular , ";

        if (isScalar())
            result += "Scalar , ";

//        if (isSymmetric())
//            result += "Symmetric , ";
//
//        if (isSkewSymmetric())
//            result += "SkewSymmetric , ";
//
//        if (isOrthogonal())
//            result += "Orthogonal , ";
//
//        if (isNormal())
//            result += "Normal , ";

        return result.substring(0, result.lastIndexOf(",")) + " Matrix";

    }

    //Multyply one row with one column.
    public static double multiplyRowWithColumn(double[] row, double[] column) throws Exception {
        if (row.length != column.length)
            throw new Exception();

        double result = 0;

        for (int i = 0; i < row.length; i++)
            result += row[i] * column[i];

        return result;
    }

    //Multiply this matrix with matrix2.
    public Matrix multiply(Matrix matrix2) throws Exception {
        if (width != matrix2.height)
            throw new Exception();

        Matrix result = new Matrix(height, matrix2.width);

        for (int i = 0; i < result.height; i++)
            for (int j = 0; j < result.width; j++)
                result.element[i][j] = multiplyRowWithColumn(getRow(i), matrix2.getColumn(j));

        return result;
    }

    //Multiply this matrix with scalar value.
    public Matrix multiply(double sclar) {
        Matrix result = new Matrix(height, width);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                result.element[i][j] = -element[i][j];

        return result;
    }

    //Find the matrix power 2  (A * A).
    public Matrix toThePower2() throws Exception {
        if (!isSquare())
            throw new Exception();
        else
            return multiply(this);
    }

    //find the Inverse of 2 × 2 matrix.
    public Matrix inverse2x2() throws Exception {

        if (width != 2 || height != 2)
            throw new Exception();

        Matrix inverseMatrix = new Matrix(2, 2);

        // coefficient =  1 / determinant
        double coefficient = 1.0 / (element[0][0] * element[1][1] - element[1][0] * element[0][1]);

        inverseMatrix.element[0][0] = element[1][1] * coefficient;
        inverseMatrix.element[1][1] = element[0][0] * coefficient;
        inverseMatrix.element[0][1] = -element[0][1] * coefficient;
        inverseMatrix.element[1][0] = -element[1][0] * coefficient;

        return inverseMatrix;
    }

    //Find the transpose of the matrix (A^T)
    public Matrix transpose() {
        Matrix transposeMatrix = new Matrix(width, height);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                transposeMatrix.element[j][i] = element[i][j];

        return transposeMatrix;
    }

    //check if 2 matrices are equals(the same number of rows , columns and the same elements).
    public boolean equals(Matrix matrix2) {
        if (height == matrix2.height && width == matrix2.width) {
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    if (element[i][j] != matrix2.element[i][j])
                        return false;
        } else
            return false;

        return true;
    }

    //Check if the matrix is Symmetric(A == AT).
    public boolean isSymmetric() {
        if (isSquare())
            return equals(transpose());
        else
            return false;
    }

    //Check if the matrix is Skew-symmetric(A == -AT).
    public boolean isSkewSymmetric() {
        if (isSquare())
            return equals(transpose().multiply(-1));
        else
            return false;
    }

    //Check if the matrix is Orthogonal (A.AT == AT.A == I).
    public boolean isOrthogonal() {
        try {

            if (isSquare())
                return multiply(transpose()).equals(transpose().multiply(this))
                        && multiply(transpose()).equals(Matrix.getIdentityMatrixOfOrder(height));
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    //Check if the matrix is Normal(A.AT == AT.A).
    public boolean isNormal() {
        try {

            if (isSquare())
                return multiply(transpose()).equals(transpose().multiply(this));
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

}
