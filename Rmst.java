// Algorithm to find Minimum Spanning 
// Tree of a given connected, undirected and 
// weighted graph 


import java.util.Scanner;

class Graph
{ 
    class Edge { 
        int src, dest, weight; 
        Edge() { 
            src = dest = weight = 0; 
        } 
    }; 
  
    class subset {
        int parent,rank;
        subset() {
            parent=rank=0;
        }
    };
    int V, E; 
    Edge edge[]; 
    subset subsets[];
    Graph(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[e]; 
        subsets =new subset[v];
     
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
        for ( v = 0; v < V; ++v)
            subsets[v] = new subset();    // Allocate memory for creating V subsets.
	
    } 
    
    
    
    void RandomizedMST(Graph graph)
{
	// Get data of given graph
	int V = graph.V, E = graph.E;
	Edge edge[] = graph.edge;
        subset subsets[]=graph.subsets;
       
	// An array to store index of the cheapest edge of
	// subset. The stored index for indexing array 'edge[]'
	int cheapest[] = new int[V]; 
	
        // Create V subsets with single elements
	for (int  v = 0; v < V; ++v)
	{
            subsets[v].parent = v;
            subsets[v].rank = 0;
            cheapest[v] = -1;
	}
        

	// Initially there are V different trees.
	// Finally there will be one tree that will be MST
	int numTrees = V;
	int MSTweight = 0;

	// Keep combining components (or sets) until all
	// compnentes are not combined into single MST.
	while (numTrees > 1)
	{
		// Everytime initialize cheapest array
		for (int v = 0; v < V; ++v)
		{
			cheapest[v] = -1;
		}

		// Traverse through all edges and update
		// cheapest of every component
		for (int i=0; i<E; i++)
		{
			// Find components (or sets) of two corners
			// of current edge
			int set1 = find(subsets, edge[i].src);
			int set2 = find(subsets, edge[i].dest);
                       // System.out.println(""+set1+" " +set2);
                        // If two corners of current edge belong to
			// same set, ignore current edge
			if (set1 == set2)
				continue;

			// Else check if current edge is closer to previous
			// cheapest edges of set1 and set2
			else
			{
			if (cheapest[set1] == -1 || edge[cheapest[set1]].weight > edge[i].weight)
				cheapest[set1] = i;
                                  

			if (cheapest[set2] == -1 || edge[cheapest[set2]].weight > edge[i].weight)
				cheapest[set2] = i;
                             
			}
		}
                

		// Consider the above picked cheapest edges and add them
		// to MST
		for (int i=0; i<V; i++)
		{
			// Check if cheapest for current set exists
			if (cheapest[i] != -1)
			{
				int set1 = find(subsets, edge[cheapest[i]].src);
				int set2 = find(subsets, edge[cheapest[i]].dest);

				if (set1 == set2)
					continue;
                                
				MSTweight += edge[cheapest[i]].weight;
				System.out.println("Edge  " + edge[cheapest[i]].src + " - "+edge[cheapest[i]].dest+
                                        "  included in MST\n");

				// Do a union of set1 and set2 and decrease number
				// of trees
				Union(subsets, set1, set2);
				numTrees--;
			}
		}
	}

	   System.out.println("Cost of MST is "+ MSTweight);
	return;
}
  

int find( subset subsets[], int i)
{
	// find root and make root as parent of i
	// (path compression)
	if (subsets[i].parent != i)
            subsets[i].parent =find(subsets, subsets[i].parent);

	return subsets[i].parent;
}

// A function that does union of two sets of x and y
// (uses union by rank)
void Union( subset subsets[], int x, int y)
{
	int xroot = find(subsets, x);
	int yroot = find(subsets, y);

	// Attach smaller rank tree under root of high
	// rank tree (Union by Rank)
	if (subsets[xroot].rank < subsets[yroot].rank)
		subsets[xroot].parent = yroot;
	else if (subsets[xroot].rank > subsets[yroot].rank)
		subsets[yroot].parent = xroot;

	// If ranks are same, then make one as root and
	// increment its rank by one
	else
	{
		subsets[yroot].parent = xroot;
		subsets[xroot].rank++;
	}
}
}
public class Rmst {
    public static void main(String[] args) {
        int V = 9; // Number of vertices in graph
	int E = 14; // Number of edges in graph
	Scanner sc=new Scanner(System.in);
        Graph graph = new Graph(V, E); 
        
    // For dynamic input
    /*  System.out.println("Enter no. of Vertices: ");
        int V=sc.nextInt();
        System.out.println("Enter no. of  Edges: ");
        int E=sc.nextInt();
        System.out.println("Enter graph entries:(Src Dest weight in order)");
        for(int i=0;i<E;i++){
            graph.edge[i].src=sc.nextInt();
            graph.edge[i].dest =sc.nextInt();
            graph.edge[i].weight =sc.nextInt();
        }*/
        
        
      	// add edge 0-1
	graph.edge[0].src = 0;
	graph.edge[0].dest = 1;
	graph.edge[0].weight = 4;

	// add edge 0-7
	graph.edge[1].src = 0;
	graph.edge[1].dest = 7;
	graph.edge[1].weight = 8;

	// add edge 1-2
	graph.edge[2].src = 1;
	graph.edge[2].dest = 2;
	graph.edge[2].weight = 8;

	// add edge 1-7
	graph.edge[3].src = 1;
	graph.edge[3].dest = 7;
	graph.edge[3].weight = 11;

	// add edge 2-3
	graph.edge[4].src = 2;
	graph.edge[4].dest = 3;
	graph.edge[4].weight = 7;

	// add edge 2-8
	graph.edge[5].src = 2;
	graph.edge[5].dest = 8;
	graph.edge[5].weight = 2;

	// add edge 2-5
	graph.edge[6].src = 2;
	graph.edge[6].dest = 5;
	graph.edge[6].weight = 4;

	// add edge 3-4
	graph.edge[7].src = 3;
	graph.edge[7].dest = 4;
	graph.edge[7].weight = 9;

	// add edge 3-5
	graph.edge[8].src = 3;
	graph.edge[8].dest = 5;
	graph.edge[8].weight = 14;

	// add edge 4-5
	graph.edge[13].src = 4;
	graph.edge[13].dest = 5;
	graph.edge[13].weight = 10;

	// add edge 5s-6
	graph.edge[9].src = 5;
	graph.edge[9].dest = 6;
	graph.edge[9].weight = 2;

	// add edge 6-8
	graph.edge[10].src = 6;
	graph.edge[10].dest = 8;
	graph.edge[10].weight = 6;

	// add edge 6-7
	graph.edge[11].src = 6;
	graph.edge[11].dest = 7;
	graph.edge[11].weight = 1;

	// add edge 7-8
	graph.edge[12].src = 7;
	graph.edge[12].dest = 8;
	graph.edge[12].weight = 7;
        graph.RandomizedMST(graph);

    }
}
