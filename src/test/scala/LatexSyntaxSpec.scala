import zio.test.*

object LatexSyntaxSpec extends ZIOSpecDefault:
  def spec = suite("LatexSyntaxSpec")(
    suite("containsCommands")(
      test("when contains commands") {
        val input =
          """Function \inlinecode{f: Any => Either[E, A]} function accepts \textit{anything} as its argument"""
        assertTrue(LatexSyntax.containsCommands(input))
      },
      test("when does not contain commands") {
        val input = "Hello World, how are you?"
        assertTrue(!LatexSyntax.containsCommands(input))
      }
    ),
    suite("stripCommands")(
      test("one word") {
        val input    = """\textbf{Hello}"""
        val expected = "Hello"
        assertTrue(extractParameter(input) == expected)
      },
      test("sentence with italic and bold") {
        val input    = """Hello \textit{World}, how are \textbf{you}?"""
        val expected = "Hello World, how are you?"
        assertTrue(extractParameter(input) == expected)
      },
      test("sentence with inline code") {
        val input    = """\inlinescala{ZIO[-R, +E, +A]} has three type parameters."""
        val expected = "\"ZIO[-R, +E, +A]\" has three type parameters."
        assertTrue(extractParameter(input) == expected)
      },
      test("sentence with reference") {
        val input    = """\refsource{zio:forking} demonstrates forking and \refsource{zio:joining} joining a fiber."""
        val expected = "Listing 1 demonstrates forking and Listing 2 joining a fiber."
        assertTrue(extractParameter(input) == expected)
      }
    )
  )

  private def extractParameter(text: String): String =
    LatexSyntax.stripCommands(text)
