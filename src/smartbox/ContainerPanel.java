package smartbox;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import mvc.*;

public class ContainerPanel extends AppPanel {
	java.awt.List components;

	private JButton add;
	private JButton rem;
	private JButton run;

	public ContainerPanel(AppFactory factory) {
        super(factory);
        // set up controls

		add = new JButton("Add");
		rem = new JButton("Rem");
		run = new JButton("Run");

		add.addActionListener(this);
		rem.addActionListener(this);
		run.addActionListener(this);

		controlPanel.setLayout((new GridLayout(3, 1, 0, 0)));

		controlPanel.add(createButtonPanel(add));
		controlPanel.add(createButtonPanel(rem));
		controlPanel.add(createButtonPanel(run));
	}

	private JPanel createButtonPanel(JButton button) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Center-align buttons
		panel.add(button);
		return panel;
	}

        // this override needed to re-initialize component fields table: 
	public void setModel(Model m) {
           super.setModel(m);
           ((Container) m).initContainer(); // restore fields of smartbox.components
        }

	public static void main(String[] args) {
		AppPanel panel = new ContainerPanel(new ContainerFactory());
		panel.display();
	}
}