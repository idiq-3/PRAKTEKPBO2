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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author acer
 */
public class configDB {
    private String jdbcURL="jdbc:mysql://localhost:3306/2210010133_pbo2";
    private String username="root";
    private String password="";
    
    private DefaultTableModel Modelnya;
    private TableColumn kolomnya;
    
    public configDB(){}
    
    public Connection getKoneksiDB() throws SQLException {
        try {
            Driver mysqldriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
            System.out.println("Koneksi DB berhasil");
        } catch (Exception e){
            System.out.println("Gagal konek ke DB: " + e.getMessage());
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(jdbcURL, username, password);
    }
    public boolean DuplicateKey (String NamaTabel, String PrimaryKey, String isiData){
        boolean hasil=false; //definisi awal
        try{
            String SQL = "SELECT * FROM " + NamaTabel + " WHERE " + PrimaryKey + " ='" + isiData + "'";
            Statement perintah = getKoneksiDB().createStatement();
            ResultSet hasilData = perintah.executeQuery(SQL);
        
            hasil=hasilData.next();//true
        } catch (Exception e){
          System.out.println("Error DuplicateKey: " + e.getMessage());
       }
       return hasil;
    }
    public String gettabelfield (String[] field)
    {
        String hasilnya="";
        int deteksiIndexAkhir=field.length-1;
        try{
            for (int i = 0; i < field.length; i++){
                if (i==deteksiIndexAkhir){
                    hasilnya=hasilnya+field[i];                    
                }else{
                    hasilnya=hasilnya+field[i]+',';
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "("+hasilnya+")";
    }
    public String getisitabel (String[] isi)
    {
        String result="";
        int deteksiIndex=isi.length-1;
        try{
            for (int i = 0; i < isi.length; i++){
                if (i==deteksiIndex){
                    result=result+isi[i]+",";                    
                }else{
                    result=result+isi[i]+',';
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "("+result+")";
    }
    
}
