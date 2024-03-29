// 2263번 트리의 순회 (G2) [분할정복]
/*
<문제 정보>
 1. n개의 정점을 갖는 이진 트리의 정점에 1 ~ N 까지의 번호가 중복없이 매겨져있다.
 2. 이진트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 출력

<변수 범위>
 1. 5초 / 128MB
 2. 1<=N<=100,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q2263_studySolve_t2 {
    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter writer;

    public static void main(String args[]) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("C:\\Create\\Study Project\\백준 알고리즘\\testCase\\Q2263_4.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int size = 5000;
        N = size;
        inOrder = new int[size];
        postOrder = new int[size];
        /*st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) inOrder[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) postOrder[i] = Integer.parseInt(st.nextToken());*/
        for (int i=0;i<N;i++) inOrder[i] = N-i;
        for (int i=0;i<N;i++) postOrder[i] = N-i;


        /*File file = new File("C:\\Create\\Study Project\\백준 알고리즘\\testCase\\result.txt");
        FileWriter fw = new FileWriter(file);
        writer = new BufferedWriter(fw);*/

        getPreOrder(0,0,N);
//        writer.write(sb.toString());
//        writer.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
//        br.close();
    }
    public static void getPreOrder(int startIn, int startPost, int length) throws IOException {
//        System.out.printf("startIn : %d, startPost : %d, length : %d\n",startIn, startPost, length);
        if (length==1) {
            /*writer.write(String.valueOf(inOrder[startIn]));
            writer.write(" ");*/
            sb.append(inOrder[startIn]).append(" ");
            return;
        }

        sb.append(postOrder[startPost+length-1]).append(" ");
        /*writer.write(String.valueOf(postOrder[startPost+length-1]));
        writer.write(" ");*/
        int endIn = startIn + length -1;
        int leftLength=0;
        int rightLength=0;
        for (int i=0;i<=length/2;i++) {
            if (inOrder[startIn+i]==postOrder[startPost+length-1] || inOrder[endIn-i]==postOrder[startPost+length-1]) {
                leftLength = inOrder[startIn+i]==postOrder[startPost+length-1] ? i : length-i-1;
                rightLength = length-leftLength-1;
                break;
            }
        }
//        System.out.printf("leftL : %d, rightL : %d\n",leftLength, rightLength);
        if(leftLength!=0) getPreOrder(startIn,startPost,leftLength);
        if(rightLength!=0) getPreOrder(startIn+leftLength+1,startPost+leftLength,rightLength);
        /*try {

        } catch(StackOverflowException e) {
            e.printStackTrace();
            System.out.printf("startIn : %d, startPost : %d, length : %d\n",startIn, startPost, length);
            return;
        }*/
    }
}
























