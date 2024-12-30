/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

// Berfungsi mengambil file laporan yang dibuat dari IReport
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author acer
 */
public class configDB {
    
    String jdbcURL ="jdbc:mysql://localhost:3306/2210010133_pbo2";
    String username ="root";
    String password ="";
    
    private DefaultTableModel Modelnya;
    private TableColumn kolomnya;
    
    Connection koneksi;
    
    public configDB(){
        try (Connection Koneksi = DriverManager.getConnection(jdbcURL, username, password)){
            Driver mysqldriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            System.out.println("Berhasil");
        } catch (SQLException error) {
            System.err.println(error.toString());
        }
    }
    
    public configDB(String url, String user, String pass){
        
        try(Connection Koneksi = DriverManager.getConnection(url, user, pass)) {
            Driver mysqldriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            
            System.out.println("Berhasil");
        } catch (Exception error) {
            System.err.println(error.toString());
        }
        
    }
    
    
    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/2210010133_pbo2";  // Ganti dengan URL dan database Anda
            String username = "root";  // Ganti dengan username database Anda
            String password = "";  // Ganti dengan password database Anda
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
            return null;
        }
    }

    
    public Boolean DuplicateKey(String NamaTable, String PrimaryKey, String isiData){
    Boolean hasil=false;
    
    int baris = 0;

    try{
        String SQL = "SELECT * FROM "+NamaTable+" WHERE "+PrimaryKey+" = '"+isiData+"'";
        Statement perintah = getKoneksi().createStatement();
        ResultSet hasilData = perintah.executeQuery(SQL);
        while(hasilData.next()){
            baris++;
        }

        if(baris==0){
            hasil=false;
        }else{
            hasil=true;
        }
        
        } catch (Exception e){
        System.err.println(e.toString());
        }

    return hasil; 
    }
    
//    public void SimpanKecamatan(String id_kecamatan, String kecamatan){
//        
//        try {
//            if (duplicateKey("kecamatan","id_kecamatan",id_kecamatan)){
//                JOptionPane.showMessageDialog(null, "ID sudah terdaftar");
//            } else{
//                String SQL = "INSERT INTO kecamatan (id_kecamatan, kecamatan) Value('"+id_kecamatan+"','"+kecamatan+"')";
//                Statement perintah = getKoneksi().createStatement();
//                
//                perintah.executeUpdate(SQL);
//                perintah.close();
//                getKoneksi().close();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
//                
//            }
//        } catch (Exception e) {
//            System.err.println(e.toString());
//        }
//    }
//    public void Simpanmasjid(String id_masjid, String nama_masjid, String alamat_masjid, String id_kecamatan, String foto){
//        
//        try {
//            if (duplicateKey("data_masjid","id_masjid",id_masjid)){
//                JOptionPane.showMessageDialog(null, "ID sudah terdaftar");
//            } else{
//                String SQL = "INSERT INTO data_masjid (id_masjid, nama_masjid, alamat_masjid, id_kecamatan, foto) Value('"+id_masjid+"','"+nama_masjid+"','"+alamat_masjid+"','"+id_kecamatan+"','"+foto+"')";
//                Statement perintah = getKoneksi().createStatement();
//                
//                perintah.executeUpdate(SQL);
//                perintah.close();
//                getKoneksi().close();
//                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
//                
//            }
//        } catch (Exception e) {
//            System.err.println(e.toString());
//        }
//    }
    
        public String getTabelField(String[] Field) {
        // Menggabungkan field dengan koma
        return "(" + String.join(", ", Field) + ")";
        }

      public String getTabelValue(String[] Value) {
    // Tambahkan tanda kutip untuk setiap elemen array
    for (int i = 0; i < Value.length; i++) {
        Value[i] = "'" + Value[i] + "'";
    }
    // Gabungkan nilai dengan koma
    return "(" + String.join(", ", Value) + ")";
}


    
// Method untuk membangun nilai field yang akan di-update
    public static String getFieldValueEdit(String[] Field, String[] value){
        String hasil = "";
        int deteksi = Field.length-1;
        try {
            for (int i = 0; i < Field.length; i++) {
                if (i==deteksi){
                    hasil = hasil +Field[i]+" ='"+value[i]+"'";
                }else{
                   hasil = hasil +Field[i]+" ='"+value[i]+"',";  
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return hasil;
    } 
    public void SimpanDinamis(String Table, String[] Field, String[] value) {
        try {
            // Generate the SQL query
            String SQL = "INSERT INTO " + Table +  getTabelField(Field) + " VALUES " + getTabelValue(value);
            // Execute the query
            Statement perintah = getKoneksi().createStatement();
            perintah.executeUpdate(SQL);
            perintah.close();
            getKoneksi().close();

            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    // Method untuk update data secara dinamis
    public static void UbahDinamis(String NamaTabel, String PrimaryKey, String IsiPrimary, String[] Field, String[] Value) {
        try {
            // Mencari apakah ID yang diberikan ada di dalam tabel
            String SQLCheck = "SELECT COUNT(*) FROM " + NamaTabel + " WHERE " + PrimaryKey + "='" + IsiPrimary + "'";
            Statement perintah = getKoneksi().createStatement();
            ResultSet rs = perintah.executeQuery(SQLCheck);

            if (rs.next() && rs.getInt(1) > 0) { // Jika ID ditemukan
                // Melakukan update jika ID ada
                String SQLUbah = "UPDATE " + NamaTabel + " SET " + getFieldValueEdit(Field, Value) + " WHERE " + PrimaryKey + "='" + IsiPrimary + "'";
                perintah.executeUpdate(SQLUbah);
                JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
            } else { // Jika ID tidak ditemukan
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
            }

            rs.close(); // Tutup ResultSet
            perintah.close(); // Tutup Statement
            getKoneksi().close(); // Tutup koneksi

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }     
    public static void HapusDinamis(String NamaTabel, String PK, String isi) {
        try {
            // Mencari apakah ID yang diberikan ada di dalam tabel
            String SQLCheck = "SELECT COUNT(*) FROM " + NamaTabel + " WHERE " + PK + " = '" + isi + "'";
            Statement perintah = getKoneksi().createStatement();
            ResultSet rs = perintah.executeQuery(SQLCheck);

            if (rs.next() && rs.getInt(1) > 0) { // Jika ID ditemukan
                // Menghapus data jika ID ditemukan
                String SQLDelete = "DELETE FROM " + NamaTabel + " WHERE " + PK + "='" + isi + "'";
                perintah.executeUpdate(SQLDelete);
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } else { // Jika ID tidak ditemukan
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
            }

            rs.close(); // Tutup ResultSet
            perintah.close(); // Tutup Statement
            getKoneksi().close(); // Tutup koneksi

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void settingJudulTabel(JTable Tabelnya, String[] JudulKolom){
        try {
            Modelnya = new DefaultTableModel();
            Tabelnya.setModel(Modelnya);
            for (int i = 0; i < JudulKolom.length; i++) {
                Modelnya.addColumn(JudulKolom[i]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void settingLebarKolom(JTable Tabelnya, int[] Kolom){
      try {
          Tabelnya.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
          for (int i = 0; i < Kolom.length; i++) {
           kolomnya = Tabelnya.getColumnModel().getColumn(i);
           kolomnya.setPreferredWidth(Kolom[i]);   
          }
      } catch (Exception e) {
          System.out.println(e.toString());
        }
    }
    //Untuk mengambil data tabel
    public Object[][] isiTabel(String SQL, int jumlah){
      Object[][] data =null;
      try {
         Statement perintah = getKoneksi().createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, 
            ResultSet.CONCUR_READ_ONLY
        );
         ResultSet dataset = perintah.executeQuery(SQL);
         dataset.last();
         int baris = dataset.getRow();
         dataset.beforeFirst();
         
         int j =0;
         data = new Object[baris][jumlah];
         
         while (dataset.next()){
             for (int i = 0; i < jumlah; i++) {
                 data[j][i]=dataset.getString(i+1);
             } // Looping barisnya dulu, baru kolomnya 🙏🙏
             j++;
         }
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return data;
    }
    public void tampilTabel(JTable Tabelnya, String SQL, String[] Judul){
      try {
        Tabelnya.setModel(new DefaultTableModel(isiTabel(SQL,Judul.length), Judul));
      } catch (Exception e) {
          System.out.println(e.toString());
      }
    }
    public void tampilLaporan(String laporanFile, String SQL) throws SQLException{
      try {
          File file = new File(laporanFile);
          JasperDesign jasDes = JRXmlLoader.load(file);

           JRDesignQuery sqlQuery = new JRDesignQuery();
           sqlQuery.setText(SQL);
           jasDes.setQuery(sqlQuery);

           JasperReport JR = JasperCompileManager.compileReport(jasDes);
           JasperPrint JP = JasperFillManager.fillReport(JR,null,getKoneksi()); 
           JasperViewer.viewReport(JP,false);
         } catch (JRException e) {
            JOptionPane.showMessageDialog(null,e.toString());       

         }
    }
    
//    public void cariData(JTable Tabelnya, String SQL, String[] Judul, String kolomPencarian, String kataKunci) {
//        try {
//            // Tambahkan WHERE untuk filter pencarian
//            String SQLSearch = SQL + " WHERE " + kolomPencarian + " LIKE '%" + kataKunci + "%'";
//            // Tampilkan data yang sudah difilter ke tabel
//            tampilTabel(Tabelnya, SQLSearch, Judul);
//        } catch (Exception e) {
//            System.out.println("Error saat mencari data: " + e.toString());
//        }
//    }
}