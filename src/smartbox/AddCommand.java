package smartbox;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class AddCommand extends Command {
    public AddCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        try {
            Container c = (Container) model;
            c.addComponent(Utilities.ask("Component name?"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Utilities.error(e);
        }
    }
}
