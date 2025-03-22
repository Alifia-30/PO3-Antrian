/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package antrian;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Antrian {
    private JFrame frame;
    private JLabel clockLabel, queueLabel;
    private JButton addButton;
    private int queueNumber = 1;
    private Timer clockTimer;

    public Antrian() {
        frame = new JFrame("Nomor Antrian Bank");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        
        JLabel titleLabel = new JLabel("Nomor Antrian", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel);
        
        clockLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(clockLabel);
        
        queueLabel = new JLabel("A000", SwingConstants.CENTER);
        queueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        frame.add(queueLabel);
        
        addButton = new JButton("Ambil Nomor");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(addButton);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeQueueNumber();
            }
        });
        
        startClock();
        frame.setVisible(true);
    }
    
    private void startClock() {
        clockTimer = new Timer(1000, e -> {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            clockLabel.setText(formatter.format(new Date()));
        });
        clockTimer.start();
    }
    
    private void takeQueueNumber() {
        new Thread(() -> {
            queueLabel.setText("A" + String.format("%03d", queueNumber++));
        }).start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Antrian::new);
    }
}

