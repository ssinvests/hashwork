package hashwork.client.content.training.employee.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.MainLayout;
import hashwork.client.content.training.employee.EmployeeTrainingMenu;
import hashwork.client.content.training.employee.table.FullfilledRequestCompetenciesTable;

/**
 * Created by hashcode on 2015/10/08.
 */
public class FullfilledRequestTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final MainLayout main;

    private final FullfilledRequestCompetenciesTable table;

    public FullfilledRequestTab(MainLayout app) {
        main = app;

        table = new FullfilledRequestCompetenciesTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }


    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {

        }
    }

    private void getHome() {
        main.content.setSecondComponent(new EmployeeTrainingMenu(main, "FULLFILLED"));
    }

    private void addListeners() {

        table.addValueChangeListener((Property.ValueChangeListener) this);
    }
}
