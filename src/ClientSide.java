import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ClientSide extends JPanel {
    public ClientSide() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = makeButtonPanelGenerate("Сгенерировать ключи");
        tabbedPane.addTab("Генерация ключей", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JComponent panel4 = makeButtonPanelVerified("Верефицировать ключ");
        panel4.setPreferredSize(new Dimension(400, 120));
        tabbedPane.addTab("Верификация ключа", icon, panel4,
                "Верификация ключа");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeButtonPanelGenerate(String text) {
        JPanel panel = new JPanel(false);
        final JButton jButton = new JButton(text);
        jButton.setPreferredSize(new Dimension(300,100));
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton.setText(e.getActionCommand() + " OLOLO");
            }
        });
        panel.add(jButton,BorderLayout.CENTER);
        return panel;
    }

    protected JComponent makeButtonPanelVerified(String text) {
        JPanel panel = new JPanel(false);
        final JButton jButton = new JButton(text);
        jButton.setPreferredSize(new Dimension(300,100));
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton.setText(e.getActionCommand() + " dasdasd");
            }
        });
        panel.add(jButton,BorderLayout.CENTER);
        return panel;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ClientSide.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new ClientSide(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
