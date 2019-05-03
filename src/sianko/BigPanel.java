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

/**
 *
 * @authors Michał i Mikołaj
 */
public class BigPanel extends javax.swing.JPanel{
    
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private TransactionPanel panel3;
    
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
   
    private javax.swing.JButton button;
    private javax.swing.JButton deleteButton;
    
    private javax.swing.GroupLayout balanceLayout;
    private javax.swing.GroupLayout mainLayout;
    private int number;
    
    private String[] operations;
    
    public BigPanel(String name, int money, int i_number, MainFrame frame){
        operations = new String[5];
        for(int i=0; i<5; i++)
        {
            operations[i]="";
        }
        number = i_number;
        setBackground(java.awt.Color.WHITE);
        panel1 = new javax.swing.JPanel();
        
        panel2 = new javax.swing.JPanel();
        panel3 = new TransactionPanel(number);
                   
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        
        button = new javax.swing.JButton();
        button.setText("Add");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                    ButtonFunction(evt, number);
            }
        });
        
        deleteButton = new javax.swing.JButton();
        deleteButton.setText("Delete account");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                    DeleteButtonFunction(evt, number, frame);
            }
        });
        
        java.awt.Font font = new java.awt.Font("Arial", 0, 25);
        
        label1.setFont(font); // NOI18N
        if(name.length()>50) name=cutName(name);
        label1.setText(name);
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
         
        label2.setFont(font); // NOI18N
        label2.setText("Balance:");
        
        if(money == 0) money = 0;
        label3.setFont(font);
        label3.setText(Integer.toString(money) + " zł");
        
        switch(number){
            case 1: 
                panel1.setBackground(new java.awt.Color(187, 222, 251));
                panel2.setBackground(new java.awt.Color(187, 222, 251));
                button.setBackground(new java.awt.Color(187, 222, 251));
                deleteButton.setBackground(new java.awt.Color(187, 222, 251));
                break;
            
            case 2: 
                panel1.setBackground(new java.awt.Color(251, 216, 187));
                panel2.setBackground(new java.awt.Color(251, 216, 187));
                button.setBackground(new java.awt.Color(251, 216, 187));
                deleteButton.setBackground(new java.awt.Color(251, 216, 187));
                break;
            
            case 3: 
                panel1.setBackground(new java.awt.Color(216, 187, 251));
                panel2.setBackground(new java.awt.Color(216, 187, 251));
                button.setBackground(new java.awt.Color(216, 187, 251));
                deleteButton.setBackground(new java.awt.Color(216, 187, 251));
                break;
        }
        
        // Layout of Balance Panel
        balanceLayout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(balanceLayout);
        balanceLayout.setHorizontalGroup(
            balanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(balanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
        balanceLayout.setVerticalGroup(
            balanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(balanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(balanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        //  Layout of Account name Panel
        panel1.setLayout(new java.awt.GridLayout(1, 0));
        panel1.add(label1);
        
        // Main Layout of Panel
        
        mainLayout = new javax.swing.GroupLayout(this);
        this.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button, 120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton, 120, 120, 120))
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                 // acount name   
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                 // balance
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                 // last transactions
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                 // add button
                .addComponent(button, 80, 80, 80)
                .addGap(80, 80, 80)
                .addComponent(deleteButton, 80, 80, 80)
                .addContainerGap())
        );

    }
    private void ButtonFunction(java.awt.event.MouseEvent evt, int accountNumber){
        Operation x = new Operation(number);
        if(x.isCorrect() == true){
            panel3.updateTransactions(x.getOperation(), accountNumber); 
            int newMoney = Integer.parseInt(label(label3.getText())) + x.getMoney();
            label3.setText(Integer.toString(newMoney) + " zł");
    
        }
    }
    
     private void DeleteButtonFunction(java.awt.event.MouseEvent evt, int accountNumber, MainFrame frame){
        try {
            File usersFile = new File("users.txt");
            java.util.List<String> lines = Files.readAllLines(usersFile.toPath());
            int tempNumber = Integer.parseInt(lines.get(2)) - 1;
            lines.set(2, Integer.toString(tempNumber));          
            Files.write(usersFile.toPath(), lines);
            File file = new File(Integer.toString(accountNumber) + ".txt");
            Files.delete(file.toPath());
            number-=1;
            frame.remove(frame.panels[accountNumber-1]);
            frame.panels[accountNumber-1] = null;
            frame.accounts[accountNumber-1] = null;
            
            if(accountNumber < tempNumber +1){
                for(int i=accountNumber; i<3; i++){
                       File tempFile = new File(Integer.toString(i+1) + ".txt");
                       File tempFile2 = new File(Integer.toString(i) + ".txt");
                       tempFile.renameTo(tempFile2);
                       frame.panels[i].number = i;
                }
            }
            
            frame.revalidate();
            frame.repaint();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         
    private String label(String label){             // usuwa " zł"
        char[] labelChar=label.toCharArray();
        char[] x=new char[label.length()-3];
        for(int i=0; i<15; i++){
            if(labelChar[i]==' ') break;
            x[i]=labelChar[i];
        }
        
        String lel=new String(x);
        
        return lel;
    }
      
    private String cutName(String name){
        char[] nameChar=name.toCharArray();
        char[] finalName=new char[50];
        
        for(int i=0; i<50; i++){
            finalName[i]=nameChar[i];
        }
        
        name=new String(finalName);
        return name;
    }
        
}

