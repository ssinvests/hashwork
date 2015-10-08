package hashwork.client.content.training.employee.views;

import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;
import hashwork.client.content.training.employee.table.FullfilledRequestCompetenciesTable;

/**
 * Created by hashcode on 2015/10/08.
 */
public class FullfilledRequestTab extends VerticalLayout implements
        Property.ValueChangeListener {

    private final HashWorkMain main;

    private final FullfilledRequestCompetenciesTable table;

    public FullfilledRequestTab(HashWorkMain app) {
        main = app;

        table = new FullfilledRequestCompetenciesTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }


    @Override
    public void valueChange(ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {

        }
    }

    private void getHome() {
        main.content.setSecondComponent(new EmployeeTrainingMenu(main, "FULLFILLED"));
    }

    private void addListeners() {

        table.addValueChangeListener((ValueChangeListener) this);
    }
}
