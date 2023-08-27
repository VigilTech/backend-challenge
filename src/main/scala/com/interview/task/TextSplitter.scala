package com.interview.task

import com.interview.task.config.TextSplitterConfig

/**
 * Entry point for splitting text functionality.
 * Can be run from command line due to main method existence.
 */
object TextSplitter {
  def main(args: Array[String]): Unit = {
    val config = TextSplitterConfig.parseArgs(args)
    val processedResult = splitTextByLines(config.text, config.charsLimitPerLine)
    println(s"""
               |Processed text:
               |$processedResult
               |""".stripMargin)
  }

  /**
   * Method for splitting text based on max number of chars in line without word breaking
   *
   * @param textToSplit - text for splitting
   * @param charLimit - max number of char in the line
   * @return - processed string splitted by charLimit symbols without words breaking
   */
  def splitTextByLines(textToSplit: String, charLimit: Int): String = {
    val wordsWithPunctuation = textToSplit.split("\\s+")
    val firstWord = wordsWithPunctuation.head

    val (wholeText, lastLine) =
      wordsWithPunctuation.tail.foldLeft(("", firstWord)) { case ((textAcc, currentLine), word) =>
        if (currentLine.length + word.length < charLimit) (textAcc, currentLine + " " + word)
        else (textAcc + currentLine + "\n", word)
      }
    wholeText + lastLine
  }
}
