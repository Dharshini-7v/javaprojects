package secondyear;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class SwingHTTPSBrowser extends JFrame {
    private JTextField urlField;
    private JTextArea displayArea;

    public SwingHTTPSBrowser() {
        super("Swing HTTPS Browser");

        urlField = new JTextField("https://");
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JButton goButton = new JButton("Go");
        goButton.addActionListener(e -> loadPage(urlField.getText()));
        urlField.addActionListener(e -> loadPage(urlField.getText()));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(urlField, BorderLayout.CENTER);
        topPanel.add(goButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadPage(String urlStr) {
        try {
            if (!urlStr.startsWith("http://") && !urlStr.startsWith("https://")) {
                urlStr = "https://" + urlStr;
            }

            URL url = new URL(urlStr);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line).append("\n");
            }
            in.close();
            con.disconnect();

            displayArea.setText(content.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot load page: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingHTTPSBrowser::new);
    }
}


