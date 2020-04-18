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
 * @author ASUS
 */
public class MeanFilter {

    static int N = 3;//matrix size
    static float[][] convolutionFormat = {
        {(float)1/9, (float)1/9, (float)1/9},
        {(float)1/9, (float)1/9, (float)1/9},
        {(float)1/9, (float)1/9, (float)1/9}};
    static float[][] rotatedFormat = new float[N][N];
    static ArrayList<Integer> finalList = new ArrayList<>();
    static ArrayList<Integer> tempList = new ArrayList<>();

    public static void main(String[] args) {
        rotateMatrix(convolutionFormat); // rotate by 180 degree
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Image Name: ");
        String fileName = input.next();
        System.out.print("Enter Image Width: ");
        int width = input.nextInt();
        String fileNameOutput = "MeanFilter_" + fileName;
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int dataValue = myInputFile.available();
            for (int i = 0; i < width + 2; i++) {
                tempList.add(-1);
            }
            while (dataValue > 0) {
                tempList.add(-1);
                for (int i = 0; i < width; i++) {
                    tempList.add(myInputFile.read());
                    dataValue--;
                }
                tempList.add(-1);
            }
            for (int i = 0; i < width + 2; i++) {
                tempList.add(-1);
            }

            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i) != -1) {
                    int sum = (int) (tempList.get(i - width - 1) * rotatedFormat[0][0]
                            + tempList.get(i - width) * rotatedFormat[0][1]
                            + tempList.get(i - width + 1) * rotatedFormat[0][2]
                            + tempList.get(i - 1) * rotatedFormat[1][0]
                            + tempList.get(i) * rotatedFormat[1][1]
                            + tempList.get(i + 1) * rotatedFormat[1][2]
                            + tempList.get(i + width - 1) * rotatedFormat[2][0]
                            + tempList.get(i + width) * rotatedFormat[2][1]
                            + tempList.get(i - width + 1) * rotatedFormat[2][2]);
                    if (sum<0) sum = 0;
                    if (sum>255) sum = 255;
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
            System.out.println("Your output file name: "+fileNameOutput);
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
    }

    static void rotateMatrix(float mat[][]) {
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
