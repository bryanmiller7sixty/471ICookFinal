package Command_Factory;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
public class AddDirectionCommand extends AbstractCommand{
    private String direction;
    private MenuItem menuItem;
    public AddDirectionCommand(String direction){
        this.direction = direction;
    }
    @Override
    public void undo(MenuButton mb, ListView directionListView){
        //Get the current index of the menu button
        int index = mb.getItems().indexOf(menuItem);
        //Remove all previous items (all action including the previous ones up to this point will be un-done)
        mb.getItems().remove(0, index + 1);
        directionListView.getItems().remove(directionListView.getItems().size() - (index + 1), directionListView.getItems().size());
    }
    public String getDirection(){
        return direction;
    }
    @Override
    public String toString(){
        if(direction.length() > 7) {
            return "Dir: " + direction.substring(0, direction.length() - 4);
        }
        return "Dir: " + direction;
    }
    @Override
    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }
}
