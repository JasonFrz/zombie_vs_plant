
import java.io.*;
import java.util.logging.Logger;

public class DataLevel {
   static String Lvl = "1";
   static String [][] jenisZombie = {{"NormalZombie"}, {"NormalZombie","ConeHeadZombie"}, {"NormalZombie","ConeHeadZombie", "FootballZombie"}, {"NormalZombie","ConeHeadZombie", "FootballZombie", "gargantuar"}};
   static int [][][] Persentase = {{{0,99}},{{0,59},{60,99}}, {{0,43},{44,76},{77,99}}, {{0,35},{36,55},{56,79},{80,99}}}; ;
   public DataLevel() {
       try {
           File file = new File("Level.vbhv");
           
           if(!file.exists()) {
               BufferedWriter bwr = new BufferedWriter(new FileWriter(file));
               bwr.write("1");
               bwr.close();
               Lvl = "1";
           } else {
               BufferedReader br = new BufferedReader(new FileReader(file));
               Lvl = br.readLine();
           }
       } catch (Exception ex) {
           
           
       }
   }
   public static void write(String lvl) {
       File file = new File("Level.vbhv");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(file));
            bwr.write(lvl);
            bwr.close();
            Lvl = lvl;
        } catch (IOException ex) {
            Logger.getLogger(DataLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
               
   }
}
