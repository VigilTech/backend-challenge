package com.interview.task

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TextSplitterSpec extends AnyFlatSpec with Matchers {

  "TextSplitter" should "split text by specific number of characters without breaking words" in {
    val textExample = "I'm a short text to split"
    val charLimit = 15
    val expectedRes =
      """
        |I'm a short
        |text to split
        |""".stripMargin.trim

    val processedText = TextSplitter.splitTextByLines(textExample, charLimit)
    processedText shouldBe expectedRes
  }

  it should "correctly work with punctuation and multiple whitespaces" in {
    val textExample = "I'm a text, with some dots... Have   multiple spaces."
    val charLimit = 10
    val expectedRes =
      """
        |I'm a
        |text, with
        |some
        |dots...
        |Have
        |multiple
        |spaces.
        |""".stripMargin.trim

    val processedText = TextSplitter.splitTextByLines(textExample, charLimit)
    processedText shouldBe expectedRes
  }

  it should "still print the word bigger than char limit" in {
    val textExample = "I'm a word from German: Sehenswürdigkeiten. Wow"
    val charLimit = 15
    val expectedRes =
      """
        |I'm a word from
        |German:
        |Sehenswürdigkeiten.
        |Wow
        |""".stripMargin.trim

    val processedText = TextSplitter.splitTextByLines(textExample, charLimit)
    processedText shouldBe expectedRes
  }

  it should "work on long production text" in {
    val textExample =
      "In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler."
    val charLimit = 40
    val expectedRes =
      """
        |In 1991, while studying computer science
        |at University of Helsinki, Linus
        |Torvalds began a project that later
        |became the Linux kernel. He wrote the
        |program specifically for the hardware he
        |was using and independent of an
        |operating system because he wanted to
        |use the functions of his new PC with an
        |80386 processor. Development was done on
        |MINIX using the GNU C Compiler.
        |""".stripMargin.trim

    val processedText = TextSplitter.splitTextByLines(textExample, charLimit)
    processedText shouldBe expectedRes
  }

  /* This tests can be composed in one with Table-check approach, there is no code duplication in it,
     but this checks in the form above are more manageable and easy to follow
  it should "check everything in one test with Table checking" in {
    import org.scalatest.prop.TableDrivenPropertyChecks._
    val table = Table(
      ("textExample", "charLimit", "expectedResult"),
      (
        "I'm a short text to split",
        15,
        """
          |I'm a short
          |text to split
          |""".stripMargin.trim,
      ),
      (
        "I'm a text, with some dots... Have   multiple spaces.",
        10,
        """
          |I'm a
          |text, with
          |some
          |dots...
          |Have
          |multiple
          |spaces.
          |""".stripMargin.trim,
      ),
      (
        "I'm a word from German: Sehenswürdigkeiten. Wow",
        15,
        """
          |I'm a word from
          |German:
          |Sehenswürdigkeiten.
          |Wow
          |""".stripMargin.trim,
      ),
      (
        "In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler.",
        40,
        """
          |In 1991, while studying computer science
          |at University of Helsinki, Linus
          |Torvalds began a project that later
          |became the Linux kernel. He wrote the
          |program specifically for the hardware he
          |was using and independent of an
          |operating system because he wanted to
          |use the functions of his new PC with an
          |80386 processor. Development was done on
          |MINIX using the GNU C Compiler.
          |""".stripMargin.trim,
      ),
    )

    forAll(table) { (textExample, charLimit, expectedRes) =>
      val processedText = TextSplitter.splitTextByLines(textExample, charLimit)
      processedText shouldBe expectedRes
    }
  }*/
}
