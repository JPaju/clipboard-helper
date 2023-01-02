# Clipboard helper

Super simple app for modifying clipboard content, useful when copying LaTeX code.
Removes LaTeX commands (`\command{parameter}`) and replaces them with their parameter.

## Usage
1. Start the application (preferably by running it from SBT)
2. The app prompts to press any key to modify the clipboard content
3. Copy text to clipboard
4. Focus the app window and press any key (for example 'enter')
5. Clipboard content is modified and ready to be pasted
6. Quit the app by pressing 'q' and 'enter'

## TODO
- [ ] Listen to changes in clipboard content and apply the modification automatically
