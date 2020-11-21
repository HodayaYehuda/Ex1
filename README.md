# Ex1
README file: 
Ex1 - Hodaya Yehuda

This project is part of my OOP course submission work as a computer science student at Ariel University.

This project includes 2 departments built on 3 interfaces:
WGraph_DS built on the weighted_graph interface.
NodeInfo built on the node_info interface, is a inner class in WGraph_DS.
WGraph_Algo built on the weighted graph_algorithms interface.

Data structures and algorithms I used in the project:
-HashMap
-Priority Queue
-LinkedList
-BFS Algorithm

////////////////////////////


NodeInfo:
The NodeInfo class represents the vertices of a graph.

Each node has 4 fields:
key, info and tag.

key and tag are Integers, Info is a String and the list of neighbors is represented as HashMap.
The key is given by a static integer - counter.

Actions that can be done on the vertices:
getKey, getInfo, setInfo, getTag, setTag.



////////////////////////////


WGraph_DS:
The Graph_DS class represents a graph that contains vertices that are connected to other vertices (thus representing edges).

Each graph has 3 fields:
-myNodesCollection: HashMap<Integer , node_info> 
-AllNodes: HashMap<Integer,HashMap<Integer, node_info>> 
-edgeWeight: HashMap<Integer,HashMap<Integer, Double>> 
-edgeNum
-mc

Those 3 data structor represent the structor of the graph.
-myNodesCollection represent collection of all the nodes in the graph.
-AllNodes is a hashMap with an inner hashMap.
He contain all the nodeInfo of the graph.
Every inner hashMap represent the Neighbors of the key.
The inner hashMap contain the nodeInfo, that I can get them by himself key.
The inner hashMap contain the nodeInfo at the key -1 (see Cartoon description).



<img width="447" alt="allNodes" src="https://user-images.githubusercontent.com/74351324/99884164-b7e39900-2c34-11eb-8c82-06a08dda4ce4.PNG">



-edgeWeight is a hashMap with an inner hashMap.
edgeWeight contain the Weight of the edges.
every inner hashmap contain his node by key (-1).



<img width="461" alt="edge" src="https://user-images.githubusercontent.com/74351324/99884175-d34ea400-2c34-11eb-9c22-41afa8941f09.PNG">




Actions that can be done on a graph:

getNode, hasEdge, addNode, connect, getV, getV, removeNode, removeEdge, nodeSize, edgeSize ,getMC.



////////////////////////////



Graph_Algo is the central class through which operations can be performed on the graph.

The graph has 4 fields:
graph, and 4 HashMapes that represent: color, parent and distance HashMaps are for the use of algorithms.

When running the algorithms, which are based on the BFS and Dijkstra algorithm, these HashMapps change as needed.

Actions that exist in WGraph_Algo:
init - Sends a pointer to a graph.
copy - Performs a deep copy using a copy constructor.
isConnected- checks if the graph is connected, by used BFS algorithm.
shortestPathDist- Returns the distance of the shortest path between two vertices, by used Dijkstra algorithm.
shortestPath - Returns a list of all the vertices that need to be traversed in the short way between two vertices, by used Dijkstra algorithm..
save- save graph to local memory 
load- load graph from local memory


In isConnected function I used BFS algorithm.
Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
The algorithm uses Queue and also changes the HashMaps of the fields.
In isConnected you start going through all the vertices when you start with one particular vertex, and count every vertex that you go through until we have reached all of them. If we went through all the vertices - the graph is linked.
If we passed a smaller number than all the vertices - the graph is not a consonant.

In the shortestPathDist functions I used Dijkstra.
By Mark the nodeInfo inner tag of all the vertices according to their distance (how many vertices need to go minimally until you reach it) from the initial vertex.
In case of shortest path, we update the tag by the shortest dist from the src.
Every time I save the "perent" of the vertax in dedicated hashMap.
This allows you to know the distance of each vertex from the initial vertex.

In the shortestPath function I used Dijkstra, exactly like shortestPathDist function.
After it, I prepared a list by the parent hashmap.

tests - 
based on basic graph (see Cartoon description), graph with a milion vertax & 4 milion edges , and graph with a milion vertax & 10 milion edges.

<img width="481" alt="graph" src="https://user-images.githubusercontent.com/74351324/99884247-64be1600-2c35-11eb-85b9-baa67891af44.PNG">









