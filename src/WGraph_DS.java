package ex1.src;



import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class WGraph_DS implements weighted_graph, Serializable {

    private static class NodeInfo implements node_info , Comparable<node_info> , Serializable{


        private int id;
        private String info;
        private double tag;
        static int counter;


        NodeInfo(node_info a) {
            this.id = a.getKey();
            this.info = a.getInfo();
            this.tag = a.getTag();


        }
        NodeInfo(int key) {
            this.id = key;
            this.info = null;
            this.tag = 0;
        }

        NodeInfo() {
            this.id = counter;
            counter++;
            this.info = null;
            this.tag = 0;
        }

        @Override
        public int getKey() {
            return this.id;
        }

        @Override
        public String getInfo() {
            return info;
        }

        @Override
        public void setInfo(String s) {
            info = s;
        }

        @Override
        public double getTag() {
            return tag;
        }

        @Override
        public void setTag(double t) {
            tag = t;
        }






        @Override
        public int compareTo(node_info o) {
            int ans = 0;
            if (this.getTag() - o.getTag() > 0) ans = 1;
            else if (this.getTag() - o.getTag() < 0) ans = -1;
            return ans;
        }
    }


/////////////////////////////////////

    /**
     *
     * Those 3 data structor represent the structor of the graph.
     * -myNodesCollection represent collection of all the nodes in the graph.
     * -AllNodes is a hashMap with an inner hashMap.
     * He contain all the nodeInfo of the graph.
     * Every inner hashMap represent the Neighbors of the key.
     * The inner hashMap contain the nodeInfo, that I can get them by himself key.
     * The inner hashMap contain the nodeInfo at the key -1 (see Cartoon description).
     * --AllNodes is a hashMap with an inner hashMap.
     * edgeWeight contain the Weight of the edges.
     * every inner hashmap contain his node by get the key (-1).
     *
     */

    private HashMap<Integer , node_info> myNodesCollection = new HashMap<Integer , node_info>();
    public HashMap<Integer,HashMap<Integer, node_info>> AllNodes = new HashMap<Integer,HashMap<Integer,node_info>>();
    public HashMap<Integer,HashMap<Integer, Double>> edgeWeight = new HashMap<Integer,HashMap<Integer,Double>>();
    private int mc=0;
    private int edgeNum=0;


    /**
     * copy constructor
     * @param g
     */
    public WGraph_DS(WGraph_DS g){
        this.mc = g.getMC();
        this.edgeNum = g.edgeSize();

        this.AllNodes = new HashMap<Integer,HashMap<Integer, node_info>>();
        this.edgeWeight = new HashMap<Integer,HashMap<Integer, Double>>();
        this.myNodesCollection = new HashMap<Integer, node_info>();
        for ( node_info k : g.getV()
        ) {
            node_info temp = new NodeInfo(k.getKey());
            HashMap<Integer, node_info> t = new HashMap<Integer, node_info>();
            this.AllNodes.put(temp.getKey(), t);
        }


        for ( node_info k : g.getV()
        ) {
            for ( node_info ni : g.AllNodes.get(k.getKey()).values()){
                this.AllNodes.get(k.getKey()).put(ni.getKey(), ni);
                this.myNodesCollection.put(k.getKey() , k);
            }

        }

        for ( node_info k : g.getV()
        ) {
            node_info temp = new NodeInfo(k.getKey());
            HashMap<Integer, Double> t = new HashMap<Integer, Double>();
            this.edgeWeight.put(temp.getKey(), t);
        }

        for ( HashMap<Integer , Double> k : g.edgeWeight.values()
        ) {
            double currentN = k.get(-1);
            this.edgeWeight.get((int)currentN).putAll(k);

            }
        }


    /**
     * constructor
     */
    public WGraph_DS(){
        this.AllNodes = new HashMap<Integer,HashMap<Integer, node_info>>();
        this.edgeWeight = new HashMap<Integer,HashMap<Integer, Double>>();
        this.myNodesCollection = new HashMap<Integer, node_info>();
    }

    @Override
    public node_info getNode(int key) {
       if (!AllNodes.containsKey(key)) return null;
       return AllNodes.get(key).get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if(!AllNodes.containsKey(node1) || !AllNodes.containsKey(node2)) return false;
        if(!AllNodes.get(node1).containsKey(node2) || !AllNodes.get(node2).containsKey(node1)) return false;
        if(!edgeWeight.get(node1).containsKey(node2) ||!edgeWeight.get(node2).containsKey(node1)) return false;

        return true;
    }

    @Override
    public double getEdge(int node1, int node2) {

        if(!AllNodes.containsKey(node1) || !AllNodes.containsKey(node2)) return -1;
        if(!AllNodes.get(node1).containsKey(node2) || !AllNodes.get(node2).containsKey(node1)) return -1;
        if(!edgeWeight.get(node1).containsKey(node2) ||!edgeWeight.get(node2).containsKey(node1)) return -1;

        return edgeWeight.get(node2).get(node1);
    }

    @Override
    public void addNode(int key) {

        if (AllNodes.containsKey(key)) return;
        node_info t = new NodeInfo(key);

        myNodesCollection.put(key , t);

        HashMap<Integer, node_info> keyN = new HashMap<Integer, node_info>();
        keyN.put(key,t);
        AllNodes.put(key,keyN);

        HashMap<Integer, Double> myEdgeW = new HashMap<Integer, Double>();
        myEdgeW.put(-1 , key*1.0);
        edgeWeight.put(key,myEdgeW);
        mc++;

    }

    @Override
    public void connect(int node1, int node2, double w) {
    if (!AllNodes.containsKey(node1) || !AllNodes.containsKey(node2) ) return;

        if (AllNodes.get(node1).containsKey(node2) && AllNodes.get(node2).containsKey(node1))
            edgeNum--;

        AllNodes.get(node1).put(node2 , AllNodes.get(node2).get(node2));
        edgeWeight.get(node1).put(node2 ,w);

        AllNodes.get(node2).put(node1 , AllNodes.get(node1).get(node1));
        edgeWeight.get(node2).put(node1 ,w);

        edgeNum++;
        mc++;
    }

    @Override
    public Collection<node_info> getV() {
        HashMap<Integer , node_info> empty = new HashMap<Integer , node_info>();
        if (myNodesCollection.isEmpty()) return empty.values();

        return myNodesCollection.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        HashMap<Integer , node_info> empty = new HashMap<Integer , node_info>();
        if (!AllNodes.containsKey(node_id)) return empty.values();
        return AllNodes.get(node_id).values();
    }

    @Override
    public node_info removeNode(int key) {
        if(!AllNodes.containsKey(key)) return null;
        for (node_info itr : getV(key)
        ) {
            if (itr.getKey() != key){
            AllNodes.get(itr.getKey()).remove(key);
            edgeWeight.get(itr.getKey()).remove(key);
                edgeNum--;
            }}


        edgeWeight.remove(key);
        AllNodes.remove(key);
        mc++;
        return myNodesCollection.remove(key);

    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (AllNodes.get(node1) == null ||
                AllNodes.get(node2) == null) return;
        if (AllNodes.get(node1).get(node2) == null ||
                AllNodes.get(node2).get(node1) == null) return;
        AllNodes.get(node1).remove(node2);
        AllNodes.get(node2).remove(node1);
        edgeWeight.get(node1).remove(node2);
        edgeWeight.get(node2).remove(node1);
        edgeNum--;
        mc++;
    }

    @Override
    public int nodeSize() {
        return myNodesCollection.size();
    }

    @Override
    public int edgeSize() {
        return edgeNum;
    }

    @Override
    public int getMC() {
        return mc;
    }


    @Override
    public boolean equals(Object a) {

        WGraph_DS A = (WGraph_DS) (a);
        if (A.nodeSize() != this.nodeSize() || A.edgeSize() != this.edgeSize()) return false;

        for (node_info n : this.myNodesCollection.values()) {
            if (!(A.myNodesCollection.containsKey(n.getKey()))) return false;
        }

        for (HashMap<Integer, Double> ha : this.edgeWeight.values()) {

            if (!(A.edgeWeight.containsValue(ha))) return false;

        }
            return true;
        }


    }
