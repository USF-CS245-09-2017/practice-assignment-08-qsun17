
public class GraphAdjMatrix implements Graph {
    private boolean[][] mat;
    private int size;
    /**
     * construction
     * @param size
     */
    public GraphAdjMatrix(int size) {
        mat = new boolean[size][size];
        this.size = size;
    }

    @Override
    public void addEdge(int v1, int v2) {
        //check parameter
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size){
            return;
        }
        mat[v1][v2] = true;
    }

    @Override
    public void topologicalSort() {
        //calculate the in-degree of each vertex
        int[] indegree = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mat[i][j]){
                    indegree[j] ++;
                }
            }
        }
        //topo-sort
        boolean[] isVisited = new boolean[size];
        int[] result = new int[size];
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //find the vertex which has 0 indegree
                if (!isVisited[j] && indegree[j] == 0){
                    isVisited[j] = true;
                    result[cnt++] = j;
                    //update indegree of vertex
                    for (int k = 0; k < size; k++) {
                        if (mat[j][k]){
                            indegree[k] --;
                        }
                    }
                    break;
                }
            }
            //not found 0 indegree vertex
            if (cnt != i + 1){
                break;
            }
        }

        //print result
        if (cnt == size){
            System.out.print(result[0]);
            for (int i = 1; i < cnt; i++) {
                System.out.print(", " + result[i]);
            }
            System.out.println();
        }else{
            System.out.println("Not found topological sort.");
        }
    }

    @Override
    public int[] neighbors(int vertex) {
        //check parameter
        if (vertex < 0 || vertex >= size){
            return new int[0];
        }
        //calculate number of neighbors
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (mat[vertex][i]){
                cnt ++;
            }
        }
        //calculate neighbors
        int[] result = new int[cnt];
        cnt  = 0;
        for (int i = 0; i < size; i++) {
            if (mat[vertex][i]){
                result[cnt++] = i;
            }
        }
        return result;
    }
}
