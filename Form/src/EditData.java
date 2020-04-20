import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EditData extends JFrame {
    String[][] data = new String[480][3];
    String[] kolom = {"NIM", "Nama", "Alamat"};
    static String nim;
    JTable tabel;
    JLabel ljudul;
    JButton kembali, edit;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;

    JPanel panel;
    JLabel ljudul2, lnama, lnim, lalamat;
    JTextField tfnama, tfnim, tfalamat;
    JButton edit2;

    public EditData() {
        setTitle("Hapus Data Mahasiswa");

        ljudul      = new JLabel("SELURUH DATA MAHASISWA");
        tabel       = new JTable(data, kolom);
        scrollPane  = new JScrollPane(tabel);
        kembali     = new JButton("Kembali");
        edit       = new JButton("Edit");

        setSize(500, 350);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(scrollPane);
        add(kembali);
        add(edit);

        ljudul.setBounds(0, 20, 500, 20);
        scrollPane.setBounds(50, 60, 400, 200);
        kembali.setBounds(250, 280, 80, 20);
        edit.setBounds(150, 280, 80, 20);

        edit.setEnabled(false);

        try{
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();

            String sql  = "SELECT * FROM data_mhs";
            resultSet   = statement.executeQuery(sql);

            int p = 0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("NIM");
                data[p][1] = resultSet.getString("Nama");
                data[p][2] = resultSet.getString("Alamat");
                p++;
            }
            statement.close();
            koneksi.getKoneksi().close();
        }catch(SQLException sqle) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqle);
        }catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(rootPane, "Class Tidak Ditemukan" + cnfe);
        }

        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });

        tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelMouseClicked(e);
                edit.setEnabled(true);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editActionPerformed(e);
            }
        });
    }

    private void editActionPerformed(ActionEvent e) {
        panel       = new JPanel();
        ljudul2     = new JLabel("FORM EDIT DATA");
        lnama       = new JLabel("Nama");
        lnim        = new JLabel("NIM");
        lalamat     = new JLabel("Alamat");
        edit2       = new JButton("Edit");

        setSize(500, 350);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        panel.setLayout(null);
        panel.add(ljudul2);
        panel.add(lnama);
        panel.add(lnim);
        panel.add(lalamat);
        panel.add(tfnama);
        panel.add(tfnim);
        panel.add(tfalamat);
        panel.add(edit2);

        ljudul2.setBounds(0, 20, 500, 20);
        lnama.setBounds(20, 60, 100, 20);
        tfnama.setBounds(200, 60, 100, 20);
        lnim.setBounds(20, 90, 100, 20);
        tfnim.setBounds(200, 90, 100, 20);
        lalamat.setBounds(20, 120, 100, 20);
        tfalamat.setBounds(200, 120, 100, 20);
        edit2.setBounds(150, 280, 80, 20);

        edit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    KoneksiDB koneksi = new KoneksiDB();
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("UPDATE data_mhs SET NIM = '" + tfnim.getText() + "Nama='"+ tfnama.getText() + "'," + "Alamat ='"+tfalamat.getText()+ "' WHERE NIM ='" + nim + "'");
                    JOptionPane.showMessageDialog(null,"Data berhasil di update","Hasil",JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Data gagal di update","Hasil",JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(null,"Driver tidak ditemukan","Hasil",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void tabelMouseClicked(MouseEvent e) {
        int baris = tabel.rowAtPoint(e.getPoint());
        nim = tabel.getValueAt(baris,0).toString();
    }
}