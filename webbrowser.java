package secondyear;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class webbrowser extends JFrame {
    private JTextField urlField;
    private JEditorPane displayPane;
    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private ArrayList<String> urls = new ArrayList<>();

    public webbrowser() {
        super("Clickable History Browser");

        // URL input
        urlField = new JTextField("https://");
        urlField.addActionListener(e -> loadPage(urlField.getText()));

        // Display area
        displayPane = new JEditorPane();
        displayPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayPane);

        // Buttons
        JButton goButton = new JButton("Go");
        JButton refreshButton = new JButton("Refresh");

        goButton.addActionListener(e -> loadPage(urlField.getText()));
        refreshButton.addActionListener(e -> refreshPage());

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(goButton);
        buttonPanel.add(refreshButton);
        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(urlField, BorderLayout.CENTER);

        // History list
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setFixedCellWidth(200);

        // Click listener for history
        historyList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) { // double-click to open
                    int index = historyList.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        loadPage(urls.get(index), false);
                    }
                }
            }
        });

        // Split pane: browser display + history
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(historyList), scrollPane);
        splitPane.setDividerLocation(200);

        // Frame layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadPage(String url) {
        loadPage(url, true);
    }

    private void loadPage(String url, boolean addToHistory) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            displayPane.setPage(new URL(url));
            urlField.setText(url);

            if (addToHistory) {
                urls.add(url);
                historyModel.addElement(url);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading page: " + e.getMessage());
        }
    }

    private void refreshPage() {
        if (displayPane.getPage() != null) {
            loadPage(displayPane.getPage().toString(), false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(webbrowser::new);
    }
}
