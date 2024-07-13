import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener, KeyListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton,subButton,mulButton ,divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("SansSerif", Font.BOLD, 20);

    double num1=0,num2=0,result=0;
    char operator;

    Calculator(){

        // Change look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,480);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.addKeyListener(this);
        frame.add(textfield);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("AC");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for(int i =0;i<9;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i =0;i<10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // Set preferred size for buttons
        clrButton.setPreferredSize(new Dimension(100, 50));
        delButton.setPreferredSize(new Dimension(100, 50));
        negButton.setPreferredSize(new Dimension(100, 50));

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(5,4,10,10));

        // First row

        panel.add(clrButton);
        panel.add(delButton);
        panel.add(negButton);
        panel.add(divButton);
        // Second row

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        // Third row

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        // Fourth row

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        // Fifth row
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);


        frame.add(panel);
        frame.add(textfield);
        frame.setVisible(true);

        textfield.requestFocusInWindow();

    }
    public static void main(String[] args){

        Calculator calc = new Calculator();

    }
    @Override
    public void actionPerformed(ActionEvent e){
        textfield.requestFocusInWindow();
        // TODO Auto-generated method stub

        for(int i=0;i<10;i++){
            if(e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if(e.getSource()==addButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = '+';
            textfield.setText("");
        }
        if(e.getSource()==subButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = '-';
            textfield.setText("");
        }
        if(e.getSource()==mulButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = '*';
            textfield.setText("");
        }
        if(e.getSource()==divButton){
            num1 = Double.parseDouble((textfield.getText()));
            operator = '/';
            textfield.setText("");
        }
        if(e.getSource()==equButton){
            num2=Double.parseDouble(textfield.getText());

            switch(operator){
                case'+':
                    result=num1+num2;
                    break;
                case'-':
                    result=num1-num2;
                    break;
                case'*':
                    result=num1*num2;
                    break;
                case'/':
                    result=num1/num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==clrButton){
            textfield.setText("");
        }
        if(e.getSource()==delButton){
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0;i<string.length()-1;i++){
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }
        if(e.getSource()==negButton){
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        System.out.println("Key Code: " + keyCode + ", Key Char: " + keyChar);

        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            // Subtracting KeyEvent.VK_NUMPAD0 from the keyCode to get the actual numeric value
            int number = keyCode - KeyEvent.VK_NUMPAD0;
            textfield.setText(textfield.getText() + number);
        }

        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9) {
            textfield.setText(textfield.getText() + (keyCode - KeyEvent.VK_0));
        } else if (keyCode == KeyEvent.VK_DECIMAL || keyCode == KeyEvent.VK_PERIOD) {
            textfield.setText(textfield.getText() + ".");
        } else if (keyCode == KeyEvent.VK_ADD || (e.isShiftDown() && keyCode == KeyEvent.VK_EQUALS)) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        } else if (keyCode == KeyEvent.VK_SUBTRACT || keyCode == KeyEvent.VK_MINUS) {
            if (textfield.getText().isEmpty()) {
                textfield.setText("-");
            } else {
                num1 = Double.parseDouble(textfield.getText());
                operator = '-';
                textfield.setText("");
            }
        } else if (keyCode == KeyEvent.VK_MULTIPLY || keyCode == KeyEvent.VK_X) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        } else if (keyCode == KeyEvent.VK_DIVIDE || keyCode == KeyEvent.VK_SLASH) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        } else if (keyCode == KeyEvent.VK_ENTER) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        } else if (keyCode == KeyEvent.VK_DELETE) {
            textfield.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but must be implemented
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but must be implemented
    }
}
