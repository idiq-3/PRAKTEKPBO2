/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package keagamaan;
import forms.mainframe;
import config.configDB;

/**
 *
 * @author acer
 */
public class Keagamaan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new mainframe().setVisible(true);
        configDB obj = new configDB();
        String field[]={"id_masjid","nama_masjid","alamat_masjid","id_kecamatan","foto"};
        String isi[]={"1","baitullah","bjm","1","masjid.png"};
        
        System.out.println(obj.gettabelfield(field));
        System.out.println(obj.getisitabel(isi));
    }
    
}
