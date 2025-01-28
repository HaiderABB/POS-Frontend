package SCD.ui.Common;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class LoginPage extends JFrame implements Props {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;

    public LoginPage(String prefix) {
        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Props.fg);
        loginPanel.setBounds(0, 0, 800, 600);

        JLabel logo = new JLabel(new ImageIcon("logo5.png"));

        logo.setBounds(350, 50, 100, 100);
        loginPanel.add(logo);

        JLabel welcomeText = new JLabel("Welcome Back,", JLabel.CENTER);
        welcomeText.setBounds(250, 180, 300, 30);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 22));
        loginPanel.add(welcomeText);

        JLabel signInText = new JLabel("Sign in to continue", JLabel.CENTER);
        signInText.setBounds(250, 220, 300, 20);
        signInText.setFont(new Font("Arial", Font.PLAIN, 16));
        signInText.setForeground(Props.txtColor);
        loginPanel.add(signInText);

        usernameField = new JTextField(prefix);
        usernameField.setBounds(250, 260, 300, 50);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField.setEditable(true);
        addNonEditablePrefix(usernameField, prefix);
        loginPanel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 330, 300, 50);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(250, 400, 300, 50);
        loginButton.setBackground(Props.bg);
        loginButton.setForeground(Props.fg);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(loginButton);

        add(loginPanel);
        setLocationRelativeTo(null);
    }

    private void addNonEditablePrefix(JTextField textField, String prefix) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (offset >= prefix.length()) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (offset >= prefix.length()) {
                    super.remove(fb, offset, length);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (offset >= prefix.length()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        textField.setText(prefix); // scd- proj initSet the prefix
    }

    public String getUsername() {
        return usernameField.getText(); // scd- proj initReturns the full text including the prefix
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
