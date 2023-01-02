import scala.util.matching.Regex

object LatexSyntax:

  // Matches latex command in the form of \command{parameter}
  val latexCommandPattern = new Regex("""\\([a-z]+)\{(.+?)\}""", "command", "parameter")

  // Checks if the text contains any latex commands
  def containsCommands(text: String): Boolean =
    latexCommandPattern.unanchored.matches(text)

  /** Removes all latex commands from the text, leaving only parameters.
    *
    * E.g.
    *   - \textit{Hello world} -> Hello world
    *   - \inlinescala{println("Hello world")} -> "println("Hello world")"
    */
  def stripCommands(text: String): String =
    formatCommands(
      text,
      (command, parameter) =>
        command match
          case "inlinescala" | "inlinecode" => quoted(parameter)
          case _                            => parameter
    )

  type CommandFormatter = (command: String, parameter: String) => String
  def formatCommands(text: String, formatter: CommandFormatter): String =
    latexCommandPattern.replaceAllIn(
      text,
      m => formatter(m.group("command"), m.group("parameter"))
    )

  private def quoted(text: String): String =
    s""""$text""""
