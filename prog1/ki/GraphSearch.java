
package ki;

import ch.unibe.iam.graph.Graph;
import ch.unibe.iam.graph.Vertex;

/**
 * einfaches Interface für eine Graphsuche.
 * WICHTIG: der implementiert Suchalgorithmus
 * soll dieses Interface einhalten! Ausserdem
 * soll er ebenfalls im Package "ki" sein.
 */
public interface GraphSearch {

  /**
   * Sets the graph to be searched. The graph can either be directed or
   * undirected.
   * 
   * @param pSearchGraph the graph to be searched
   */
  public void setSearchGraph(Graph pSearchGraph);
  
  /**
   * search a path in the searchgraph, from start- to goalnode.
   *
   */
  public void search();
  
  /**
   * Returns the result of the search, specified through an array of vertices.
   * result[0] is equal to the start vertex, result[result.length] is equal to
   * the goal vertex.
   * 
   * @return Vertex[] result, an ordered array of vertices specifying the path
   *         to the result.
   */
  public Vertex[] getSolution();
  
}
