package view;

import java.awt.Image;
import javax.swing.ImageIcon;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author USER
 */
public class frmDisplay extends javax.swing.JFrame {

    /**
     * Creates new form frmDisplay
     */
    boolean appendable=true;
    char preOpp;
    double preNum;
    double tmp = 0;
    char opp = '+';

    public frmDisplay() {
        initComponents();
        Image img = new ImageIcon("icon.png").getImage();
        this.setIconImage(img);
    }

    private double calculate(double d1, double d2, char o) {
        double result = 0;
        switch (o) {
            case '+':
                result = d1 + d2;
                break;
            case '-':
                result = d1 - d2;
                break;
            case '*':
                result = d1 * d2;
                break;
            case '/':
                result = d1 / d2;
                break;
            case '=':
                result = d2;
                break;
        }
        return result;
    }
    private double calculateUnary(double dd,String oo){
        switch(oo)
        {
            case "square":
                return dd*dd;
            case "sqrt":
                return Math.sqrt(dd);
            case "%":
                return dd/100;
            case "1/x":
                return 1/dd;
        }
        return 0;
    }
    private void actOnNumber(char num) {
        //reset text field
        if (!appendable || txtDisplay.getText().equals("0") && num != '.') {
            txtDisplay.setText(num + "");
        } //append text field
        else {
            txtDisplay.setText(txtDisplay.getText() + num);
        }
        appendable = true;
        txtDisplay.requestFocusInWindow();
    }
    private void actOnOpp(char o) {
        String screen = txtDisplay.getText();
        //only perform when screen's contain is not 'error'
        if(!screen.contains("Error"))
        {            
            if(appendable)
            {                
                double d2 = Double.parseDouble(screen);
                //Exception divided by zero
                if(d2 == 0 && opp == '/')
                {
                    txtDisplay.setText("Error: divided by zero");
                    opp  = '=';
                    tmp = 0;
                }
                    
                else
                {
                    double result = calculate(tmp, d2, opp);
                    txtDisplay.setText(round(result));
                    tmp = result;                  
                    opp = o;
                }   
            }
            //override previous operator
            else
            {
                opp = o;                          
            }
            
        }
        appendable = false;
        txtDisplay.requestFocusInWindow();

    }
    private void actOnUnary(String oo)
    {
        String screen = txtDisplay.getText();
        if(!screen.contains("Error"))
        {                        
            double display = Double.parseDouble(screen);
            String result;
            if(display < 0 && oo.equals("sqrt"))
                result = "Error: Square root of negative number";
            else if(display == 0 && oo.equals("1/x"))
                result = "Error: Divided by zero";
            else
            {
                //if the previous operator is 'equal', update the display number into 'tmp'
                if( opp == '=')
                    tmp = calculateUnary(display, oo);
                result = round(calculateUnary(display, oo));
            }            
            txtDisplay.setText(result);
        }
        appendable = false;
        txtDisplay.requestFocusInWindow();
    }
    private void actOnEqual()
    {
        //if double button equal were typed, continue to calculate with previous
        //operand and previous operater
        if (opp == '=' && !appendable) 
        {
            double result = calculate(tmp, preNum, preOpp);
            tmp = result;
            txtDisplay.setText(round(result));
        }
        //store previous operator and previous operand in case of reuse
        else
        {
            preNum = Double.parseDouble(txtDisplay.getText());
            preOpp = opp;
            actOnOpp('=');
        }
    }

    private String round(double input) {
        String result;
        //round number up to 9 digit after the dot
        double roundNum = Math.round(input * 1000_000_000) / 1000_000_000.0;
        result = roundNum + "";

        //see if the result is an integer
        if (result.endsWith(".0")) {
            int index = result.indexOf('.');
            result = result.substring(0, index);
        }
        return result;
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDisplay = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnSquare = new javax.swing.JButton();
        btnSqrt = new javax.swing.JButton();
        btnPercent = new javax.swing.JButton();
        btnReverse = new javax.swing.JButton();
        btnClearScreen = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnDivide = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnMultiply = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnSubtract = new javax.swing.JButton();
        btnNeg = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnEqual = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");

        txtDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplay.setText("0");
        txtDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDisplayKeyTyped(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 4, 10, 10));

        btnSquare.setText("<html><p>x<sup>2</sup></p>");
        btnSquare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSquareActionPerformed(evt);
            }
        });
        jPanel1.add(btnSquare);

        btnSqrt.setText("<html>&radic;");
        btnSqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqrtActionPerformed(evt);
            }
        });
        jPanel1.add(btnSqrt);

        btnPercent.setText("%");
        btnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPercentActionPerformed(evt);
            }
        });
        jPanel1.add(btnPercent);

        btnReverse.setText("1/x");
        btnReverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReverseActionPerformed(evt);
            }
        });
        jPanel1.add(btnReverse);

        btnClearScreen.setText("CE");
        btnClearScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearScreenActionPerformed(evt);
            }
        });
        jPanel1.add(btnClearScreen);

        btnClearAll.setText("C");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });
        jPanel1.add(btnClearAll);

        btnDel.setText("<html> &#8678;");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        jPanel1.add(btnDel);

        btnDivide.setText("/");
        btnDivide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivideActionPerformed(evt);
            }
        });
        jPanel1.add(btnDivide);

        btn7.setBackground(new java.awt.Color(250, 250, 250));
        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn7);

        btn8.setBackground(new java.awt.Color(250, 250, 250));
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(btn8);

        btn9.setBackground(new java.awt.Color(250, 250, 250));
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9);

        btnMultiply.setText("X");
        btnMultiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplyActionPerformed(evt);
            }
        });
        jPanel1.add(btnMultiply);

        btn4.setBackground(new java.awt.Color(250, 250, 250));
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(btn4);

        btn5.setBackground(new java.awt.Color(250, 250, 250));
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5);

        btn6.setBackground(new java.awt.Color(250, 250, 250));
        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(btn6);

        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });
        jPanel1.add(btnPlus);

        btn1.setBackground(new java.awt.Color(250, 250, 250));
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1);

        btn2.setBackground(new java.awt.Color(250, 250, 250));
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(btn2);

        btn3.setBackground(new java.awt.Color(250, 250, 250));
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(btn3);

        btnSubtract.setText("-");
        btnSubtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubtractActionPerformed(evt);
            }
        });
        jPanel1.add(btnSubtract);

        btnNeg.setText("+/-");
        btnNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegActionPerformed(evt);
            }
        });
        jPanel1.add(btnNeg);

        btn0.setBackground(new java.awt.Color(250, 250, 250));
        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        jPanel1.add(btn0);

        btnDot.setText(".");
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        jPanel1.add(btnDot);

        btnEqual.setText("=");
        btnEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualActionPerformed(evt);
            }
        });
        jPanel1.add(btnEqual);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDisplay)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubtractActionPerformed
        // TODO add your handling code here:
        actOnOpp('-');
    }//GEN-LAST:event_btnSubtractActionPerformed

    private void txtDisplayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisplayKeyTyped
        // TODO add your handling code here:        
        char c = evt.getKeyChar();
        String screen = txtDisplay.getText();
        //processing operator key
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            actOnOpp(c);
        }
        if (c == '='||c == '\n') {
            actOnEqual();
        }
        boolean valid = (c >= '0' && c <= '9' || c == '.' && !screen.contains("."));
        //processing numberic key
        if (valid)
            actOnNumber(c);
        //ignore others key
        evt.consume();
    }//GEN-LAST:event_txtDisplayKeyTyped

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        actOnNumber('1');
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        actOnNumber('2');
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        actOnNumber('3');
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        actOnNumber('4');
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        actOnNumber('5');
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        actOnNumber('6');
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
        actOnNumber('7');
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        actOnNumber('8');
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
        actOnNumber('9');
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        // TODO add your handling code here:
        actOnNumber('0');
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        // TODO add your handling code here:
        if(!txtDisplay.getText().contains("."))
            actOnNumber('.');
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        // TODO add your handling code here:
        actOnOpp('+');
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMultiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplyActionPerformed
        // TODO add your handling code here:
        actOnOpp('*');
    }//GEN-LAST:event_btnMultiplyActionPerformed

    private void btnDivideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivideActionPerformed
        // TODO add your handling code here:
        actOnOpp('/');
    }//GEN-LAST:event_btnDivideActionPerformed
    
    private void btnEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualActionPerformed
        // TODO add your handling code here:
        actOnEqual();
        txtDisplay.requestFocusInWindow();
    }//GEN-LAST:event_btnEqualActionPerformed

    private void btnNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegActionPerformed
        // TODO add your handling code here:
        String display = txtDisplay.getText();
        if (display.charAt(0) == '-') {
            display = display.substring(1);
        } else {
            display = '-' + display;
        }
        txtDisplay.setText(display);
        txtDisplay.requestFocusInWindow();
    }//GEN-LAST:event_btnNegActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        if(appendable)
        {
            String display = txtDisplay.getText();
            display = display.substring(0, display.length()-1);
            txtDisplay.setText(display);
        }
        txtDisplay.requestFocusInWindow();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        // TODO add your handling code here:
        txtDisplay.setText("0");
        txtDisplay.requestFocusInWindow();
        opp = '=';
        tmp = 0;
    }//GEN-LAST:event_btnClearAllActionPerformed

    private void btnClearScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearScreenActionPerformed
        // TODO add your handling code here:   
        if(appendable)
            txtDisplay.setText("0");
        txtDisplay.requestFocusInWindow();
    }//GEN-LAST:event_btnClearScreenActionPerformed
    
    private void btnSquareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSquareActionPerformed
        // TODO add your handling code here:       
        actOnUnary("square");
    }//GEN-LAST:event_btnSquareActionPerformed

    private void btnSqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqrtActionPerformed
        // TODO add your handling code here:
        actOnUnary("sqrt");
    }//GEN-LAST:event_btnSqrtActionPerformed

    private void btnPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPercentActionPerformed
        // TODO add your handling code here:
        actOnUnary("%");
    }//GEN-LAST:event_btnPercentActionPerformed

    private void btnReverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReverseActionPerformed
        // TODO add your handling code here:
        actOnUnary("1/x");
            
    }//GEN-LAST:event_btnReverseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnClearScreen;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDivide;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnEqual;
    private javax.swing.JButton btnMultiply;
    private javax.swing.JButton btnNeg;
    private javax.swing.JButton btnPercent;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnReverse;
    private javax.swing.JButton btnSqrt;
    private javax.swing.JButton btnSquare;
    private javax.swing.JButton btnSubtract;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDisplay;
    // End of variables declaration//GEN-END:variables
}
