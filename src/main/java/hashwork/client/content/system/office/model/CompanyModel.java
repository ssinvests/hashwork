package hashwork.client.content.system.office.model;

import java.io.Serializable;

/**
 * Created by garran on 2015/09/20.
 */
public class CompanyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
