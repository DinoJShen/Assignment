/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author JShen
 */
public class Convolution5x5 {

    static int N = 5;//matrix size
    static int[][] convolutionFormat = {
        {-2, -1, 0, 1, 2},
        {-2, -1, 0, 1, 2},
        {-2, -1, 0, 1, 2},
        {-2, -1, 0, 1, 2},
        {-2, -1, 0, 1, 2}};
    static int[][] rotatedFormat = new int[N][N];
    static ArrayList<Integer> finalList = new ArrayList<>();
    static ArrayList<Integer> tempList = new ArrayList<>();

    public static void main(String[] args) {
        rotateMatrix(convolutionFormat); // rotate by 180 degree
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Image Name: ");
        String fileName = input.next();
        System.out.print("Enter Image Width: ");
        int width = input.nextInt();

        String fileNameOutput = "Convolution2_" + fileName;
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int dataValue = myInputFile.available();
            for (int i = 0; i < 2*(width + 4); i++) {
                tempList.add(-1);
            }
            while (dataValue > 0) {
                tempList.add(-1);
                tempList.add(-1);
                for (int i = 0; i < width; i++) {
                    tempList.add(myInputFile.read());
                    dataValue--;
                }
                tempList.add(-1);
                tempList.add(-1);
            }
            for (int i = 0; i < 2*(width + 4); i++) {
                tempList.add(-1);
            }

            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i) != -1) {
                    int sum = tempList.get(i - 2 * width - 8 - 2) * rotatedFormat[0][0]
                            + tempList.get(i - 2 * width - 8 - 1) * rotatedFormat[0][1]
                            + tempList.get(i - 2 * width - 8) * rotatedFormat[0][2]
                            + tempList.get(i - 2 * width - 8 + 1) * rotatedFormat[0][3]
                            + tempList.get(i - 2 * width - 8 + 2) * rotatedFormat[0][4]
                            + tempList.get(i - width - 4 - 2) * rotatedFormat[1][0]
                            + tempList.get(i - width - 4- 1) * rotatedFormat[1][1]
                            + tempList.get(i - width - 4) * rotatedFormat[1][2]
                            + tempList.get(i - width - 4 + 1) * rotatedFormat[1][3]
                            + tempList.get(i - width - 4 + 2) * rotatedFormat[1][4]
                            + tempList.get(i - 2) * rotatedFormat[2][0]
                            + tempList.get(i - 1) * rotatedFormat[2][1]
                            + tempList.get(i) * rotatedFormat[2][2]
                            + tempList.get(i + 1) * rotatedFormat[2][3]
                            + tempList.get(i + 2) * rotatedFormat[2][4]
                            + tempList.get(i + width - 4 - 2) * rotatedFormat[3][0]
                            + tempList.get(i + width - 4 - 1) * rotatedFormat[3][1]
                            + tempList.get(i + width - 4) * rotatedFormat[3][2]
                            + tempList.get(i + width - 4 + 1) * rotatedFormat[3][3]
                            + tempList.get(i + width - 4 + 2) * rotatedFormat[3][4]
                            + tempList.get(i + 2 * width - 8 - 2) * rotatedFormat[4][0]
                            + tempList.get(i + 2 * width - 8 - 1) * rotatedFormat[4][1]
                            + tempList.get(i + 2 * width - 8) * rotatedFormat[4][2]
                            + tempList.get(i + 2 * width - 8 + 1) * rotatedFormat[4][3]
                            + tempList.get(i + 2 * width - 8 + 2) * rotatedFormat[4][4];
                    if (sum < 0) {
                        sum = 0;
                    }
                    if (sum > 255) {
                        sum = 255;
                    }
                    finalList.add(sum);
                }
            }
            try (FileOutputStream myOutputFile = new FileOutputStream(fileNameOutput)) {
                for (Integer str : finalList) {
                    myOutputFile.write(str);
                }
                myOutputFile.close();
            } catch (IOException ex) {
                System.out.print("File output error!");
            }
            System.out.println("Your output file name: " + fileNameOutput);

        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
    }

    static void rotateMatrix(int mat[][]) {
        int countX = 0;
        int countY = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                rotatedFormat[countX][countY] = mat[i][j];
                if (countY >= N - 1) {
                    countY = 0;
                } else {
                    countY++;
                }
            }
            countX++;
        }
    }
}
