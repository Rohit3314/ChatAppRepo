package com.Myapp1;
import java.net.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Client extends JFrame{
	
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	
	// components for GUI
	
	private JLabel heading = new JLabel("Client Area"); 
	private JTextArea msgarea = new JTextArea();
	private JTextField msginputarea = new JTextField();
	private Font font = new Font("Arial", Font.PLAIN,20);

	// constructor for Client
	
	public Client()
	{
		
		try
		{
			
			System.out.println("Message to server");
			socket = new Socket("127.0.0.1",7777);
			System.out.println("Connection Succeeded");
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream());
	        
	        createGUI();
	        handleEvents();
	        Reading();
	        
		} catch(Exception e)
		
		{
			
		}		
	}
	
	private void createGUI()
	{
	
		  this.setTitle("Client Messenger");
		  this.setSize(600,600);
		  this.setLocationRelativeTo(null);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  heading.setFont(font);
          msgarea.setFont(font);
          msginputarea.setFont(font);

          heading.setHorizontalAlignment(SwingConstants.CENTER);
          heading.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
          
          this.setLayout(new BorderLayout());          
          this.add(heading, BorderLayout.NORTH);
          this.add(msgarea, BorderLayout.CENTER);
          this.add(msginputarea, BorderLayout.SOUTH);
          
		  this.setVisible(true);
		
	}

	private void handleEvents()
	{
		
		msginputarea.addKeyListener(new KeyListener()
				{

					@Override
					public void keyTyped(KeyEvent e) {
						
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
					
						if(e.getKeyCode() == 10)
						{
						String contents = msginputarea.getText();	
						msgarea.append("Me : "+contents+"\n");
						out.flush();
						msginputarea.setText("");
						msginputarea.requestFocus();
						}
						
					}
			
			
				});
		
	}
	
	private void Reading()
	{
		
		Runnable r1 = () -> {
			
			//System.out.println("Reader started");
			
			try
			{
				while(true)
					
				{
					String msg = br.readLine();
					if(msg.equals("exit"))
					{
						
						JOptionPane.showMessageDialog(this,"chat finish");
						msginputarea.setEnabled(false);						
						socket.close();
						break;
						
					}
					
					msgarea.append("Server : "+msg +"\n");
				}
				
			}catch(Exception e)
			{
				
			}
		};
	}
	
}	
	
