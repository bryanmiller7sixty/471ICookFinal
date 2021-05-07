package Command_Factory;
public class Utility_Factory_Method {
    public static CommandFactoryIF createCommandFactoryObject() {
        return new CommandFactory();
    }
}
