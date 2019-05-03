/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sianko;

import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Mikołaj i Michał
 */
public class Account {
     private int balance;
     private String name;
     private javax.swing.JLabel accountName;
     private javax.swing.JLabel nameLabel;
     private javax.swing.JLabel balanceLabel;
     private javax.swing.JTextField nameField;
     private javax.swing.JSpinner balanceField;
     private javax.swing.JButton NextButton;
     private javax.swing.JDialog accountFrame;
     
     private boolean correct;
     
     public Account(int accountNumber, boolean exists){
         balance = 0;
         name = " ";
         correct = false;
         if(exists == false){
             if(accountNumber == 0)
                 initComponents(1);
             else
                 initComponents(accountNumber);
         }
         else{
            try {
                String fileName= Integer.toString(accountNumber) + ".txt";
                File Accounts = new File(fileName);
                Scanner in = new Scanner(Accounts);
                name = in.nextLine();
                balance = Integer.parseInt(in.nextLine());
                correct = true;
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
     }
     
     private void initComponents(int accountNumber) {
        accountFrame = new javax.swing.JDialog(null, "SIANKO", APPLICATION_MODAL );
        
        java.awt.Font font = new java.awt.Font("Arial", 0, 15);
        
        nameField = new javax.swing.JTextField();
        balanceField = new javax.swing.JSpinner();
        
        nameLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        NextButton = new javax.swing.JButton();
        nameLabel.setText("Account Name!");
        nameLabel.setFont(font); 
        balanceLabel.setText("Start Balance !");
        balanceLabel.setFont(font); 
        NextButton.setText("Next");
        NextButton.setFont(font); 
        NextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                    NextButtonFunction(evt, accountFrame, accountNumber);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(accountFrame.getContentPane());
        accountFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(balanceField)
                        .addComponent(nameField)            
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NextButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                
                            .addGap(81, 81, 81)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(balanceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                .addGap(4, 4, 4))
            ));

    accountFrame.pack();
    accountFrame.setLocationRelativeTo(null);
    accountFrame.setVisible(true);  
    accountFrame.setAlwaysOnTop( true );
    }
    private void NextButtonFunction(java.awt.event.MouseEvent evt, javax.swing.JDialog accountFrame, int accountNumber){ 
        name = nameField.getText();
        balance = (int) balanceField.getValue();
        try {
            String fileName= Integer.toString(accountNumber) + ".txt";
            File accountFile = new File(fileName);
            try (PrintWriter zapis = new PrintWriter(accountFile)) {
                    zapis.println(name);
                    zapis.println(balance);
                    zapis.println("0");
                    zapis.close();
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        correct = true;
        accountFrame.dispose();
    }
    
    public int getBalance(){
        return balance;
    }
    
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public javax.swing.JDialog getFrame(){
        return accountFrame;
    }
    public boolean isCorrect(){
        return correct;
    }
    
}
