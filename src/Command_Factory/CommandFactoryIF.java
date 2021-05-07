package Command_Factory;
import java.util.ArrayList;
public interface CommandFactoryIF {
    public CommandIF createCommand(String request, ArrayList<String> typeList);
}
