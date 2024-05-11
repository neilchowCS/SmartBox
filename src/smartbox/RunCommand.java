package smartbox;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class RunCommand extends Command {
    public RunCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        try {
            Container c = (Container) model;
            c.launch(Utilities.ask("Component name?"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Utilities.error(e);
        }
    }
}
