package ex1.src;



import java.io.Serializable;
import java.io.*;
import java.util.*;

import static java.lang.Double.POSITIVE_INFINITY;


public class WGraph_Algo implements weighted_graph_algorithms , Serializable{
    private weighted_graph graph = new WGraph_DS();

    /**
     * HashMaps that i used at the algorithems
     */
    private HashMap<Integer, Integer> color = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> per = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> dis = new HashMap<Integer, Integer>();

    @Override
    public void init(weighted_graph g) {
        this.graph = g;
    }


    @Override
    public weighted_graph getGraph() {
        return graph;
    }

    /**
     * deep copy, copy constructor of the WGraph_DS
     * @return
     */
    @Override
    public weighted_graph copy() {
        if (graph == null) return null;
        weighted_graph newGraph = new WGraph_DS((WGraph_DS) graph);
        return newGraph;
    }

    /**
     *In isConnected function I used BFS algorithm.
     * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
     * The algorithm uses Queue and also changes the HashMaps of the fields.
     * In isConnected you start going through all the vertices when you start with one particular vertex, and count every vertex that you go through until we have reached all of them. If we went through all the vertices - the graph is linked.
     * If we passed a smaller number than all the vertices - the graph is not a consonant.
     *
     * @return
     */
    @Override
    public boolean isConnected() {

        if (graph.getV().isEmpty() || graph.getV().size() == 1) return true;

        setH(this.color, 0);
        setH(this.per, null);
        setH(this.dis, -1);

        node_info s1 = graph.getV().iterator().next();

        color.put(s1.getKey(), 1);
        dis.put(s1.getKey(), 0);

        int numVert = 0;
        LinkedList<node_info> q = new LinkedList<node_info>();
        q.addLast(s1);

        while (!q.isEmpty()) {
            node_info u = q.removeFirst();
            if (u != null) {
                for (node_info v : graph.getV(u.getKey())
                ) {
                    if (color.get(v.getKey()) == 0) {
                        color.put(v.getKey(), 1);
                        v.setTag(dis.get(u.getKey()) + 1);
                        per.put(v.getKey(), u.getKey());
                        q.addLast(v);
                    }
                }

            }
            color.put(u.getKey(), 2);
            numVert++;
        }
        return (graph.getV().size() == numVert);
    }

    /**
     *
     * helped function set the HashMaps to the start value
     * @param myHa
     * @param a
     */
    public void setH(HashMap<Integer, Integer> myHa, Integer a) {

        for (node_info t : graph.getV()) {

            myHa.put(t.getKey(), a);
        }
    }

    /**
     * In the shortestPathDist functions I used Dijkstra.
     * By Mark the nodeInfo inner tag of all the vertices according to their distance (how many vertices need to go minimally until you reach it) from the initial vertex.
     * In case of shortest path, we update the tag by the shortest dist from the src.
     * Every time I save the "perent" of the vertax in dedicated hashMap.
     * This allows you to know the distance of each vertex from the initial vertex.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {

        if (src == dest) return 0;
        if (graph.getNode(src) == null || graph.getNode(dest) == null) return -1;
        if (graph.getV(src).isEmpty() || graph.getV(dest).isEmpty()) return -1;
        if(graph.getV().isEmpty()) return -1;

        Queue<node_info> q = new PriorityQueue<node_info>();




        for (node_info t : graph.getV()) {
            t.setTag(POSITIVE_INFINITY);
           // q.add(t);
            per.put(t.getKey() , null);
            color.put(t.getKey() , 0);
        }


        graph.getNode(src).setTag(0.0);
        q.add(graph.getNode(src));
        while (!q.isEmpty()) {

            node_info u = q.poll();

            for (node_info v: graph.getV(u.getKey())) {
                if (v.getKey() != u.getKey() && v.getKey() != -1 && color.get(v.getKey()) == 0 ){
                    Double temp = u.getTag()  + graph.getEdge(v.getKey(),u.getKey());
                    if(v.getTag() > temp){
                        v.setTag(temp);
                        per.put(v.getKey(),u.getKey());
                        q.add(v);

                    }
                }
            }
        color.put(u.getKey() , 1);

        }

        if(graph.getNode(dest).getTag() == POSITIVE_INFINITY) return -1;
        return graph.getNode(dest).getTag();
    }


    /**
     *
     * In the shortestPath function I used Dijkstra, exactly like shortestPathDist function.
     * After it, I prepared a list by the parent hashmap.
     *
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {

        LinkedList<node_info> l = new LinkedList<node_info>();

        if (graph.getNode(src) == null || graph.getNode(dest) == null) return null;
        if (graph.getV(src).isEmpty() || graph.getV(dest).isEmpty()) return null;
        if(graph.getV().isEmpty()) return null;
        if (src == dest) {
            l.addLast(graph.getNode(src));
            return l;
        }
        if (shortestPathDist(src , dest) == -1) return null;

        Queue<node_info> q = new PriorityQueue<node_info>();




        for (node_info t : graph.getV()) {
            t.setTag(POSITIVE_INFINITY);
            // q.add(t);
            per.put(t.getKey() , null);
            color.put(t.getKey() , 0);
        }


        graph.getNode(src).setTag(0.0);
        q.add(graph.getNode(src));
        while (!q.isEmpty()) {

            node_info u = q.poll();

            for (node_info v: graph.getV(u.getKey())) {
                if (v.getKey() != u.getKey() && v.getKey() != -1 && color.get(v.getKey()) == 0 ){
                    Double temp = u.getTag()  + graph.getEdge(v.getKey(),u.getKey());
                    if(v.getTag() > temp){
                        v.setTag(temp);
                        per.put(v.getKey(),u.getKey());
                        q.add(v);

                    }
                }
            }
            color.put(u.getKey() , 1);

        }

        l.addLast(graph.getNode(dest));
        Integer t = per.get(dest);

        while(t!=null) {
            l.addFirst(graph.getNode(t));
            t=per.get(t);
        }


    return l;
}


    /**
     * Save graph to local memory
     *
     * @param file - the file name (may include a relative path).
     * @return
     */

    @Override
    public boolean save(String file) {
        try{
            FileOutputStream _file = new FileOutputStream(file);
            ObjectOutputStream _outs =
                    new ObjectOutputStream(_file);

        _outs.writeObject(this.graph);
        _outs.close();
        _file.close();

        }
    catch(IOException e){
            e.printStackTrace();
        }
        return true;

    }

    /**
     * Load graph from local memory
     *
     * @param file - file name
     * @return
     */
    @Override
    public boolean load(String file) {

        try {
        FileInputStream myFile = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(myFile);

            ois.close();
            myFile.close();
        }
        catch (Exception error){
            error.printStackTrace();
        }

        return true;
    }

}