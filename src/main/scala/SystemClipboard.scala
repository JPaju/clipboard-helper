import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.FlavorEvent
import java.awt.datatransfer.FlavorListener
import java.awt.datatransfer.StringSelection

// Cannot be named 'Clipboard' because it conflicts with awt Clipboard class
object SystemClipboard:

  private lazy val clipboard =
    Toolkit.getDefaultToolkit.getSystemClipboard

  def readFromClipboard(): String =
    clipboard
      .getData(DataFlavor.stringFlavor)
      .toString

  def copyToClipboard(text: String): Unit =
    val stringSelection = new StringSelection(text)
    clipboard.setContents(stringSelection, null)

  def modifyClipboardContent(modify: String => String): Unit =
    val originalContent = readFromClipboard()
    val modifiedContent = modify(originalContent)
    copyToClipboard(modifiedContent)

  // TODO Attempts to attach listeners to clipboard content changes, but it doesn't work
  // def whenClipboardContentChanges(action: String => Any): Unit =
  //   println("Adding new clipboard listener...")
  //   val clipboardListener: FlavorListener = (event: FlavorEvent) =>
  //     val content = event.toString()
  //     println(s"Clipboard content changed, source: ${event.getSource}, current content: $content")
  //     action(content)

  //   clipboard.addFlavorListener(clipboardListener)
  // end whenClipboardContentChanges

  // def removeClipboardListeners(): Unit =
  //   println("Removing clipboard listeners...")
  //   val clipboardListeners = clipboard.getFlavorListeners
  //   clipboardListeners.foreach(clipboard.removeFlavorListener)
