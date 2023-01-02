import scala.io.StdIn

@main def clipboardApp(): Unit =
  var loop = true
  while loop do
    val userInput  = StdIn.readLine("Press any key to modify clipboard content, 'q' to quit: ")
    val shouldStop = userInput.toLowerCase.startsWith("q")

    if shouldStop then loop = false
    else SystemClipboard.modifyClipboardContent(LatexSyntax.stripCommands)

  println("Exiting...")
