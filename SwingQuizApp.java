package secondyear;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingQuizApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private int scoreQ1 = 0;
    private int scoreQ2 = 0;
    private int timeLeft = 60; // seconds per question
    private Timer timer;

    private JLabel timerLabel1, timerLabel2;
    private JLabel q1ResultLabel, q2ResultLabel;

    // Q1
    private JRadioButton q1Opt1, q1Opt2, q1Opt3, q1Opt4;
    private ButtonGroup q1Group;

    // Q2
    private JCheckBox q2Opt1, q2Opt2, q2Opt3, q2Opt4;

    public SwingQuizApp(){
        setTitle("Timed Swing Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // ---------- Page 1: Question 1 ----------
        JPanel q1Panel = new JPanel(new BorderLayout());
        JLabel q1Label = new JLabel("1. What is the capital of India?");
        q1Label.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel1 = new JLabel("Time left: 60", SwingConstants.RIGHT);

        JPanel q1TopPanel = new JPanel(new BorderLayout());
        q1TopPanel.add(q1Label, BorderLayout.WEST);
        q1TopPanel.add(timerLabel1, BorderLayout.EAST);

        q1Opt1 = new JRadioButton("Mumbai");
        q1Opt2 = new JRadioButton("New Delhi");
        q1Opt3 = new JRadioButton("Kolkata");
        q1Opt4 = new JRadioButton("Chennai");

        q1Group = new ButtonGroup();
        q1Group.add(q1Opt1);
        q1Group.add(q1Opt2);
        q1Group.add(q1Opt3);
        q1Group.add(q1Opt4);

        JPanel q1Options = new JPanel(new GridLayout(0, 1));
        q1Options.add(q1Opt1);
        q1Options.add(q1Opt2);
        q1Options.add(q1Opt3);
        q1Options.add(q1Opt4);

        JButton nextButton1 = new JButton("Next");
        q1ResultLabel = new JLabel("Marks: 0/1");

        JPanel q1Bottom = new JPanel();
        q1Bottom.add(q1ResultLabel);
        q1Bottom.add(nextButton1);

        q1Panel.add(q1TopPanel, BorderLayout.NORTH);
        q1Panel.add(q1Options, BorderLayout.CENTER);
        q1Panel.add(q1Bottom, BorderLayout.SOUTH);

        // ---------- Page 2: Question 2 ----------
        JPanel q2Panel = new JPanel(new BorderLayout());
        JLabel q2Label = new JLabel("2. Which crops are available in all seasons in India? (Multiple correct)");
        q2Label.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel2 = new JLabel("Time left: 60s", SwingConstants.RIGHT);

        JPanel q2TopPanel = new JPanel(new BorderLayout());
        q2TopPanel.add(q2Label, BorderLayout.WEST);
        q2TopPanel.add(timerLabel2, BorderLayout.EAST);

        q2Opt1 = new JCheckBox("Rice");
        q2Opt2 = new JCheckBox("Maize");
        q2Opt3 = new JCheckBox("Raagi");
        q2Opt4 = new JCheckBox("Jute");

        JPanel q2Options = new JPanel(new GridLayout(0, 1));
        q2Options.add(q2Opt1);
        q2Options.add(q2Opt2);
        q2Options.add(q2Opt3);
        q2Options.add(q2Opt4);

        JButton submitButton = new JButton("Submit");
        q2ResultLabel = new JLabel("Marks: 0/3");

        JPanel q2Bottom = new JPanel();
        q2Bottom.add(q2ResultLabel);
        q2Bottom.add(submitButton);

        q2Panel.add(q2TopPanel, BorderLayout.NORTH);
        q2Panel.add(q2Options, BorderLayout.CENTER);
        q2Panel.add(q2Bottom, BorderLayout.SOUTH);

        // ---------- Add to Card Panel ----------
        cardPanel.add(q1Panel, "Q1");
        cardPanel.add(q2Panel, "Q2");

        add(cardPanel, BorderLayout.CENTER);

        // ---------- Timer Setup ----------
        startTimer(1); // for question 1

        // Next Button Action
        nextButton1.addActionListener(e -> {
            checkAnswer1();
            nextQuestion(2);
        });

        // Submit Button Action
        submitButton.addActionListener(e -> {
            checkAnswer2();
            showResult();
        });

        setVisible(true);
    }

    private void startTimer(int questionNumber) {
        timeLeft = 60;
        JLabel timerLabel = (questionNumber == 1) ? timerLabel1 : timerLabel2;

        if (timer != null && timer.isRunning()) timer.stop();

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerLabel.setText("Time left: " + timeLeft + "s");
                timeLeft--;

                if (timeLeft < 0) {
                    timer.stop();
                    if (questionNumber == 1) {
                        checkAnswer1();
                        nextQuestion(2);
                    } else {
                        checkAnswer2();
                        showResult();
                    }
                }
            }
        });
        timer.start();
    }

    private void checkAnswer1() {
        scoreQ1 = 0;
        if (q1Opt2.isSelected()) scoreQ1 = 1;
        q1ResultLabel.setText("Marks: " + scoreQ1 + "/1");
    }

    private void checkAnswer2() {
        scoreQ2 = 0;
        if (q2Opt1.isSelected()) scoreQ2++;
        if (q2Opt2.isSelected()) scoreQ2++;
        if (q2Opt3.isSelected()) scoreQ2++;
        q2ResultLabel.setText("Marks: " + scoreQ2 + "/3");
    }

    private void nextQuestion(int questionNumber) {
        if (questionNumber == 2) {
            cardLayout.show(cardPanel, "Q2");
            startTimer(2);
        }
    }

    private void showResult() {
        timer.stop();
        int total = scoreQ1 + scoreQ2;
        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\n\nQuestion 1: " + scoreQ1 + "/1\nQuestion 2: " + scoreQ2 + "/3\n\nTotal: " + total + "/4",
                "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new SwingQuizApp();
    }
}
