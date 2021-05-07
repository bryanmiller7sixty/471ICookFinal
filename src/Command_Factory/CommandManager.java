package Command_Factory;
import java.util.ArrayList;
import java.util.List;
public class CommandManager {
    private List<AbstractCommand> addDirectionCommandList;
    private List<AbstractCommand> addIngredientCommandList;
    public CommandManager(){
        addDirectionCommandList = new ArrayList<AbstractCommand>();
        addIngredientCommandList = new ArrayList<AbstractCommand>();
    }
    public void addIngredientCommand(CommandIF addIngredientCommand){
        AbstractCommand aI = (AbstractCommand) addIngredientCommand;
        addIngredientCommandList.add(aI);
    }
    public void addDirectionCommand(CommandIF addDirectionCommand){
        AbstractCommand aD = (AbstractCommand) addDirectionCommand;
        addDirectionCommandList.add(aD);
    }
    public List<String> getDirectionList(){
        List<String> directionList = new ArrayList<>();
        for(CommandIF commandName: addDirectionCommandList){
            directionList.add(((AddDirectionCommand)commandName).getDirection());
        }
        return directionList;
    }
    public List<String> getNameForIngredient(){
        List<String> nameList = new ArrayList<>();
        for(CommandIF commandName: addIngredientCommandList){
            nameList.add(((AddIngredientCommand)commandName).getName());
        }
        return nameList;
    }
    public List<String> getTypeForIngredient(){
        List<String> nameList = new ArrayList<>();
        for(CommandIF commandName: addIngredientCommandList){
            nameList.add(((AddIngredientCommand)commandName).getType());
        }
        return nameList;
    }
    public List<String> getQuanForIngredient(){
        List<String> nameList = new ArrayList<>();
        for(CommandIF commandName: addIngredientCommandList){
            nameList.add(((AddIngredientCommand)commandName).getQuantity());
        }
        return nameList;
    }
    public void updateAddDirectionCommandList(int index){
        //Need to remove up until the size minus the index
        //Since we want to keep whats at the beginning we must remove backwards
        //However, since the index is technically how many elements we want to remove going backwards
        //All we need to do is subtract the size minus the index and that will be our stopping point
        int newIndex = (addDirectionCommandList.size() - 1) - index;
        for(int i = addDirectionCommandList.size() - 1; i >= newIndex; i--){
            addDirectionCommandList.remove(i);
        }
    }
    public void updateIngredientCommandList(int index){
        //Need to remove up until the size minus the index
        //Since we want to keep whats at the beginning we must remove backwards
        //However, since the index is technically how many elements we want to remove going backwards
        //All we need to do is subtract the size minus the index and that will be our stopping point
        int newIndex = (addIngredientCommandList.size() - 1) - index;
        for(int i = addIngredientCommandList.size() - 1; i >= newIndex; i--){
            addIngredientCommandList.remove(i);
        }
    }
}
