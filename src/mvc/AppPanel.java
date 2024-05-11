package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;
    private Model model;
    private JFrame frame;
    protected JPanel controlPanel;
    private View view;
    private AppFactory factory;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        controlPanel = new JPanel();
        view = factory.makeView(model);
        controlPanel.setBackground(Color.PINK);
        this.setLayout(new GridLayout(1, 2));

        model.subscribe(this);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        addComponents();
    }

    public void addComponents() {
        this.add(controlPanel, BorderLayout.CENTER);
        this.add(view);
    }

    public void display() {frame.setVisible(true);}
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean found = false;
        try {
            for (String s : factory.getEditCommands()){
                if(s.equals(cmd)){
                    found = true;
                    break;
                }
            }
            if(found){
                Command c = factory.makeEditCommand(model, cmd, e.getSource());
                c.execute();
            }
            else{
                switch (cmd) {
                    case "Save": {
                        Utilities.save(model, false);
                        break;
                    }
                    case "SaveAs": {
                        Utilities.save(model, true);
                        break;
                    }

                    case "Open": {
                        Model newModel = Utilities.open(model);
                        if (newModel != null) setModel(newModel);
                        break;
                    }

                    case "New": {
                        Utilities.saveChanges(model);
                        setModel(factory.makeModel());
                        model.setUnsavedChanges(false);
                        break;
                    }

                    case "Quit": {
                        Utilities.saveChanges(model);
                        System.exit(0);
                        break;
                    }

                    case "About": {
                        Utilities.inform(factory.about());
                        break;
                    }
                    case "Help":{
                        Utilities.inform(factory.getHelp());
                        break;
                    }
                    default: {
                        throw new Exception("Unrecognized command: " + cmd);
                    }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();

    }
    public Model getModel() { return model; }

    @Override
    public void update() {

    }
}
