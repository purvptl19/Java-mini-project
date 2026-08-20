import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener
{
    private final JTextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public Calculator()
    {
        setTitle("Calculator");
        setSize(320, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        display = new JTextField("0");
        display.setFont(new Font("Monospaced", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setPreferredSize(new Dimension(300, 70));

        String[] buttonLabels =
                {
                    "C", "±", "%", "÷",
                    "7",  "8",  "9",  "×",
                    "4",  "5",  "6",  "−",
                    "1",  "2",  "3",  "+",
                    "0",  ".",  "⌫",  "="
                };

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        for (String label : buttonLabels) {
            JButton btn = createButton(label);
            buttonPanel.add(btn);
        }
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        displayPanel.add(display, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));
        add(displayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    private JButton createButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        switch (label)
        {
            case "÷": case "×": case "−": case "+": case "=":
                btn.setBackground(new Color(255, 149, 0));
                btn.setForeground(Color.WHITE);
                break;
            case "C": case "±": case "%":
                btn.setBackground(new Color(90, 90, 90));
                btn.setForeground(Color.WHITE);
                break;
            default:
                btn.setBackground(new Color(58, 58, 58));
                btn.setForeground(Color.WHITE);
        }
        Color base = btn.getBackground();
        btn.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(base.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(base);
            }
        });

        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        switch (cmd)
        {

            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                if (startNewInput) {
                    display.setText(cmd);
                    startNewInput = false;
                } else
                    {
                        if (display.getText().equals("0")) display.setText(cmd);
                        else display.setText(display.getText() + cmd);
                    }
                break;
            case ".":
                if (startNewInput) { display.setText("0."); startNewInput = false; }
                else if (!display.getText().contains("."))
                    display.setText(display.getText() + ".");
                break;

            case "÷": case "×": case "−": case "+":
                firstNumber = Double.parseDouble(display.getText());
                operator = cmd;
                startNewInput = true;
                break;

            case "=":
                if (!operator.isEmpty())
                {
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = calculate(firstNumber, secondNumber, operator);
                    display.setText(formatResult(result));
                    operator = "";
                    startNewInput = true;
                }
                break;

            case "C":
                display.setText("0");
                firstNumber = 0;
                operator = "";
                startNewInput = true;
                break;

            case "⌫":
                String current = display.getText();
                if (current.length() > 1)
                    display.setText(current.substring(0, current.length() - 1));
                else
                    display.setText("0");
                break;

            case "±":
                double val = Double.parseDouble(display.getText());
                display.setText(formatResult(-val));
                break;

            case "%":
                double pct = Double.parseDouble(display.getText()) / 100.0;
                display.setText(formatResult(pct));
                break;
        }
    }
    private double calculate(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "−" -> a - b;
            case "×" -> a * b;
            case "÷" -> b != 0 ? a / b : Double.NaN;
            default -> b;
        };
    }
    private String formatResult(double result)
    {
        if (Double.isNaN(result)) return "Error";
        if (result == (long) result) return String.valueOf((long) result);
        return String.valueOf(result);
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
