import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class HapusData extends JFrame {
    String[][] data = new String[480][3];
    String[] kolom = {"NIM", "Nama", "Alamat"};
    static String nim;
    JTable tabel;
    JLabel ljudul;
    JButton kembali, hapus;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;

    public HapusData() {
        setTitle("Hapus Data Mahasiswa");

        ljudul      = new JLabel("SELURUH DATA MAHASISWA");
        tabel       = new JTable(data, kolom);
        scrollPane  = new JScrollPane(tabel);
        kembali     = new JButton("Kembali");
        hapus       = new JButton("Hapus");

        setSize(500, 350);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(ljudul);
        add(scrollPane);
        add(kembali);
        add(hapus);

        ljudul.setBounds(0, 20, 500, 20);
        scrollPane.setBounds(50, 60, 400, 200);
        kembali.setBounds(250, 280, 80, 20);
        hapus.setBounds(150, 280, 80, 20);

        hapus.setEnabled(false);

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
                hapus.setEnabled(true);
            }
        });

        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusActionPerformed(e);
            }
        });

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
    }

    private void hapusActionPerformed(ActionEvent e) {
        try{
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();

            String sql  = "DELETE FROM data_mhs WHERE NIM = '" + nim + "'";
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus");
            statement.close();
            koneksi.getKoneksi().close();
        }catch(SQLException sqle) {
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Dihapus" + sqle);
        }catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(rootPane, "Class Tidak Ditemukan" + cnfe);
        }
    }

    private void tabelMouseClicked(MouseEvent e) {
        int baris = tabel.rowAtPoint(e.getPoint());
        nim = tabel.getValueAt(baris,0).toString();
    }
}