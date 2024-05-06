
import java.io.*;
import java.util.logging.Logger;

public class LevelData {
   static String Lvl = "1";
   static String [][] Level = {{"NormalZombie"}, {"NormalZombie","ConeHeadZombie"}, {"NormalZombie","ConeHeadZombie", "FootballZombie"}, {"NormalZombie","ConeHeadZombie", "FootballZombie", "gargantuar"}};
   static int [][][] LevelValue = {{{0,99}},{{0,49},{50,99}}, {{0,33},{34,66},{67,99}}, {{0,25},{26,50},{51,75},{76,99}}}; ;
   public LevelData() {
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
            Logger.getLogger(LevelData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
               
   }
}
