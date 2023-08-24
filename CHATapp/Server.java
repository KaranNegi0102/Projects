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
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    private JLabel Heading=new JLabel("Friend 1");
    private JTextArea MessageArea=new JTextArea();
    private JTextField MessageInput=new JTextField();
    private Font font=new Font("MONOSPACE",Font.PLAIN,20);


     Server()
    {
        try {
            server=new ServerSocket(7777);
            System.out.println("server is ready to accept the connection");
            System.out.println("waiting..");
            socket=server.accept();
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            createGUI2();
            HandleEvents();
            startReading();
//            startWriting();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createGUI2() {
        this.setTitle("server [End]");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);

        this.getContentPane().setBackground(new Color(205,92,92));
        Heading.setFont(font);
        MessageArea.setFont(font);


        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setBorder((BorderFactory.createEmptyBorder(20,20,20,20)));
//        MessageInput.setHorizontalAlignment(SwingConstants.CENTER);





        JButton send = new JButton("Send");
        send.setBounds(320, 320, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("MONOSPACE", Font.PLAIN, 16));
        this.add(send);




    //messageinput
//        MessageInput.setFont(font);
        MessageInput.setBounds(40, 320, 250, 40);




        MessageArea.setEditable(false);
        this.setLayout(new BorderLayout());
        this.add(Heading,BorderLayout.NORTH);
        this.add(MessageArea,BorderLayout.CENTER);

        JScrollPane jScrollPane=new JScrollPane(MessageArea);
        this.add(jScrollPane,BorderLayout.CENTER);

        this.add(MessageInput, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String contentToSend=MessageInput.getText();
        MessageArea.append("Server : "+contentToSend+"\n");
        out.println(contentToSend);
        out.flush();
        MessageInput.setText("");
        MessageInput.requestFocus();

    }
    private void HandleEvents(){
        MessageInput.addKeyListener(new KeyListener() {
            @Override
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
                    String contentToSend=MessageInput.getText();
                    MessageArea.append("Server : "+contentToSend+"\n");
                    out.println(contentToSend);
                    out.flush();
                    MessageInput.setText("");
                    MessageInput.requestFocus();
                }
            }
        });
    }

    public void startReading() {
        Runnable r1 = () -> {
            System.out.println("reader started");

            try{
                while (true) {
                    String text = br.readLine();
                    if (text.equals("exit"))
                    {
                        System.out.println("client stopped");
                        JOptionPane.showMessageDialog(this,"client terminated");
                        MessageInput.setEnabled(false);
                        socket.close();
                        break;
                    }
//                    System.out.println("client: " + text);
                    MessageArea.append(("client: " + text+"\n"));
                }

            }catch (Exception e) {
//                e.printStackTrace();
                System.out.println("closed ");
            }
        };
        new Thread(r1).start();
    }
    public void startWriting() {
        Runnable r2 = () -> {
            System.out.println("writer started");

            try{
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
                System.out.println("closed connection");
            }
        };
        new Thread(r2).start();
    }
    //main
    public static void main(String[] args) {
        System.out.println("this is server");
        new Server();

    }
}
