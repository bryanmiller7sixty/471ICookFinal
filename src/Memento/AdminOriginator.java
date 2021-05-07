package Memento;
import Command_Factory.CommandManager;
import java.util.List;

public class AdminOriginator {
    private List<String> directionList;
    private List<String> ingredientType;
    private List<String> ingredientName;
    private List<String> ingredientQuantity;
    private String title;
    private String authorRec;
    private static AdminOriginator firstInstance = null;
    private CommandManager commandManager;
    private boolean flag;
    
    private AdminOriginator() {}
    
    public static AdminOriginator getInstance() {
        if(firstInstance == null) {
            firstInstance = new AdminOriginator();
        }
        return firstInstance;
    }
    
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
    public void setDirectionList(List<String> directionList) {
        this.directionList = directionList;
    }
    public void setIngredientType(List<String> ingredientType) {
        this.ingredientType = ingredientType;
    }
    public void setIngredientName(List<String> ingredientName) {
        this.ingredientName = ingredientName;
    }
    public void setIngredientQuantity(List<String> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthorRec(String authorRec) {
        this.authorRec = authorRec;
    }
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public AdminMemento saveStateToMemento() {
        return new AdminMemento(directionList, ingredientType, ingredientName, ingredientQuantity, title, authorRec, commandManager, flag);
    }
    
    public void getStateFromMemento(AdminMemento objMemento) {
        AdminMemento memento = objMemento;
        directionList = memento.getDirectionList();
        ingredientType = memento.getIngredientType();
        ingredientName = memento.getIngredientName();
        ingredientQuantity = memento.getIngredientQuantity();
        title = memento.getTitle();
        authorRec = memento.getAuthorRec();
        commandManager = memento.getCommandManager();
        flag = memento.getFlag();
    }
}
