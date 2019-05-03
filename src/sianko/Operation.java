/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sianko;
import java.awt.Dimension;
/**
 *
 * @authors Mikołaj i Michał
 */

import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Operation {
     private int money;
     private String name;
     private char[] date;
     private javax.swing.JLabel Warning;
     private javax.swing.JLabel moneyLabel;
     private javax.swing.JLabel dateLabel;
     private javax.swing.JTextField dateField;
     private javax.swing.JSpinner moneyField;
     private javax.swing.JButton NextButton;
     private javax.swing.JDialog operationFrame;
     
     private boolean correct;
     public Operation(int accountNumber){
         money = 0;
         name = "";
         date = null;
         correct = false;
         initComponents(accountNumber);
         
     }
     
     private void initComponents(int accountNumber) {
        operationFrame = new javax.swing.JDialog(null, "SIANKO", APPLICATION_MODAL );
        operationFrame.setPreferredSize(new Dimension(310, 250));
        java.awt.Font font = new java.awt.Font("Arial", 0, 15);
        
        dateField = new javax.swing.JTextField();
        moneyField = new javax.swing.JSpinner();
        
        moneyLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        NextButton = new javax.swing.JButton();

        Warning = new javax.swing.JLabel();
         
        Warning.setText("Error. Try again!");
        Warning.setForeground(java.awt.Color.RED);
        Warning.setFont(font);
        Warning.setVisible(false);
         
        
        moneyLabel.setText("Enter money:");
        moneyLabel.setFont(font); 
        dateLabel.setText("Write date. dd-mm-yyyy:");
        dateLabel.setFont(font); 
        NextButton.setText("Save");
        NextButton.setFont(font); 
        NextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                NextButtonFunction(evt, operationFrame, accountNumber);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(operationFrame.getContentPane());
        operationFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Warning, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(moneyField)       
                        .addComponent(dateField) 
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NextButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(moneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, javax.swing.GroupLayout.DEFAULT_SIZE))
                            .addGap(81, 81, 81)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(Warning)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(moneyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moneyField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                .addGap(4, 4, 4))
            ));

    operationFrame.pack();
    operationFrame.setLocationRelativeTo(null);
    operationFrame.setVisible(true);  
    operationFrame.setAlwaysOnTop( true );
    }
    private void NextButtonFunction(java.awt.event.MouseEvent evt, javax.swing.JDialog operationFrame, int accountNumber){ 
        money = (int) moneyField.getValue();
        date = dateField.getText().toCharArray();
        
        if(!dateCheck(date)){
            Warning.setVisible(true);
            return;
        }
        try {
            String fileName= Integer.toString(accountNumber) + ".txt";
            File accountFile = new File(fileName);
            java.util.List<String> lines = Files.readAllLines(accountFile.toPath());
            String number = lines.get(2);
            int newNumber = Integer.parseInt(number) + 1;
            lines.set(2, Integer.toString(newNumber));
            String balance = lines.get(1);
            int newBalance = Integer.parseInt(balance) + money;
            lines.set(1, Integer.toString(newBalance));
            String transaction = Integer.toString(money)+ " zł" + numberSpaces(Integer.toString(money)) + String.valueOf(date);
            lines.add(transaction);            
            Files.write(accountFile.toPath(), lines);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        correct = true;
        operationFrame.dispose();
    }
    
    public String numberSpaces(String moneyString){
        String spaces="";
        int x;
        char[] tab=moneyString.toCharArray();
        if(tab[0]=='-') x=63-2*moneyString.length() +1;
        else x=63-2*moneyString.length();
        for (int i=0; i<x; i++){
            spaces=spaces+" ";
        }
       
        return spaces;
    }
    
    private boolean dateCheck(char[] date){
        String dateString=new String(date);
        if(dateString.length()!=10) return false;
        char[] day=new char[2];
        day[0]=date[0]; day[1]=date[1];
        char[] month=new char[2];
        month[0]=date[3]; month[1]=date[4];
        char[] year=new char[4];
        year[0]=date[6]; year[1]=date[7]; year[2]=date[8]; year[3]=date[9];
        int dayInt, monthInt, yearInt;
        String yearString = new String(year);
        String monthString = new String(month);
        String dayString = new String(day);
        
        if(date[2]!='-' || date[5]!='-' || yearString.length()!=4 || dayString.length()!=2 || monthString.length()!=2) return false;
        else{
            if(day[0]<48 || day[0]>57 || day[1]<48 || day[1]>57) return false;
            if(month[0]<48 || month[0]>57 || month[1]<48 || month[1]>57) return false;
            dayInt=Integer.parseInt(String.valueOf(day));
            monthInt=Integer.parseInt(String.valueOf(month));
            if(days(dayInt, monthInt)==false) return false;
        }
        return true;
    }
    
    private boolean days(int day, int month){
        switch(month)
        {
            case 1: 
                if(day>0 && day<32) return true;
                else return false;   
            case 2: 
                if(day>0 && day<30) return true;
                else return false; 
            case 3: 
                if(day>0 && day<32) return true;
                else return false; 
            case 4: 
                if(day>0 && day<31) return true;
                else return false; 
            case 5: 
                if(day>0 && day<32) return true;
                else return false; 
            case 6: 
                if(day>0 && day<31) return true;
                else return false; 
            case 7: 
                if(day>0 && day<32) return true;
                else return false; 
            case 8: 
                if(day>0 && day<32) return true;
                else return false; 
            case 9: 
                if(day>0 && day<31) return true;
                else return false; 
            case 10: 
                if(day>0 && day<32) return true;
                else return false; 
            case 11: 
                if(day>0 && day<31) return true;
                else return false; 
            case 12: 
                if(day>0 && day<32) return true;
                else return false; 
            default: return false;
        }
    }
    
    public int getMoney(){
        return money;
    }
    
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public javax.swing.JDialog getFrame(){
        return operationFrame;
    }
     
    public String getOperation(){
        return Integer.toString(money) + " zł"+ numberSpaces(Integer.toString(money)) +  String.valueOf(date);
    }
    
    public boolean isCorrect(){
        return correct;
    }
}
