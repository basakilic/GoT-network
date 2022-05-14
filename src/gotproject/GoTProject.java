package gotproject;
import java.io.FileNotFoundException;

public class GoTProject {

    public static void main(String[] args){        
        try {
            Menu menu = new Menu();
            menu.intro();
        } catch (FileNotFoundException ex) {
        }
    }
}
