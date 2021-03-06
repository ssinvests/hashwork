package hashwork.client.content.system.utilities.forms;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import hashwork.app.facade.LocationFacade;
import hashwork.app.util.fields.ButtonsHelper;
import hashwork.app.util.fields.UIComboBoxHelper;
import hashwork.app.util.fields.UIComponentHelper;
import hashwork.client.content.system.utilities.model.FunderModel;
import hashwork.domain.ui.location.Location;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by hashcode on 2015/10/12.
 */
public class FunderForm extends FormLayout {

    private final FunderModel bean;
    public final BeanItem<FunderModel> item;
    public final FieldGroup binder;
    final UIComponentHelper UIComponent;
    final UIComboBoxHelper UIComboBox;
    // Define Buttons
    public Button save = new Button("Save");
    public Button edit = new Button("Edit");
    public Button cancel = new Button("Cancel");
    public Button update = new Button("Update");
    public Button delete = new Button("Delete");

    public FunderForm() {
        bean = new FunderModel();
        item = new BeanItem<>(bean);
        binder = new FieldGroup(item);
        UIComponent = new UIComponentHelper();
        UIComboBox = new UIComboBoxHelper();

        TextField trainingFunderName = UIComponent.getGridTextField("Funder  Name :", "trainingFunderName", FunderModel.class, binder);
        TextField contactNumber = UIComponent.getGridTextField("Contact Number :", "contactNumber", FunderModel.class, binder);
        TextField costCenter = UIComponent.getGridTextField("Cost Center :", "costCenter", FunderModel.class, binder);
        TextField postalCode = UIComponent.getGridTextField("Postal Code :", "postalCode", FunderModel.class, binder);


        TextArea postalAddress = UIComponent.getGridTextArea("Postal Address :", "postalAddress", FunderModel.class, binder);
        TextArea physicalAddress = UIComponent.getGridTextArea("Physical  Address :", "physicalAddress", FunderModel.class, binder);


        final ComboBox city = UIComboBox.getComboBox("City :", "city", FunderModel.class, binder, new Consumer<ComboBox>() {
            public void accept(ComboBox comboBox) {
                Set<Location> cities = LocationFacade.locationService
                        .findAll()
                        .parallelStream()
                        .filter(city -> city.getId() != city.getParentId())
                        .collect(Collectors.toSet());

                for (Location city : cities) {
                    comboBox.addItem(city.getId());
                    comboBox.setItemCaption(city.getId(), city.getName());
                }
            }
        });

        GridLayout grid = new GridLayout(4, 10);
        grid.setSizeFull();
        grid.addComponent(trainingFunderName, 0, 0);
        grid.addComponent(costCenter, 1, 0);
        grid.addComponent(contactNumber, 2, 0);
        grid.addComponent(physicalAddress, 0, 1, 0, 2);
        grid.addComponent(postalAddress, 1, 1, 1, 2);
        grid.addComponent(postalCode, 2, 1);
        grid.addComponent(city, 2, 2);

        HorizontalLayout buttons = ButtonsHelper.getButtons(save, edit, cancel, update, delete);
        buttons.setSizeFull();
        grid.addComponent(new Label("<hr/>", ContentMode.HTML), 0, 6, 2, 6);
        grid.addComponent(buttons, 0, 8, 2, 8);
        addComponent(grid);
    }


}