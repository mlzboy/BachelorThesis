package thu.ailab.config

import com.typesafe.config._
import scala.collection.mutable.HashMap
import java.io.File

object MyConfigFactory {
  private val conf = ConfigFactory.load()
  private val configCache = new HashMap[String, MyConfig]
  val logfile = conf.getString("logger.filepath")
  configCache("MyLoggerConfig") = new MyLoggerConfig(simpleExpansion(logfile))
  /**
   * Replace the `~' with user's home directory
   */
  private def simpleExpansion(filepath: String) = 
    if (filepath.startsWith("~" + File.separator)) 
      System.getProperty("user.home") + filepath.substring(1)
    else 
      filepath
  /**
   * Get configuration according to the name
   */
  def get(name: String) = configCache.get(name)
}

