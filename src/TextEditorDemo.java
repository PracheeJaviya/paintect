
import javax.swing.*;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class TextEditorDemo extends JFrame {

   public TextEditorDemo() {

      JPanel cp = new JPanel();

      RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
     
      
      cp.add(sp);

       this.add(cp);
//      this.setTitle("Text Editor Demo");
//      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);

   }

   public static void main(String[] args) {
      // Start all Swing applications on the EDT.
     
        new TextEditorDemo().setVisible(true);
     
   }

}