import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditForm extends JFrame{

    JLabel ljudul, lnama, lnim, lalamat;
    JTextField tfnama, tfnim, tfalamat;
    JButton edit, kembali;
    Statement statement;
    ResultSet resultSet;

    public EditForm(String nim) {
        System.out.println(nim);

        ljudul      = new JLabel("FORM EDIT DATA");
        lnama       = new JLabel("Nama");
        lnim        = new JLabel("NIM");
        lalamat     = new JLabel("Alamat");

        tfnama      = new JTextField();
        tfnim       = new JTextField();
        tfalamat    = new JTextField();

        edit        = new JButton("Edit");
        kembali     = new JButton("Kembali");

        setTitle("Form Edit Data");
        setSize(500, 350);
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
        add(edit);
        add(kembali);

        ljudul.setBounds(0, 20, 500, 20);
        lnama.setBounds(20, 60, 100, 20);
        tfnama.setBounds(200, 60, 200, 20);
        lnim.setBounds(20, 90, 100, 20);
        tfnim.setBounds(200, 90, 200, 20);
        lalamat.setBounds(20, 120, 100, 20);
        tfalamat.setBounds(200, 120, 200, 20);
        edit.setBounds(150, 200, 80, 20);
        kembali.setBounds(250, 200, 80, 20);

        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EditData();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    KoneksiDB koneksi = new KoneksiDB();
                    statement = koneksi.getKoneksi().createStatement();
                    statement.executeUpdate("UPDATE data_mhs SET NIM = '" + tfnim.getText() + "', Nama='"+ tfnama.getText() + "', Alamat ='" + tfalamat.getText()+ "' WHERE NIM ='" + EditData.nim + "'");
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
}
