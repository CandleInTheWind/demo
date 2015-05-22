package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ClientSide extends JPanel {

    private final static String GENERATE = "GENERATE";
    private final static String VERIFIED = "VERIFIED";

    public ClientSide() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeButtonPanel("Сгенерировать ключи", GENERATE);
        tabbedPane.addTab("Генерация ключей", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JComponent panel4 = makeButtonPanel("Верефицировать ключ", VERIFIED);
        panel4.setPreferredSize(new Dimension(400, 120));
        tabbedPane.addTab("Верификация ключа", panel4);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeButtonPanel(String text, String type) {
        JPanel panel = new JPanel(false);
        final JButton jButton = new JButton(text);
        jButton.setPreferredSize(new Dimension(300, 100));
        ActionListener l;
        if (type.equals(GENERATE)) {
            l = new ActionListenerForGenerate(jButton);
        } else if (type.equals(VERIFIED)) {
            l = new ActionListenerForVerified(jButton);
        } else {
            throw new IllegalArgumentException("Vse ochen ploho");
        }
        jButton.addActionListener(l);
        panel.add(jButton, BorderLayout.CENTER);
        return panel;
    }

    private class ActionListenerForGenerate implements ActionListener {

        private final JButton jButton;

        private ActionListenerForGenerate(JButton jButton) {
            this.jButton = jButton;
        }

        public void actionPerformed(ActionEvent e) {
            jButton.setText(e.getActionCommand() + GENERATE);
        }
    }

    private class ActionListenerForVerified implements ActionListener {

        private final JButton jButton;

        private ActionListenerForVerified(JButton jButton) {
            this.jButton = jButton;
        }

        public void actionPerformed(ActionEvent e) {
            jButton.setText(e.getActionCommand() + VERIFIED);
        }
    }

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
