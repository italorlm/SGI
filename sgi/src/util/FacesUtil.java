package util;

import javax.faces.event.ActionEvent;

public class FacesUtil {

    // Getters -----------------------------------------------------------------------------------

    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }

}
