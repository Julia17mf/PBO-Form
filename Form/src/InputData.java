import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputData extends JFrame {
    JLabel ljudul, lnama, lnim, lalamat;
    JTextField tfnama, tfnim, tfalamat;
    JButton simpan, kembali;
    Statement statement;

    public InputData() {
        setTitle("Input Data Mahasiswa");

        ljudul      = new JLabel("INPUT DATA MAHASISWA");
        lnama       = new JLabel("Nama");
        lnim        = new JLabel("NIM");
        lalamat     = new JLabel("Alamat");

        tfnama      = new JTextField();
        tfnim       = new JTextField();
        tfalamat    = new JTextField();

        simpan      = new JButton("Simpan");
        kembali     = new JButton("Kembali");

        setSize(300, 250);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(lnama);
        add(lnim);
        add(lalamat);
        add(tfnama);
        add(tfnim);
        add(tfalamat);
        add(simpan);
        add(kembali);

        ljudul.setBounds(0, 20, 300, 20);
        lnama.setBounds(50, 60, 50, 20);
        tfnama.setBounds(100, 60, 140, 20);
        lnim.setBounds(50, 90, 50, 20);
        tfnim.setBounds(100, 90, 140, 20);
        lalamat.setBounds(50, 120, 50, 20);
        tfalamat.setBounds(100, 120, 140, 20);
        simpan.setBounds(50, 150, 80, 20);
        kembali.setBounds(160, 150, 80, 20);

        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KoneksiDB koneksi = new KoneksiDB();
                try{
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("INSERT INTO data_mhs VALUES ('" + tfnim.getText() + "','"
                    + tfnama.getText() + "','" + tfalamat.getText() + "')");
                    JOptionPane.showMessageDialog(rootPane, "Data Tersimpan");
                }catch(ClassNotFoundException ex) {
                    Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
                }catch (SQLException ex) {
                    Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
    }
}
