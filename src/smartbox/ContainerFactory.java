package smartbox;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class ContainerFactory implements AppFactory {
    public Container makeModel(){
        return new Container();
    };

    @Override
    public View makeView(Model m) {
        return new ContainerView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Add", "Rem", "Run"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command c = null;
        switch (type) {
            case "Add":
                c = new AddCommand(model);
                break;
            case "Rem":
                c = new RemoveCommand(model);
                break;
            case "Run":
                c = new RunCommand(model);
                break;
        };
        return c;

    }

    @Override
    public String getTitle() {
        return "SmartBox";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Add: add named component to container", "Rem: remove named component from container"
        , "Run: run named App"};
    }

    @Override
    public String about() {
        return "SmartBox: Container Component";
    }
}
