package Memento;
import java.util.ArrayList;
import java.util.List;

public class AdminCareTaker {
    private static AdminCareTaker firstInstance = null;
    
    private AdminCareTaker() {}
    
    public static AdminCareTaker getInstance() {
        if(firstInstance == null) {
            firstInstance = new AdminCareTaker();
        }
        return firstInstance;
    }
    //Create a new Memento List that will store all the states saved from the Admin GUI
    private AdminMemento memento;
    
    //add a state of the Admin GUI into a Memento list
    public void add(AdminMemento state) {
        memento = state;
    }
    
    //choose a state from the Memento list to pass back Originator
    public AdminMemento getMemento() {
        return memento;
    }
}
