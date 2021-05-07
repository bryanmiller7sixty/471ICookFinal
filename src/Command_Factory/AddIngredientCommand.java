package Command_Factory;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
public class AddIngredientCommand extends AbstractCommand{
    private String type;
    private String name;
    private String quantity;
    private MenuItem menuItem;
    public AddIngredientCommand(String type, String name, String quantity){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public void undo(MenuButton mb, ListView ingredientListView){
        //Get the current index of the menu button
        int index = mb.getItems().indexOf(menuItem);
        //Remove all previous items (all action including the previous ones up to this point will be un-done)
        mb.getItems().remove(0, index + 1);
        ingredientListView.getItems().remove(ingredientListView.getItems().size() - (index + 1), ingredientListView.getItems().size());
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getQuantity(){
        return quantity;
    }
    @Override
    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }
    @Override
    public String toString() {
        return type + "...";
    }
}
