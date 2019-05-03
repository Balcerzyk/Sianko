package sianko;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @authors Michał i Mikołaj
 */
public class LoginFrame extends javax.swing.JFrame {
    
    private javax.swing.JLabel Warning;
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel Login;
    private javax.swing.JButton NextButton;
    private javax.swing.JLabel Password;
    private javax.swing.JTextField loginField;
    private javax.swing.JPasswordField passField;
    protected boolean passed;
    protected boolean firstRun;
    public LoginFrame() {
        super("SIANKO");
        initComponents();
        passed = false;
        firstRun = false;
        try{
        File users = new File("users.txt");
        if(users.createNewFile()){ // jesli nie istnieje
            firstRun = true;
            passed = true;
            Warning.setText("First startup. Enter Login datas");
            Warning.setForeground(Color.BLUE);
            Font f = Warning.getFont();
            Warning.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            FileOutputStream oUsers = new FileOutputStream(users, false); 
            oUsers.close();
        }
        }
        catch(IOException fileE){ 
            passed = false;
            super.dispose();
        }
    }

    /**
     *
     * @return true if passed
     */
    public boolean getPassed(){
        return passed;
    }
    public boolean getFirstRun(){
        return firstRun;
    }
    private void initComponents() {

        Login = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        Warning = new javax.swing.JLabel();
        NextButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        loginField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Login.setText("LOGIN");
        
        Warning.setText("Enter Your data to log in!");
            
        Password.setText("PASSWORD");

        NextButton.setText("Next");
        NextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                    NextButtonFunction(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExitButtonFunction(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(passField)
                        .addComponent(loginField)
                        .addComponent(Warning, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(ExitButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NextButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                
                            .addGap(81, 81, 81)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Warning)
                .addComponent(Login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                    .addComponent(ExitButton))
                .addGap(4, 4, 4))
        );

        pack();
        

    }
    private void ExitButtonFunction(java.awt.event.MouseEvent evt) {                                       
        super.dispose();
    } 
    private void NextButtonFunction(java.awt.event.MouseEvent evt){ 
        if(passed == true & (!"".equals(getPassword())) & (!"".equals(getName())) ){
            final String i_name = getName();
            final String i_password = getPassword();
            try {
            File Users = new File("users.txt");
            try (PrintWriter zapis = new PrintWriter(Users)) {
                    zapis.println(i_name);
                    zapis.println(i_password);
                    zapis.println(0);
                    zapis.close();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            super.dispose();
        }
        else if( Authorization() == true)
            super.dispose();
        else{
            Warning.setText("Bad data or any. Try again!");
            Warning.setForeground(Color.RED);
            Font f = Warning.getFont();
            Warning.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            loginField.setText("");
            passField.setText("");
        }
    } 
    @Override
    public String getName() {
        return loginField.getText();
    }
    public String getPassword() {
        String password = "";
        char[] pass = passField.getPassword();
        for(int i=0; i<pass.length; i++) {
            password += pass[i];
        }
        return password;
    }
    private boolean Authorization(){
        try {
            File Users = new File("users.txt");
            Scanner in = new Scanner(Users);
            final String name = in.nextLine();
            final String password = in.nextLine();
            final String i_name = getName();
            final String i_password = getPassword();
            
            if(name.equals(i_name) & password.equals(i_password)){
                passed = true;
                return true;
            }
            else{
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}