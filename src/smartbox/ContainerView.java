package smartbox;
import mvc.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Collection;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components);
    }

    @Override
    public void update() {

        components.removeAll();

        Container c = (Container)model;
        Collection<Component> cc = c.getComponents();
        for (Component ccc : cc){
            components.add(ccc.name);
        }

        super.update();
    }

    // etc.
}