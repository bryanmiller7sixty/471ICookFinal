package Command_Factory;
import java.util.ArrayList;
public class CommandFactory implements CommandFactoryIF{
    @Override
    public CommandIF createCommand(String request, ArrayList<String> type) {
        //Make sure the type is just a common class here
        //Then use a cast and get fields to get the correct info
        if(request.equalsIgnoreCase("Direction")){
            return new AddDirectionCommand(type.get(0));
        }else if(request.equalsIgnoreCase("Ingredient")){
            return new AddIngredientCommand(type.get(0), type.get(1), type.get(2));
        }
        throw new IllegalArgumentException();
    }
}
