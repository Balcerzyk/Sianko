/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sianko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Mikołaj i Michał
 */
public class MainFrame extends javax.swing.JFrame {
    BigPanel[] panels;
    Account[] accounts;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    /**
     * Creates new form NewJFrame2
     */
    public MainFrame() {
        super("SIANKO"); 
        initComponents();
    }

    private void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setBackground(new java.awt.Color(158, 158, 158));     
        
        java.awt.GridLayout layout = new java.awt.GridLayout(0, 3, 5, 2);
        getContentPane().setLayout(layout);

        panels = new BigPanel[3];
        accounts = new Account[3];
        
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        
        menuBar.setBackground(new java.awt.Color(255, 255, 255));
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBarClicked(evt);
            }
        });

        menu.setText("New Account");
        menuBar.add(menu);

        setJMenuBar(menuBar);
        try {
            File Users = new File("users.txt");
            Scanner in = new Scanner(Users);
            String login = in.nextLine(); 
            String pass  =  in.nextLine();
            int accountsNumber = in.nextInt();
            if(accountsNumber == 0) {
                Account x = new Account(0, false);
                if(x.isCorrect() == true){
                    accounts[0] = x;
                    accountsNumber = 1;
                    PrintWriter writer = new PrintWriter(Users);
                    writer.println(login);
                    writer.println(pass);
                    writer.println(1);
                    writer.close();
                }
            }
            else{
                for(int i=0; i<accountsNumber; i++){
                    accounts[i] = new Account(i+1, true);
                }
            }
            for(int i=0; i<accountsNumber; i++){
                panels[i] = new BigPanel(accounts[i].getName(), accounts[i].getBalance(), i+1, this);
                add(panels[i]);
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        pack();
    }
    private void menuBarClicked(java.awt.event.MouseEvent evt) {   
        for(int i=0; i<3; i++){
            if(accounts[i] == null){
                Account x = new Account(i+1, false);
                if(x.isCorrect() == true){
                    accounts[i] = x;
                    try {
                        File users = new File("users.txt");
                        java.util.List<String> lines = Files.readAllLines(users.toPath());
                        String accountsNumber = lines.get(2);
                        int newNumber = Integer.parseInt(accountsNumber) + 1;
                        lines.set(2, Integer.toString(newNumber));          
                        Files.write(users.toPath(), lines);
                        panels[i] = new BigPanel(accounts[i].getName(), accounts[i].getBalance(), i+1, this);
                        add(panels[i]);
                        revalidate();
                    } 
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    catch (IOException ex) {
                        Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }     
    } 
      
}