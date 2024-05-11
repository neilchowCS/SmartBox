package mvc;

public interface AppFactory {
    //Model model = new Model();
    default View makeView(Model m){return null;}

    default Model makeModel(){
        return null;
    }
    default String getTitle() {
        return "Title not set in specific factory";
    }
    default String about(){return "information not specified in specific factory";}
    default String[] getHelp(){return new String[]{"information for buttons not specified in factory"};}
    default Command makeEditCommand(Model model, String type, Object source){
        return null;
    }
    default String[] getEditCommands(){return new String[]{};}
}
