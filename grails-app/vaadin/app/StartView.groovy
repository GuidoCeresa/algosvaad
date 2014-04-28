package app

import com.vaadin.grails.Grails
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.*

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 20-1-14
 * Time: 13:01
 */
/** A start view for navigating to the main view */
public class StartView extends VerticalLayout implements View {

    public StartView() {
        setSizeFull()

        String homeLabel = Grails.i18n("default.home.label")
        Label label = new Label(homeLabel)
        this.addComponent(label)
        Label label2 = new Label('Pippus')
        this.addComponent(label2)

        Button button = new Button('Vai alla Home page',
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        getUI().getNavigator().navigateTo(MyUI.MAINVIEW)
                    }
                })
        addComponent(button)
        setComponentAlignment(button, Alignment.MIDDLE_CENTER)
    } // fine del metodo

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show('Applicazione: menu iniziale');
    } // fine del costruttore
} // fine della classe
