/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mailapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MailApp extends JFrame {
    Container cp;
    JPanel p1;

    JLabel mail_label;//메일 
    JTextField mail_text;

    JLabel sub_label; //제목 
    JTextField sub_text;

    JLabel msg_label; //메세지 
    JTextArea msg_text;
    JScrollPane scrollPane;

    JButton send_button;

    public static void main(String[] args) {
        MailApp app = new MailApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(500, 300);
        app.setVisible(true);
    }

    public MailApp() {
        super("202305127 junhyung_kim");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        p1 = new JPanel(new GridLayout(4, 2, 10, 10));
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mail_label = new JLabel("Your email:");
        mail_text = new JTextField();

        sub_label = new JLabel("Subject:"); //메일 제목 입력칸 
        sub_text = new JTextField(); //메일 제목 텍스트 

        msg_label = new JLabel("Message:");
        msg_text = new JTextArea(5, 20); //메세지 본문칸 세로5, 가로 20
        msg_text.setLineWrap(true);
        msg_text.setWrapStyleWord(true);
        scrollPane = new JScrollPane(msg_text);

        send_button = new JButton("Send"); //전송버튼 
        send_button.addActionListener(new ButtonHandler());

        p1.add(mail_label);
        p1.add(mail_text);
        p1.add(sub_label);
        p1.add(sub_text);
        p1.add(msg_label);
        p1.add(scrollPane);
        p1.add(new JLabel()); // filler
        p1.add(send_button);

        cp.add(p1, BorderLayout.CENTER);
    }

    class ButtonHandler implements ActionListener {
        @Override //send_button, mail_text, sub_text, msg_text 상속 받음 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == send_button) {
                String to = mail_text.getText().trim();
                String subject = sub_text.getText().trim();
                String message = msg_text.getText().trim();

                if (to.isEmpty() || subject.isEmpty() || message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.");
                    return;
                }

                String user = "본인메일@gmail.com"; // 본인의 이메일
                String pass = "본인 앱비밀번호";     // 앱 비밀번호

                try {
                    SendMail.send(to, subject, message, user, pass);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "메일 전송 실패: " + ex.getMessage());
                }
            }
        }
    }
}