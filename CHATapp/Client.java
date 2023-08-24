package Projects.CHATapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    private JLabel heading=new JLabel("Friend 2");
    private JTextArea messageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN,20);

    public Client(){
        try{
            System.out.println("sending request to server");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("connection done.");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
              createGUI();
              newhandleEvents();
            startReading();
//            startWriting();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createGUI(){
        this.setTitle("client Message[END]");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);//center pe kardega window ko
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setBackground(new Color(0,255,255));
//        this.setVisible(true);
        //coding for the component;
        heading.setFont(new Font("SAN_SERIF",Font.PLAIN,25));
        messageArea.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        messageInput.setFont(font);

        heading.setIcon(new ImageIcon("phone.png"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder((BorderFactory.createEmptyBorder(20,20,20,20)));


        messageInput.setHorizontalAlignment(SwingConstants.CENTER);//msssg fromm right
        messageInput.setBounds(5, 320, 310, 40);
        messageInput.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));



        JButton send = new JButton("Send");
        send.setBounds(320, 320, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("MONOSPACE", Font.PLAIN, 16));
        this.add(send);




//        messageArea.setEditable(false);
//        layout
        this.setLayout(new BorderLayout());
        //component
        this.add(heading,BorderLayout.NORTH);
        this.add(messageArea,BorderLayout.EAST);

        JScrollPane jScrollPane=new JScrollPane(messageArea);
        this.add(jScrollPane,BorderLayout.CENTER);

        this.add(messageInput, BorderLayout.SOUTH);
        this.setVisible(true);

    }


    public void actionPerformed(ActionEvent ae){
        String contentToSend=messageInput.getText();
        messageArea.append("Client: "+contentToSend+"\n");
        out.println(contentToSend);
        out.flush();
        messageInput.setText("");
        messageInput.requestFocus();
    }


    private void newhandleEvents(){
        messageInput.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("key released " + e.getKeyCode());
                if(e.getKeyCode()==10){
//                    System.out.println("you have pressed enter button");
                    String contentToSend=messageInput.getText();
                    messageArea.append("Me : "+contentToSend+"\n");
                    out.println(contentToSend);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }
        });
    }
    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started");
            try {
            while (true) {
                    String text = br.readLine();
                    if (text.equals("exit"))
                    {
                        System.out.println(" server stopped");
                        JOptionPane.showMessageDialog(this,"Server Terminated");
                        messageInput.setEnabled(false);
                        socket.close();
                        break;
                    }
//                    System.out.println("server: " + text);
                    messageArea.append("server: " + text+"\n");
                }
            }catch (Exception e) {
//                e.printStackTrace();
                System.out.println("connection closed see you soon");
            }
        };
//        Thread t1=new Thread(r1);
//        t1.start();
        new Thread(r1).start();
    }
    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("writer started");

            try {
            while(true && !socket.isClosed()){
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                    if(content.equals("exit")){
                    socket.close();
                    break;
                    }
                }

            }catch (Exception e) {
//                e.printStackTrace();
                System.out.println("connection closed seeya");
            }
        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("this is client");
        new Client();
    }


}