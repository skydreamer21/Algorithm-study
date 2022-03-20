// 15686번 치킨 배달 (G5) [bfs dfs]
/*
<문제 정보>
 1. 폐업시키지 않을 치킨집 M개를 골랐을 때 도시의 치킨거리 최솟값

<변수 범위>
 1. 1초 / 512MB
 2. 2<=N<=50
 3. 1<=M<=13
 4. 집의 개수 <= 2N

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.*;

public class Q15686_3 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dists;
    static int[] chi_dis;
    static ArrayList<P10> chicken = new ArrayList<>();
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static int ans=Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        int house=1;
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) {
                    map[i][j]=-2;
                    chicken.add(new P10(i,j));
                }
                if(map[i][j]==1) map[i][j]=house++;
            }
        }

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }*/

        dists = new int[chicken.size()][house]; //house 보다 하나 더 많음
        chi_dis = new int[house];
        Arrays.fill(chi_dis,Integer.MAX_VALUE);
        chi_dis[0]=0;
        for (int chi=0;chi<chicken.size();chi++) {
            getChickDis(chi);
            for (int i=1;i<=N;i++) Arrays.fill(visited[i],false);
        }

        choose(0,0);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void choose (int depth, int idx) {
        if(depth==M) {
            /*for (int i=0;i<chicken.size();i++) {
                System.out.printf("(%d,%d) dis : ",chicken.get(i).x,chicken.get(i).y);
                for (int j=1;j<chi_dis.length;j++) System.out.printf("%d ",dists[i][j]);
                System.out.println();
            }*/


            for (int i=0;i<chicken.size();i++) {
                if(map[chicken.get(i).x][chicken.get(i).y]==-3) {
                    for (int j=1;j<chi_dis.length;j++) {
                        chi_dis[j]=Math.min(chi_dis[j],dists[i][j]);
                    }
                }
            }
            int sum=0;
            for (int d : chi_dis) sum+=d;
            ans = Math.min(ans,sum);
            Arrays.fill(chi_dis,Integer.MAX_VALUE);
            chi_dis[0]=0;

            return;
        }
        if(idx==chicken.size()) return;
        for (int i=idx;i<chicken.size();i++) {
            map[chicken.get(i).x][chicken.get(i).y]=-3;
            choose(depth+1,i+1);
            map[chicken.get(i).x][chicken.get(i).y]=-2;
        }
    }

    public static void getChickDis (int chi) {
        Deque<P10> q = new ArrayDeque<>();
        q.add(new P10(chicken.get(chi).x, chicken.get(chi).y));
        P10 now;
        int nextX, nextY;
        boolean inRange;
        int dis=0;
        int s;

        while(!q.isEmpty()) {
            s=q.size();

            for (int i=0;i<s;i++) {
                now = q.poll();

                for (int[] d : dir) {
                    nextX=now.x+d[0];
                    nextY=now.y+d[1];
                    inRange=nextX>=1 && nextY>=1 && nextX<=N && nextY<=N;
                    if(inRange && !visited[nextX][nextY]) {
                        if(map[nextX][nextY]>=1) dists[chi][map[nextX][nextY]]+=(dis+1);
                        visited[nextX][nextY]=true;
                        q.add(new P10(nextX,nextY));
                    }
                }
            }
            dis++;
        }
    }
}

class P10 {
    int x,y;
    public P10 (int x, int y) {
        this.x=x;
        this.y=y;
    }
}





























