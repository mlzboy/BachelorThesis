package thu.ailab.cluster

import thu.ailab.sequence.TagSeqFactory
import thu.ailab.tree._
import thu.ailab.global._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.{HashSet => MHashSet, HashMap => MHashMap}

/**
 * Class for clustering documents
 */
class DocNaiveAggloCluster extends {
  private val dataset = MyConfigFactory.getValue[String]("global.dataset") 
  val id2filename = scala.io.Source.fromFile(
      MyConfigFactory.getValue[String](dataset, "output.id2filename")).getLines.toArray
  val tagSeqFactory = new TagSeqFactory(id2filename)  
  override val initSize = tagSeqFactory.getSize //pre-initialized
} with NaiveAggloCluster {	
  /**
   * overrides
   */
  override def initDistArray() = {
    val ret = new Array[Double]((initSize - 1) * initSize / 2)
    for (
      (line, idx) <- scala.io.Source.fromFile(
        MyConfigFactory.getValue[String](dataset, "output.distFile")).getLines.zipWithIndex
    ) {
      ret(idx) = line.toDouble
    }
    ret
  }
	override val clusterThreshold = MyConfigFactory.getValue[Double](
      "cluster.DocNaiveAggloCluster.clusterThreshold")
  override def composeShow(verbose: Boolean) = {
    if (verbose) (id: Int) => tagSeqFactory.getFilename(id)
    else (id: Int) => id.toString
  }
	/**
	 * Output functions
	 */
  import thu.ailab.utils.Tools.withPrintWriter  
  def writeClusterFile(filename: String) = {
    withPrintWriter(filename){ pw =>
      clusters.foreach(x => pw.println(x._1 + "\n" + x._2.toStr(false)))
    }
  }
  def writeClusterXML(filename: String) = {
    xml.XML.save(filename, 
        <clusters>
        {for (c <- clusters) yield c._2.toXML(false)}
        </clusters>)
  }
}

object TestDocNaiveAggloCluster extends AppEntry {
  import thu.ailab.utils.Tools.timeIt
  val naive = new DocNaiveAggloCluster
  println(timeIt(naive.clustering)._2)
  val dataset = MyConfigFactory.getValue[String]("global.dataset") 
  naive.writeClusterXML(MyConfigFactory.getValue[String](dataset, "output.clusterFile"))
}
