import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    JLabel ljudul;
    JButton input, tampil, hapus, edit, exit;
    JOptionPane confirm;

    public Menu() {
        setTitle("Form Mahasiswa");

        ljudul  = new JLabel("MENU");

        input   = new JButton("1. Input Data Mahasiswa");
        tampil  = new JButton("2. Tampilkan Data Mahasiswa");
        hapus   = new JButton("3. Hapus Data Mahasiswa");
        edit    = new JButton("4. Edit Data Mahasiswa");
        exit    = new JButton("0. Exit");

        setSize(300, 300);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        input.setHorizontalAlignment(SwingConstants.LEFT);
        tampil.setHorizontalAlignment(SwingConstants.LEFT);
        hapus.setHorizontalAlignment(SwingConstants.LEFT);
        edit.setHorizontalAlignment(SwingConstants.LEFT);
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(input);
        add(tampil);
        add(hapus);
        add(edit);
        add(exit);

        ljudul.setBounds(0, 20, 300, 20);
        input.setBounds(20, 50, 250, 20);
        tampil.setBounds(20, 80, 250, 20);
        hapus.setBounds(20, 110, 250, 20);
        edit.setBounds(20, 140, 250, 20);
        exit.setBounds(20, 170, 250, 20);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new InputData();
            }
        });

        tampil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TampilData();
            }
        });

        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new HapusData();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EditData();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}