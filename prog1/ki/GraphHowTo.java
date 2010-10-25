package ki;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import ch.unibe.iam.graph.DGraphElementFactory;
import ch.unibe.iam.graph.Edge;
import ch.unibe.iam.graph.Graph;
import ch.unibe.iam.graph.HashGraph;
import ch.unibe.iam.graph.UGraphElementFactory;
import ch.unibe.iam.graph.Vertex;
import ch.unibe.iam.graph.drawing.MyVisualizer;
import ch.unibe.iam.graph.io.GraphReader;
import ch.unibe.iam.graph.io.GraphWriter;
import ch.unibe.iam.graph.io.GxlReader;
import ch.unibe.iam.graph.io.GxlWriter;

/**
 * Demo class showing basic usage of the graph-package.
 * 
 * @author irniger
 */
public class GraphHowTo {

  public static void main(String[] args) {

    /*
     * gerichtete und ungerichtete graphen können erstellt werden.
     * wichtig ist dabei der typ der Factory. 
     * - gerichtete Graphen:   DGraphElementFactory()
     * - ungerichtete Graphen: UGraphElementFactory()
     */ 
    Graph undirectedGraph = new HashGraph(new UGraphElementFactory()); //ungerichtet
    Graph directedGraph = new HashGraph(new DGraphElementFactory()); //gerichtet
    System.err.println("graph 1 directed: " + undirectedGraph.isDirected());
    System.err.println("graph 2 directed: " + directedGraph.isDirected());

    /*
     * Zu Beginn sind die Graphen natürlich leer...
     */
    System.err.println("graph 1 is empty: " + undirectedGraph.isEmpty());
    System.err.println("graph 2 is empty: " + directedGraph.isEmpty());

    /*
     * Knoten einfügen...
     */
    Vertex v1, v2, v3;
    v1 = directedGraph.addVertex();
    v2 = directedGraph.addVertex();
    v3 = directedGraph.addVertex();

    /*
     * Einfache Abfrage der Graphen
     */
    System.err.println("nr of vertices in graph 1: "
        + undirectedGraph.sizeVertices());
    System.err.println("nr of vertices in graph 2: "
        + directedGraph.sizeVertices());

    /*
     * Knoten (allg. GraphElements) können Attribute hinzugefügt 
     * werden. Solche Attribute sind generell vom Typ Object. 
     * Am einfachsten ist es aber, wenn man sich dabei auf String,Double,...
     * beschränkt. (Insbesondere, wenn man I/O verwendet, d.h. die
     * Graphen als Dateien abspeichert.) Die Attribute werden unter
     * einem Schlüssel gespeichert und können unter diesem Schlüssel
     * wieder abgefragt werden.
     */
    v1.setAttribute("color", "red");
    v1.setAttribute("area", new Double(2.0));
    v2.setAttribute("color", "blue");
    v3.setAttribute("color", "green");

    /*
     * Beispiel-Abfrage
     */
    System.out.println(v1.getAttribute("color"));

    /*
     * Analog zu Knoten können auch Kanten zwischen den
     * Knoten eingefügt werden. 
     * ACHTUNG: 
     * die Methode lautet mGraph.addEdge(head,tail).
     * Der Pfeil ist dabei beim Kopf, die eingefügte
     * Kante zeigt also vom tail zum head.
     */
    Edge e1, e2, e3;
    e2 = directedGraph.addEdge(v3, v1); // kante von v1 zu v3
    e1 = directedGraph.addEdge(v2, v1); // kante von v1 zu v2
    e3 = directedGraph.addEdge(v3, v2); // kante von v2 zu v3

    // similar to vertices, edges can also be assigned attributes
    // to store information...
    e1.setAttribute("gewicht", new Float(3.0));
    e2.setAttribute("gewicht", new Float(4.0));
    e3.setAttribute("gewicht", new Float(2.0));
    e1.setAttribute("edgeName", "edge from v1 to v2");
    e2.setAttribute("edgeName", "edge from v1 to v3");
    e3.setAttribute("edgeName", "edge from v2 to v3");

    // den Graphen anzeigen...
    visualize(directedGraph);
    
    /*
     * Graphen können im Gxl-Format abgespeichert werden....
     */
    String outFile = "./test.gxl";
    writeGraph(directedGraph, outFile);

    /*
     * Graphen können im Gxl-Format gelesen werden....
     */
    Graph gelesen = null;
    gelesen = readGraph(outFile);
    
    /*
     * ACHTUNG!!
     * In GXL gibt es nur den Datentyp Float, keinen Datentyp
     * Double. D.h., am einfachsten ist es, für alle Fliesskomma-Variablen
     * den Typ float bzw Float zu verwenden. Die Genauigkeit eines Floats
     * reicht für die Übungsaufgabe auch vollkommen aus
     */

    
    /* 
     * Soviel zum Tutorial. Nun geht's an die Graphsuche.
     * WICHTIG: 
     * - der implementiert Suchalgorithmus soll das Interface GraphSearch einhalten! 
     * - ausserdem soll er ebenfalls im Package "ki" sein.
     */


    /*
     * Wie in der Aufgabe angegeben soll der Suchgraph im
     * Gxl-Format eingelesen werden. 
     */
    Graph searchGraph = null;
    String inputGraphFile = "path to input file";
    searchGraph = readGraph(inputGraphFile);

    /*
     * Nach erfolgreichem einlesen, kann die Suche
     * eigentlich beginnen.
     */
    if (searchGraph != null) {

      /*
       * hier wird die graphsuche gemacht. die folgenden
       * 3 schritte müssen eingehalten werden:
       * 1. angabe des suchgraphen
       * 2. starten der suche
       * 3. gefundener lösungspfad als vertex-array
       *    mit startknoten an position 0 und endknoten
       *    an position 1.
       * insbesondere fehlen hier die angabe von start und endknoten
       * sowie die angabe der funktion f=g+h. beides kann/soll 
       * individuell gelöst werden.
       * 
       * Start und Endknoten müssen noch aus dem Graphen extrahiert
       * werden. Am einfachsten iteriert man dabei über die Knoten
       * des Graphen, etwa in der Art (die Prozeduren isGoalVertex und
       * isStartVertex sind bereits im How-To programmiert):
       *       
       * Vertex goalVertex = null;
       * Vertex startVertex = null;
       * Iterator vIt = searchGraph.vertices();
       * while (vIt.hasNext()) {
       *   Vertex cv = (Vertex) vIt.next();
       *   if (isGoalVertex(cv)) goalVertex = cv;
       *   if (isStartVertex(cv))startVertex = cv;
       * }
       */
      
      try {
        // euer Algorithmus muss das Interface GraphSearch einhalten
        GraphSearch mSearch = null;
      
        // der zu durchsuchende Graph muss explizit gesetzt werden können
        mSearch.setSearchGraph(searchGraph);
      
        // die Suche soll per search-Methode gestartet werden
        mSearch.search();

        // Rückgabe des Resultates
        Vertex[] pathToGoalNode = mSearch.getSolution();

        // der gefundene Pfad kann im Graph-Visualizer angezeigt werden
        for (int i = 0; i < pathToGoalNode.length; i++) {
          pathToGoalNode[i].setAttribute("color", new Integer(-16724992));  // grüne Farbe für Lösungsknoten
        }

        visualize(searchGraph);
      } catch (Exception e) {
        System.err.println("error searching graph");
        e.printStackTrace();
        System.exit(1);
      }
    }

  }

  /**
   * @param searchGraph
   */
  private static void visualize(Graph searchGraph) {
    // define frame, its size and make it visible
    String systemUI = UIManager.getSystemLookAndFeelClassName();

    try {
      UIManager.setLookAndFeel(systemUI);
    } catch (Exception exc) {
      ;
    }

    Frame myFrame = new MyVisualizer(searchGraph);
    myFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    myFrame.setBounds(10, 10, 800, 500); // this time use a predefined frame
                                         // size/position
    myFrame.setVisible(true);
  }

  private static boolean isStartVertex(Vertex pVertex) {
    Object curAttrib = pVertex.getAttribute("isStart");
    if (curAttrib != null) {
      if (curAttrib instanceof Boolean) {
        return ((Boolean) curAttrib).booleanValue();
      }
    }
    return false;
  }

  private static boolean isGoalVertex(Vertex pVertex) {
    Object curAttrib = pVertex.getAttribute("isGoal");
    if (curAttrib != null) {
      if (curAttrib instanceof Boolean) {
        return ((Boolean) curAttrib).booleanValue();
      }
    }
    return false;
  }

  /**
   * @param directedGraph
   * @param outFile
   */
  private static void writeGraph(Graph directedGraph, String outFile) {
    GraphWriter mWriter = new GxlWriter(outFile);
    try {
      // unfortunately, the file format gxl only knows
      // the datatype float (no double). however, we
      // can set the GxlWriter to transform float to
      // doubles, probably losing some precision as a tradeoff.
      // NOTE: this "feature" is specific to the Gxl-I/O classes only
      ((GxlWriter) mWriter).mConvertDoubleToFloat = true;
      mWriter.write(directedGraph);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param searchGraph
   * @return
   */
  private static Graph readGraph(String pFile) {
    GraphReader mReader;
    mReader = new GxlReader(pFile);
    try {
      return mReader.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}