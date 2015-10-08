package hashwork.client.content.training.institutions.views;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import hashwork.app.facade.TrainingFacade;
import hashwork.app.facade.UtilFacade;
import hashwork.domain.ui.training.Course;
import hashwork.domain.ui.training.ScheduledCourse;

/**
 * Created by hashcode on 2015/10/08.
 */
public class AssessParticipantTab extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final HashWorkMain main;
    private final ScheduledCourseTable table;
    private final ScheduledCourseForm form;


    public AssessParticipantTab(HashWorkMain app) {

        main = app;
        form = new ScheduledCourseForm();
        table = new ScheduledCourseTable(main);
        setSizeFull();
        addComponent(table);
        addListeners();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == form.save) {
            saveForm(form.binder);
        } else if (source == form.edit) {
            setEditFormProperties();
        } else if (source == form.cancel) {
            getHome();
        } else if (source == form.update) {
            saveEditedForm(form.binder);
        } else if (source == form.delete) {
            deleteForm(form.binder);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        final Property property = event.getProperty();
        if (property == table) {
            final ScheduledCourse schecduleCourse = TrainingFacade.getCourseScheduleModelService().findById(table.getValue().toString());
            form.binder.setItemDataSource(new BeanItem<ScheduledCourse>(schecduleCourse));
            setReadFormProperties();
        }
    }

    private void setReadFormProperties() {
        form.binder.setReadOnly(true);
        //Buttons Behaviou
        form.save.setVisible(false);
        form.edit.setVisible(true);
        form.cancel.setVisible(true);
        form.delete.setVisible(true);
        form.update.setVisible(false);
    }

    private void addListeners() {
        //Register Button Listeners
        form.save.addClickListener((Button.ClickListener) this);
        form.edit.addClickListener((Button.ClickListener) this);
        form.cancel.addClickListener((Button.ClickListener) this);
        form.update.addClickListener((Button.ClickListener) this);
        form.delete.addClickListener((Button.ClickListener) this);
        //Register Table Listerners
        table.addValueChangeListener((Property.ValueChangeListener) this);
    }

    private void setEditFormProperties() {
        form.binder.setReadOnly(false);
        form.save.setVisible(false);
        form.edit.setVisible(false);
        form.cancel.setVisible(true);
        form.delete.setVisible(false);
        form.update.setVisible(true);
    }

    private void saveEditedForm(FieldGroup binder) {
        try {

            binder.commit();
            TrainingFacade.getCourseScheduleModelService().merge(getEntity(binder));

        } catch (FieldGroup.CommitException e) {
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void deleteForm(FieldGroup binder) {
        TrainingFacade.getCourseScheduleModelService().remove(getEntity(binder));
        getHome();
    }

    private ScheduledCourse getEntity(FieldGroup binder) {
        CourseBean bean = ((BeanItem<CourseBean>) binder.getItemDataSource()).getBean();
        Status status = UtilFacade.getStatusModelService().findById(bean.getCourseStatusId());

        Course course = TrainingFacade.getCourseModelService().findById(bean.getCourseName());

        return null;
    }

    private void saveForm(FieldGroup binder) {
        try {

            binder.commit();
            TrainingFacade.getCourseScheduleModelService().persist(getEntity(binder));
            getHome();
            Notification.show("Record ADDED!", Notification.Type.TRAY_NOTIFICATION);
        } catch (FieldGroup.CommitException e) {
            getEntity(binder);
            Notification.show("Values MISSING!", Notification.Type.TRAY_NOTIFICATION);
            getHome();
        }
    }

    private void getHome() {
        main.content.setSecondComponent(new InstitutionManagementMenu(main, "PARTICIPANTS"));
    }

}