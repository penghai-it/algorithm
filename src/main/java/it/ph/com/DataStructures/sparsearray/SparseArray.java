package it.ph.com.DataStructures.sparsearray;

import cn.hutool.json.JSONUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: P H
 * @时间: 2024/03/11
 * @描述: 二维数组转稀疏数组
 */
public class SparseArray {
    public int chessArray(int[][] chessArray) {
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[5][3] = 6;
        //输出原始棋盘
        int sum = 0;
        for (int[] row : chessArray) {
            for (int data : row) {
                if (data != 0) {
                    ++sum;
                }
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return sum;
    }

    public int[][] sparseArray(int sum, int length, int[][] chessArray) {
        //创建一个稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组第一行赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //用于记录是第几个数据
        int count = 0;
        //继续给后面的赋值
        //遍历行数
        for (int i = 0; i < length; i++) {
            //遍历列数遍历列数
            for (int j = 0; j < length; j++) {
                //非0数据
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        for (int[] ints : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }
        return sparseArray;
    }

    public void recover(int[][] sparseArray) {
        //新建一个二维数组
        int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        //将值赋给二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public File writeIn(int[][] sparseArray) {
        File file = new File("E:\\sparseArray.txt");
        try {
            List<String> list = new ArrayList<>();
            for (int[] ints : sparseArray) {
                list.add(Arrays.toString(ints));
            }
            // 创建FileOutputStream对象
            FileOutputStream fos = new FileOutputStream(file);
            // 创建BufferedWriter对象
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            // 写入数据
            writer.write(list.toString());
            // 关闭写入流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void read(File file) {
        // 创建FileInputStream对象
        try {
            FileInputStream fis = new FileInputStream(file);
            // 创建BufferedReader对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                // 处理每一行的数据
                List<List> lists = JSONUtil.parseArray(line).toList(List.class);
                int[][] sparseArray = new int[lists.size()][3];
                int count = 0;
                for (List<Integer> list : lists) {
                    int count1 = 0;
                    for (Integer integer : list) {
                        sparseArray[count][count1] = integer;
                        count1++;
                    }
                    count++;
                }
                System.out.println("===============");
                for (int[] row : sparseArray) {
                    for (int data : row) {
                        System.out.printf("%d\t", data);
                    }
                    System.out.println();
                }
            }
            // 关闭读取流
            reader.close();
            //删除文件
            file.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        //创建一个11*11的二维数组数组 并返回值大于0的数据数量
        int[][] chessArray = new int[11][11];
        int sum = new SparseArray().chessArray(chessArray);
        //将二维数组转成稀疏数组
        int[][] sparseArray = new SparseArray().sparseArray(sum, chessArray.length, chessArray);
        //将稀疏数组恢复为二维数组
        new SparseArray().recover(sparseArray);
        //将稀疏数组写入磁盘
        File file = new SparseArray().writeIn(sparseArray);
        //读取磁盘的文件流恢复稀疏数组
        new SparseArray().read(file);
    }
}