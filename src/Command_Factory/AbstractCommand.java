package Command_Factory;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
public abstract class AbstractCommand implements CommandIF{
    @Override
    public void undo(MenuButton mb, ListView listView){
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        try {
            throw new IllegalAccessException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}

