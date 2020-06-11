package matrix;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean changeMatrix = true;
        Matrix matrix = null;

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("choose the operation:\n"
                    + "1. Determine the matrix type.\n"
                    + "2. Find the product of two matrices.\n"
                    + "3. Find the matrix power 2.\n"
                    + "4. Find the inverse of 2 x 2.\n"
                    + "5. Find the transpose.\n"
                    + "6. Check if the matrix is symmetric.\n"
                    + "7. Check if the matrix is Skew-symmetric.\n"
                    + "8. Check if the matrix is Orthogonal.\n"
                    + "9. Check if the matrix is Normal.\n"
            );

            //Enter and check if the opeation is valid.
            int operation;

            while (true) {
                try {
                    System.out.print("I want to find: ");
                    operation = scanner.nextInt();

                    if (operation >= 1 && operation <= 9)
                        break;
                    else
                        System.out.println("\nWrong operation (" + operation
                                + ") , please reEnter the operation");

                } catch (Exception e) {
                    System.out.println("\nWrong operation, please reEnter the operation");
                    //Here we give a new value to the scanner , because the previous one
                    //have an error so we can't use it again.
                    scanner = new Scanner(System.in);
                }

            }

            if (changeMatrix) {
                System.out.println("---Matrix1---");
                matrix = new Matrix();
            }

            System.out.println("");

            switch (operation) {
                case 1:
                    System.out.println("matrix1 = ");
                    matrix.print();
                    System.out.println("Matrix type: " + matrix.type());
                    break;

                case 2:
                    try {
                        System.out.println("---Matrix2---");
                        Matrix matrix2 = new Matrix();

                        System.out.println("matrix1 = ");
                        matrix.print();

                        System.out.println("matrix2 = ");
                        matrix2.print();

                        Matrix result = matrix.multiply(matrix2);
                        System.out.println("matrix1 x matrix2  = ");
                        result.print();
                        break;
                    } catch (Exception e) {
                        System.out.println("Wrong multiplication , The number of columns in matrix1 must equal the number of rows in matrix2");
                    }
                    break;

                case 3:
                    System.out.println("matrix1 = ");
                    matrix.print();
                    try {
                        Matrix result = matrix.toThePower2();
                        System.out.println("matrix1 to the power 2 = ");
                        result.print();
                    } catch (Exception e) {
                        System.out.println("The matrix must be a square matrix(number of rows = number of columns)");
                    }
                    break;

                case 4:
                    System.out.println("matrix1 = ");
                    matrix.print();
                    try {
                        Matrix result = matrix.inverse2x2();
                        System.out.println("The inverse of matrix1 is = ");
                        result.print();
                    } catch (Exception e) {
                        System.out.println("This matrix is not 2 x 2 matrix");
                    }
                    break;

                case 5:
                    System.out.println("matrix1 = ");
                    matrix.print();

                    System.out.println("matrix1^T = ");
                    matrix.transpose().print();
                    break;

                case 6:
                    System.out.println("matrix1 = ");
                    matrix.print();

                    if (matrix.isSymmetric())
                        System.out.println("This matrix is symmetric");
                    else
                        System.out.println("This matrix is not symmetric");
                    break;

                case 7:
                    System.out.println("matrix1 = ");
                    matrix.print();

                    if (matrix.isSkewSymmetric())
                        System.out.println("This matrix is Skew-Symmetric");
                    else
                        System.out.println("This matrix is not Skew-Symmetric");
                    break;

                case 8:
                    System.out.println("matrix1 = ");
                    matrix.print();

                    if (matrix.isOrthogonal())
                        System.out.println("This matrix is Orthogonal");
                    else
                        System.out.println("This matrix is not Orthogonal");
                    break;

                case 9:
                    System.out.println("matrix1 = ");
                    matrix.print();

                    if (matrix.isNormal())
                        System.out.println("This matrix is Normal");
                    else
                        System.out.println("This matrix is not Normal");
                    break;
            }

            System.out.print("\nDo you want to make another operation ? press y , or press anything else to close the progeam : ");

            if (scanner.next().equals("y")) {
                System.out.print("Do you want to change the last matrix(matrix1) press y , or press anyhing else if you don't want to chagne it : ");
                changeMatrix = scanner.next().equals("y");
            } else
                break;
        }

    }

}
