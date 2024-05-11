package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    protected Boolean unsavedChanges = false;
    protected String fileName = null;
    public void changed(){
        unsavedChanges = true;
        notifySubscribers();
    }
    public String getFileName(){
        return fileName;
    }
    public Boolean getUnsavedChanges(){
        return unsavedChanges;
    }
    public void setFileName(String name){fileName=name;}
    public void setUnsavedChanges(Boolean state){unsavedChanges=state;}
}
