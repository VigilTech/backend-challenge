package com.interview.task.config

import com.interview.task.config.TextSplitterConfig.{DefaultCharsLimit, EmptyText}

import java.security.InvalidParameterException

/**
 * Config class for defining parameters for [[com.interview.task.TextSplitter]]
 * Default values are used only for parsing and cannot be used without override
 */
case class TextSplitterConfig(
    text: String = EmptyText,
    charsLimitPerLine: Int = DefaultCharsLimit,
)

object TextSplitterConfig {
  // default variables for config
  val EmptyText = ""
  val DefaultCharsLimit = 0

  private val errorMessage = "Warning: Could not parse arguments, please rerun program with valid parameters"

  private val parser = new scopt.OptionParser[TextSplitterConfig]("TextSplitter") {
    head("Config for running text splitting text")
    opt[String]('t', "text").required() valueName "<text>" action { (t, c) =>
      c.copy(text = t)
    } text "Text to split line by line"
    opt[Int]('l', "limit").required() valueName "<limit>" action { (l, c) =>
      c.copy(charsLimitPerLine = l)
    } text "Max limit of chars per line"

    help("help") text "prints usage text"

    override def showUsageOnError: Option[Boolean] = Some(true)

    override def reportError(msg: String): Unit = println(s"Warning: $msg")
  }


  def parseArgs(args: Array[String]): TextSplitterConfig =
    parser.parse(args, TextSplitterConfig()) match {
      case Some(conf) => conf
      case None =>
        println(errorMessage)
        throw new InvalidParameterException(errorMessage);
    }

}
