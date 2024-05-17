//Author kappasutra\\
/*In this code prompts I'll share my "local" keylogger code 
This keylogger usage is not for harmware its user friendly 
And also this code is for education*/
//Also check commanded setBackground color codes to have some fun 
import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
//import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MouseListenerDemo implements KeyListener, MouseListener {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private int clickCount  = 1;

    public static void main(String[] args) {
        MouseListenerDemo keyLogger = new MouseListenerDemo();
        keyLogger.start();
    }

    public void start() {
        JFrame frame = new JFrame("Key Logger");
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        JButton clearButton = createClearButton();
        JButton exitButton = createExitButton(); 

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inputScrollPane, BorderLayout.NORTH);
        frame.getContentPane().add(outputScrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(clearButton, BorderLayout.SOUTH);
        frame.getContentPane().add(exitButton, BorderLayout.EAST); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        inputArea.addKeyListener(this);
        outputArea.addMouseListener(this);
        //outputArea.setBackground(Color.BLUE);
    }

    private JButton createClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearOutputArea();
                clearInputArea();
            }
        });
        return clearButton;
    }
    private JButton createExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitButton;
    }

    private void clearInputArea() {
        inputArea.setText("");
    }

    private void clearOutputArea() {
        outputArea.setText("");
    }

    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        logKeyEvent("Key Pressed: " + key);
    }
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {
        //outputArea.setBackground(Color.ORANGE);
        logMouseEvent("Mouse pressed;" + getClickCount() + "# of clicks: ", e);
    }

    public void mouseReleased(MouseEvent e) {
        //outputArea.setBackground(Color.BLACK);
        logMouseEvent("Mouse released; " + getClickCount() + " # of clicks: ", e);
    }

    public void mouseEntered(MouseEvent e) {
        //outputArea.setBackground(Color.RED);
        logMouseEvent("Mouse entered", e);
    }

    public void mouseExited(MouseEvent e) {
        //outputArea.setBackground(Color.BLUE);
        logMouseEvent("Mouse exited", e);
    }

    public void mouseClicked(MouseEvent e) {
        logMouseEvent("Mouse clicked;" + getClickCount() + " # of clicks: ", e);
        incrementClickCount(); 
    }

    private void logMouseEvent(String eventDescription, MouseEvent e) {
        outputArea.append(eventDescription + " detected on " + e.getComponent().getClass().getName() + "." + System.lineSeparator());
    }
    private void logKeyEvent(String eventDescription) {
        outputArea.append(eventDescription + System.lineSeparator());
    }
    private void incrementClickCount() {
        clickCount++;
    }
    private int getClickCount() {
        return clickCount;
    }
}

