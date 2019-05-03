/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sianko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

/**
 *
 * @author Mikołaj
 */
public class TransactionPanel extends javax.swing.JPanel{
    private javax.swing.JLabel[] labels;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    
    private javax.swing.GroupLayout mainLayout;
    
    
    public TransactionPanel(int accountNumber){ 
        java.awt.Font font = new java.awt.Font("Arial", 0, 25);
         
        labels = new javax.swing.JLabel[5];
        for(int i= 0; i<5; i++)
            labels[i] = new javax.swing.JLabel();
        
        for(int i=0; i<5; i++){
            labels[i].setFont(font);
            labels[i].setText("");
        }
         
        try {
            String fileName= Integer.toString(accountNumber) + ".txt";
            File accountFile = new File(fileName);
            java.util.List<String> lines = Files.readAllLines(accountFile.toPath());
            int number = Integer.parseInt(lines.get(2));
            for(int i=4, j=number+2; (i>=0 && i>4-number); i--, j--){
                labels[i].setText(lines.get(j));           
            }
            
            changeColor(accountNumber);
           
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch(accountNumber){
            case 1: setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(187, 222, 251)));                
                break;
            
            case 2: setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(251, 216, 187)));          
                break;
            
            case 3: setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 187, 251)));                
                  break;
        }        

        
        mainLayout = new javax.swing.GroupLayout(this);
        setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labels[0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labels[1], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labels[2], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labels[3], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labels[4], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(labels[4], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labels[3], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labels[2], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labels[1], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labels[0], javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }
    public void updateTransactions(String newOperation, int accountNumber){
        for(int i=0; i<4; i++){
            labels[i].setText(labels[i+1].getText());
        }
            
        labels[4].setText(newOperation);
        changeColor(accountNumber);
        
    }
    
    public void changeColor(int accountNumber){
        try {
            String fileName= Integer.toString(accountNumber) + ".txt";
            File accountFile = new File(fileName);
            java.util.List<String> lines = Files.readAllLines(accountFile.toPath());
            int number = Integer.parseInt(lines.get(2));
            
            String x;
            char[] y ;
            switch(number){
                case 0: break;
                case 1:
                    x=lines.get(3);
                    y= x.toCharArray();
                    if(y[0]=='-') labels[4].setForeground(new java.awt.Color(178, 10, 19));
                    else labels[4].setForeground(new java.awt.Color(16, 154, 9));
                    break;
                case 2:
                    x=lines.get(3);
                    y = x.toCharArray();
                    if(y[0]=='-') labels[3].setForeground(new java.awt.Color(178, 10, 19));
                    else labels[3].setForeground(new java.awt.Color(16, 154, 9));
                    x=lines.get(4);
                    y = x.toCharArray();
                    if(y[0]=='-') labels[4].setForeground(new java.awt.Color(178, 10, 19));
                    else labels[4].setForeground(new java.awt.Color(16, 154, 9));
                    break;
                default:
                    for(int i=number, j=0; i>number-5; i--, j++){
                        String a=lines.get(number-2+j);
                        if((number-2+j)<3) continue;
                        char[] b = a.toCharArray();
                        if(b[0]=='-') labels[j].setForeground(new java.awt.Color(178, 10, 19));
                        else labels[j].setForeground(new java.awt.Color(16, 154, 9));

                    }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}






