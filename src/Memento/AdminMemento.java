package Memento;
import Command_Factory.CommandManager;
import java.util.ArrayList;
import java.util.List;

public class AdminMemento {
    private List<String> directionList;
    private List<String> ingredientType;
    private List<String> ingredientName;
    private List<String> ingredientQuantity;
    private String title;
    private String authorRec;
    private CommandManager commandManager;
    private boolean flag;
    
    public List<String> getDirectionList() {
        return directionList;
    }
    public List<String> getIngredientType() {
        return ingredientType;
    }
    public List<String> getIngredientName() {
        return ingredientName;
    }
    public List<String> getIngredientQuantity() {
        return ingredientQuantity;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthorRec() {
        return authorRec;
    }
    public CommandManager getCommandManager() {
        return commandManager;
    }
    public boolean getFlag(){
        return flag;
    }
    
    public AdminMemento(List<String> directionList, List<String> ingredientType, List<String> ingredientName, List<String> ingredientQuantity, String title, String authorRec, CommandManager commandManager, Boolean flag) {
        this.directionList = directionList;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
        this.title = title;
        this.authorRec = authorRec;
        this.commandManager = commandManager;
        this.flag = flag;
    }
}
