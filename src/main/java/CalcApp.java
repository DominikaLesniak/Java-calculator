import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public class CalcApp extends JFrame {
    private JTextArea screenArea;
    private JTextField inputArea;
    private JButton evaluateButton;
    private JButton reset;
    private JButton exit;
    private JPanel mainPanel;
    private JPanel listPanel;
    private JList fuctionList;
    private JMenuBar menuBar;
    private JMenu menu;
    private ArrayList<String> inputHistory;
    private int histCounter;
    double result;


    public CalcApp() {
        setTitle("Probably a calculator");
        setVisible(true);
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(600, 500));
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        inputArea.requestFocus();
        mainPanel.setBackground(Color.PINK);
        reset = new JButton("reset");
        exit = new JButton("exit");
        inputHistory = new ArrayList<String>();
        histCounter = -1;

        menu = new JMenu("Options");
        menuBar = new JMenuBar();
        menu.add(reset);
        menu.add(exit);

        menuBar.getAccessibleContext().setAccessibleDescription("Calculator menu");
        menuBar.add(menu);
        setJMenuBar(menuBar);


        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (histCounter >= 0) {
                        inputArea.setText(inputHistory.get(histCounter));
                        histCounter--;
                    } else {
                        inputArea.setText("");
                        histCounter = inputHistory.size() - 1;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Expression expression = new Expression(inputArea.getText());
                    if (expression.checkSyntax()) {
                        result = expression.calculate();
                        String currentDisplay = MessageFormat.format( "{1} = {0} \n\n", result, inputArea.getText());
                        inputHistory.add(inputArea.getText());
                        histCounter = inputHistory.size() - 1;
                        screenArea.append(currentDisplay);
                        inputArea.setText("");
                    } else {
                        String errorMessage = expression.getErrorMessage();
                        screenArea.setText(errorMessage);
                        JOptionPane.showMessageDialog(null, "\"" + inputArea.getText() + "\" is not correct input","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        evaluateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Expression expression = new Expression(inputArea.getText());
                if (expression.checkSyntax()) {
                    result = expression.calculate();
                    String currentDisplay = MessageFormat.format( "{1} = {0} \n\n", result, inputArea.getText());
                    inputHistory.add(inputArea.getText());
                    histCounter = inputHistory.size() - 1;
                    screenArea.append(currentDisplay);
                    inputArea.setText("");
                } else {
                    String errorMessage = expression.getErrorMessage();
                    screenArea.setText(errorMessage);
                    JOptionPane.showMessageDialog(null, "\"" + inputArea.getText() + "\" is not correct input","Error",JOptionPane.ERROR_MESSAGE);
                }
                inputArea.requestFocus();
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screenArea.setText("");
                inputArea.setText("");

            }
        });

        fuctionList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {

                    MathFunction wartosc;
                    wartosc = (MathFunction) fuctionList.getSelectedValue();
                    //JOptionPane.showMessageDialog(null, wartosc.getFunctionCode());
                    if ((wartosc.getFunctionCode()).equals("111")) {
                        inputArea.setText("" + result);
                    } else {
                        inputArea.setText(inputArea.getText() + wartosc.getFunctionCode());
                    }
                    inputArea.requestFocus();
                    if (inputArea.getText().endsWith(")")) {
                        inputArea.setCaretPosition(inputArea.getText().length() - 1);
                    }
                }
            }
        });

    }

    public void setFunctionList() {

        DefaultListModel<MathFunction> listModel = new DefaultListModel<>();
        MathFunction sin = new MathFunction("Sinus", "sin()");
        MathFunction cos = new MathFunction("Cosinus", "cos()");
        MathFunction tg = new MathFunction("Tangens", "tg()");
        MathFunction ctg = new MathFunction("Cotangens", "ctg()");
        MathFunction sqr = new MathFunction("Square root", "sqrt()");
        MathFunction ln = new MathFunction("Natural logarithm", "ln()");
        MathFunction pi = new MathFunction("π (number pi)", "pi");
        MathFunction e = new MathFunction("e (number e)", "e");
        MathFunction gold_ratio = new MathFunction("Golden ratio", "phi");
        MathFunction addit = new MathFunction("+", "+");
        MathFunction substr = new MathFunction("-", "-");
        MathFunction multipl = new MathFunction("*", "*");
        MathFunction divis = new MathFunction("/", "/");
        MathFunction last_result = new MathFunction("Last result", "111");

        listModel.addElement(addit);
        listModel.addElement(substr);
        listModel.addElement(multipl);
        listModel.addElement(divis);
        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tg);
        listModel.addElement(ctg);
        listModel.addElement(ln);
        listModel.addElement(sqr);
        listModel.addElement(pi);
        listModel.addElement(e);
        listModel.addElement(gold_ratio);
        listModel.addElement(last_result);

        fuctionList.setModel(listModel);
        fuctionList.setCellRenderer(new MathFunctionsRenderer());
    }
}
