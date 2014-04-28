package views

import app.MyUI
import app.TableExample
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 20-1-14
 * Time: 13:01
 */
/** A start view for navigating to the main view */
/** Home page */
public class MainView extends AlgosView {

    public MainView() {
        super()
        addComponent(headingLabel());
        addComponent(new TableExample());
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Showing view: Main!");
    }

    private static Label headingLabel() {
        return new Label("Main");
    }

    private Button helpButton() {
        Button button = new Button("Help", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(MyUI.HELPVIEW)
            }
        });
        return button;
    }

//    @Override
//    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        Notification.show('Home page');
//    } // fine del costruttore
} // fine della classe
