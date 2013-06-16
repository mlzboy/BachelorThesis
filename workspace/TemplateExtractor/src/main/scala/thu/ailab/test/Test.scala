package thu.ailab.test

import thu.ailab.global._
import thu.ailab.template.TemplateManager
import thu.ailab.template.EssentialNode
import thu.ailab.template.OptionalNode

object Test extends AppEntry {
  def getTemplateStat(templateFile: String) = {
    val tpMan = TemplateManager.recoverTemplates(templateFile)
    var enLength, enCount, onCount = 0
    var onLength = 0.0
    for (tp <- tpMan.getTemplates; tn <- tp.tnArray) {
      tn match {
        case en: EssentialNode => enLength += en.getTreeNodeCount; enCount += 1
        case on: OptionalNode => onLength += on.getAverageLength; onCount += 1
      }
    }
    println("template average: " + 1.0 * 
        tpMan.getTemplates.map(_.tnArray.size).sum / tpMan.getTemplates.size) 
    println("en average: " + enLength * 1.0 / enCount)
    println("on average: " + onLength * 1.0 / onCount)
  }
  getTemplateStat(sys.props("user.home") + "/Programs/BachelorThesis/Data/material/templateFile_news_0.4")
  getTemplateStat(sys.props("user.home") + "/Programs/BachelorThesis/Data/material/templateFile_news_0.5")
  getTemplateStat(sys.props("user.home") + "/Programs/BachelorThesis/Data/material/templateFile_news_0.6")
}
