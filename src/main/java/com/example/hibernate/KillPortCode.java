package com.example.hibernate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class KillPortCode {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame f=new JFrame("PID kill made by sumon & arif");
        //first textfiled and button

        final JTextField port8080=new JTextField();
        JButton button8080=new JButton("Kill 8080");
        port8080.setBounds(20,20, 150,50);
        button8080.setBounds(20,80, 150,50);
        try {
            String pid8080=SearchPid09("8080" );
            port8080.setText(pid8080);

        if (pid8080!=null){
            //PID kill for detault 8080 port
            button8080.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String x=port8080.getText();
                    System.out.println("this is the test read from search  "+x);
                    killingpid(x);
                    port8080.setText("Successfully Killed");
                    System.out.println("showing");
                    if (pid8080.equals("Successfully Killed"))
                    {
                        port8080.setText("PID Already killed");
                    }


                }
            });
        }
        else port8080.setText("PID Already killed");
        }
        catch (Exception e)
        {
            e.getMessage();
        }



        //searching port

        final JTextField search=new JTextField();
        JButton searchbutton=new JButton("Search Port");
        search.setBounds(220,20, 150,50);
        searchbutton.setBounds(220,80, 150,50);

        //another text field and button no 3

        final JTextField PID=new JTextField();
        JButton KillPID=new JButton("Kill Port");
        PID.setBounds(400,20, 150,50);
        KillPID.setBounds(400,80, 150,50);

        //PID search
        searchbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String x=search.getText();
                System.out.println("this is the test read from search  "+x);
                String pidfound=SearchPid09(x);
                PID.setText(pidfound);
                KillPID.setText("Kill Port: "+x);


            }
        });

        //kill PID after searching
        KillPID.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                PID.getText();
                System.out.println("pid found is "+PID.getText());
                killingpid(PID.getText());
                PID.setText("Successfully killed PID");



            }
        });


        f.add(port8080);
        f.add(button8080);
        f.add(search);
        f.add(searchbutton);
        f.add(PID);
        f.add(KillPID);
        f.setSize(650,350);
        f.setLayout(null);
        f.setVisible(true);

    }

    public static void killingpid(String line)

    {

        try {
            Process process1 = Runtime.getRuntime().exec("taskkill /PID "+line+" /f");
            System.out.println("PID killed");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //searching PID using port search

    public static String SearchPid09(String portNo){

        // Build the command to query the system for the process listening on the port
        String[] command = {"cmd.exe", "/c", "netstat -aon | findstr :" + portNo};

        // Execute the command and capture the output
        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = null;
        try {
            process = pb.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line=null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        line = line.substring(line.lastIndexOf(' ') + 1);

        // Output the PID to the console
        System.out.println("PID: " + line);

    return  line;

    }





}

	
