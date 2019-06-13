package com.example.kk.pitch.Controller;

import java.util.LinkedList;
import java.lang.Object;
import java.util.NoSuchElementException;

// cam be used to see if a person to be added is a friend of a friend, will be used to recommend adding users like on linkedin
// VA array can determine how 'deep' the search goes

public class BreadthFirstSearch {
    private LinkedList<Integer> list[];
    ////////////////////////////////////////////////
    // CHANGED n to static WHEN IMPLEMENTING PART 4 IF IT BROKE OTHER PARTS, REVERT
    ///////////////////////////////////////////////////////////////////
    static int n;   // size of list

    // Constructor to initialize list
    //public static void constructor(int n){

    BreadthFirstSearch(int n){
        this.n = n;
        list = new LinkedList[n];

        for (int i = 0; i < n; i++){
            list[i] = new LinkedList<>();
        }
        //}
    }


    //part 3
    private static class VertexInfo{
        public int distance = -1;
        public int parent = -1;
        //private LinkedList<Integer> l[];

        VertexInfo(int distance, int parent){
            this.distance = distance;
            this.parent = parent;
            /*
            l = new LinkedList[n];

            for (int i = 0; i < n; i++){
                l[i] = new LinkedList<>();
            }
            */
        }
    }

    //////////////////////////////
    // /// /// PART 4 /// /// ////
    //////////////////////////////
    private static class TreeNode{
        public int vertexNumber;
        public LinkedList<TreeNode> childrenList;

        TreeNode(int vertexNumber, LinkedList<TreeNode> childrenList){
            this.vertexNumber = vertexNumber;
            this.childrenList = childrenList;
        }
    }


    // returns ROOT of breadth-first-tree for the given 's' source vertex
    private TreeNode buildTree(int s){
        // we will return this once we build the tree
        TreeNode root;

        // create a VA array to understand who has what parent in the tree
        VertexInfo[] VA = BFS(s);

        // We need to take information from here and figure out how to build the TreeNodes and their childrenList based on .parent
        //printVertexInfo(VA);

        // Create a list of all the tree nodes to then fill
        LinkedList<TreeNode> nodeStorage = new LinkedList<TreeNode>();

        // Go through and populate the list
        for (int i = s; i < n; i++){
            LinkedList<TreeNode> childrenListTemp = new LinkedList<TreeNode>();
            TreeNode treeNodeTemp = new TreeNode(i, childrenListTemp);
            nodeStorage.add(treeNodeTemp);
        }

        for (int i = (s+1); i < n; i ++){
            int parent = VA[i].parent;

            // get the parents' childrenList and add this tree node to it
            nodeStorage.get(parent).childrenList.add(nodeStorage.get(i));
        }

        // set the root to the source vertex provided
        root = nodeStorage.get(s);

        return root;
    }

    // function that does work to print out bfs tree with correct spacing
    public void printTree3(TreeNode root, int depth){
        if (root == null){
            return;
        }
        String output = "";
        for (int k = 0; k < (depth * 4); k ++){
            output += " ";
        }
        System.out.println(output + (root.vertexNumber+1) );

        TreeNode printTemp;
        while(true){
            try{
                printTemp = root.childrenList.removeFirst();

            } catch (NoSuchElementException e){
                //System.out.println("No more children in this list");
                return;
            }
            printTree2(printTemp, depth + 1);
        }
        //System.out.println("segtest2");
        //printTree2(printTemp);

    }

    // recursive wrapper
    public void printTree2(TreeNode root, int depth){
        printTree3(root, depth);
    }

    // Prints breadth-first-tee for a given source vertex 's'
    // 's' is given by natural numbering (gharibians tests)
    public void printTree(int s){
        int depth = 0;
        // obtain root node then go through and print tree
        TreeNode root = buildTree(s-1);

        // recursive call to print the tree
        printTree2(root, depth);
        // TreeNode temp;
        // TreeNode temp2;

        // Print out root node
        //System.out.println("Root: " + (root.vertexNumber+1));

        // // Iterate thru root node's childrenList and print them
        // for(int i = 0; i < root.childrenList.size(); i++){
        //     // set this child as a temp node
        //     temp = root.childrenList.get(i);
        //     // Print out the first child's vertex number
        //     System.out.println(temp.vertexNumber+1);
        //     // Iterate thru temp's children
        //     for (int j = 0; j < temp.childrenList.size(); j++){
        //         temp2 = root.childrenList.get(j);
        //         System.out.println(temp2.vertexNumber+1);
        //     }
        // }


    }

    //////////////////////////////////
    // /// /// END PART 4 /// /// ////
    //////////////////////////////////







    // part 3
    private VertexInfo[] BFS(int s){
        //int N = digraph.n;
        VertexInfo[] VA = new VertexInfo[n];

        // must add new vertex info's one by one into array
        for (int h = 0; h < n; h++){
            VA[h] = new VertexInfo(-1, -1);
        }
        //System.out.println("N: " + N);

        // initialize all items in VA array to -1
        ///// changed int u=1 to =0 ////
        //System.out.println("N: " + N);
        // for (int u=0; u<N; u++){
        //     /// debug
        //     System.out.println(u);
        //     /// debug
        //     VA[u].distance = -1;
        //     VA[u].parent = -1;
        // }

        VA[s].distance = 0;

        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.offer(s);
        //System.out.println("S: " + s);

        //debug
        //printVertexInfo(digraph, VA);


        while(queue.size() != 0){
            int u = queue.remove();

            for (Integer i : list[u]){
                //System.out.println("Setting info for Source " + (u) + " and Neighbor " + (i));
                if(VA[i].distance == -1){
                    //System.out.println("VA[" + i + "].distance = -1");
                    VA[i].distance = VA[u].distance +1;
                    VA[i].parent = u;
                    queue.offer(i);
                }
            }
        }

        return VA;
    }

    public void printVertexInfo(VertexInfo[] VA){
        int i = 0;
        int j = 0;

        for (i = 0; i < n; i++){
            System.out.println("Natural Vertex: " + (i+1));
            System.out.println(".distance = " + VA[i].distance);
            System.out.println("Natual .parent = " + (VA[i].parent + 1));
        }
    }

    public boolean isTherePath(BreadthFirstSearch digraph, int from, int to){
        //System.out.println("in isTherePath");

        VertexInfo[] VA = BFS(from);
        if(VA[to].distance == -1){
            System.out.println("isTherePath: No Path");
            return false;
        }
        else{
            System.out.println("isTherePath: Yes Path");
            return true;
        }
    }

    public int lengthOfPath(BreadthFirstSearch digraph, int from, int to){
        //System.out.println("in lengthOfPath");
        //System.out.println("From: " + from + " To: " + to);
        VertexInfo[] VA = BFS(from);
        int length;

        //debug
        //printVertexInfo(digraph, VA);

        if(VA[to].distance == -1){
            //if no path exists, we are outputting -1 as the length of the path
            //System.out.println("lengthOfPath: No Path");
            length = -1;
        }
        else{
            length = 0;
            while(from != to){
                length = length + 1;
                to = VA[to].parent;
            }
        }
        //length = length + 1;
        return length;
    }


    public void printPath(BreadthFirstSearch digraph, int from, int to){
        // Debug
        //System.out.println("in print path");
        VertexInfo[] VA = BFS(from);
        String output = "";
        if(VA[to].distance == -1){
            System.out.println("no path");
        }
        else{
            // String output = "";
            while(from != to){
                output = "->" + (to+1) + output;
                to = VA[to].parent;
            }
            output = (from+1) + output;
            System.out.println(output);
        }
    }


    public void addEdge(int from, int to){
        // from--; // convert from Natural Numbers
        // to--;
        // ALREADY IN NATURAL

        // check if it already exists
        int sublistIndex = 0;   // track where we are in top level list
        for (LinkedList<Integer> sublist : list){
            for (Integer i : sublist){
                if (   (i == to) && (sublistIndex == from)   ){
                    return;         // we already have an entry for this, do nothing
                }
            }
            sublistIndex++;
        }
        // does not exist in list; add edge
        this.list[from].add(to);
    }

    public void deleteEdge(int from, int to){
        // from--; // convert from Natural Numbers
        // to--;
        // ALREADY IN NATURAL
        int del = 0;    //

        // check if it already exists
        int sublistIndex = 0;   // track where we are in top level list
        for (LinkedList<Integer> sublist : list){
            for (Integer i : sublist){
                if (   (i == to) && (sublistIndex == from)   ){
                    del = 1;    // we can delete this
                    // this.list[from].remove(this.list[from].indexOf(to));         // we already have an entry for this, delete it
                }
            }
            sublistIndex++;
        }
        if (del == 1){
            this.list[from].remove(this.list[from].indexOf(to));         // we already have an entry for this, delete
        }
        // does not exist in list, do nothing
    }

    // Counts edges in graph
    public int edgeCount(){
        int numEdges = 0;

        for(int i = 0; i < n; i ++){
            for (Integer item : list[i]){
                numEdges++;
            }
        }
        return numEdges;
    }

    // returns n, the number of vertices in the graph
    public int vertexCount(){
        return n;
    }

    public void print(){
        System.out.println("\nThe graph is the following:");
        for(int i = 0; i < n; i ++){
            System.out.print( (i + 1) + " is connected to: ");
            for (Integer item : list[i]){
                // if we are at end of list
                if ( list[i].indexOf(item) == (list[i].size()-1)   )  {
                    System.out.print( (item + 1));
                }
                else{
                    System.out.print( (item + 1) + ", ");
                }
            }
            System.out.println("");
        }
    }


    // TESTED WORKING
    // Counts number of indegrees to a vertice (arrows pointing towards it)
    private int[] indegrees(BreadthFirstSearch digraph){
        int N = digraph.n;
        int[] indegrees = new int[N];
        for (int i = 0; i<N; i++)
            indegrees[i] = 0;
        for(int u = 0; u<N; u++){
            //for(int v = 0; v<list.length; v++)
            for (Integer item : list[u]){
                indegrees[item] = indegrees[item] + 1;
            }
        }
        // debugging
        // for (int i = 0; i < indegrees.length; i++){
        //     System.out.println("Indegree at Vertex: " + (i+1) + ": " + indegrees[i]);
        // }
        return indegrees;
    }



    public int[] topSort(BreadthFirstSearch digraph){
        int N = digraph.n;
        // System.out.println("N: " + N);

        int[] indegrees = indegrees(digraph);
        int[] A = new int[N];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int u = 0; u<N; u++){
            if (indegrees[u] == 0)
                queue.offer(u);
        }
        // debugging
        // for (int i = 0; i < queue.size(); i++){
        //     System.out.println("Queue[" + (i) + "]: " + (queue.get(i) + 1)  );
        // }
        int i = 0;
        while(!queue.isEmpty()){
            // U is the next vertex in the queue, starts with the vertex with 0 indegrees
            int u = queue.remove();
            // Add this vertex (with 0 indegrees) to result list
            A[i] = u;
            // Increment Result List Pointer (i)
            i=i+1;

            //for(int v=0; v<list.length; v++){
            for (Integer item : list[u]){
                indegrees[item] = indegrees[item]-1;
                if(indegrees[item] == 0){
                    //System.out.println("Adding Vertex to Queue: " + (item + 1));
                    queue.offer(item);

                }
            }
        }
        // check for cyclic
        if (i != N){
            throw new IllegalArgumentException("The Graph has a Cycle");
        }
        return A;
    }

}

class BreadthFirstSearchTest{


    public static void edgeCountWork(BreadthFirstSearch digraph){
        System.out.print("\nNumber of Edges is: ");
        System.out.println(digraph.edgeCount());
    }
    public static void vertexCountWork(BreadthFirstSearch digraph){
        System.out.print("\nNumber of Vertices is: ");
        System.out.println(digraph.vertexCount());
    }

    public static void addEdgeWork(BreadthFirstSearch digraph, String From, String To){
        // Prompt for To and From
        System.out.print("From: ");
        int from = Integer.parseInt(From) - 1;
        System.out.print("To: ");
        int to = Integer.parseInt(To) - 1;

        digraph.addEdge(from, to);

        System.out.println("\nAdded Edge (" + (from+1) + "," + (to+1) + ")");


    }

    public static void delEdgeWork(BreadthFirstSearch digraph, String From, String To){
        // Prompt for To and From
        System.out.print("From: ");
        int from = Integer.parseInt(From) - 1;
        System.out.print("To: ");
        int to = Integer.parseInt(To) - 1;

        digraph.deleteEdge(from, to);

        System.out.println("\nDeleted Edge (" + (from+1) + "," + (to+1) + ")");

    }


    public static void topologicalSort(BreadthFirstSearch digraph){
        System.out.println("\nTopological sort of given graph");
        int i = 0;
        int[] A = {1,2};    // initialize this with garbage to avoid compiler error
        // try for cyclic exeption
        try{
            A = digraph.topSort(digraph);
        } catch (IllegalArgumentException e){
            System.err.println("Caught Illegal Argument exception: " + e.getMessage());
            return;
        }

        for(i = 0; i < (A.length-1); i++){
            System.out.print(  (A[i] + 1) + ", ");
        }
        System.out.println((A[i] + 1));
    }

    public static void checkPath(BreadthFirstSearch digraph, String From, String To){
        System.out.print("From: ");
        int from = Integer.parseInt(From) - 1;
        System.out.print("To: ");
        int to = Integer.parseInt(To) - 1;

        //System.out.println("\nsegcheck1");
        boolean check = digraph.isTherePath(digraph, from, to);
        //System.out.println("Check= " + check);
        if(check == true)
            System.out.println("There exists a path");
        else
            System.out.println("No path exists");

    }

    public static void pathLength(BreadthFirstSearch digraph, String Source, String To){
        System.out.print("From: ");
        int from = Integer.parseInt(Source) - 1;
        System.out.print("To: ");
        int to = Integer.parseInt(To) - 1;


        System.out.println("Calling lengthOfPath");
        int length = digraph.lengthOfPath(digraph, from, to);

        System.out.println("Length of Path: " + length);

    }

    public static void printingPath(BreadthFirstSearch digraph, String Source, String To){
        System.out.print("From: ");
        int from = Integer.parseInt(Source) - 1;
        System.out.print("To: ");
        int to = Integer.parseInt(To) - 1;

        digraph.printPath(digraph, from, to);

    }


    public static void breadthFirstTree(BreadthFirstSearch digraph, String Source){
        System.out.print("Source Vertex Number: ");
        int sourceVertex = Integer.parseInt(Source); // not -1 because of natural indexing in Gharibyians tests

        digraph.printTree(sourceVertex);

    }

}
