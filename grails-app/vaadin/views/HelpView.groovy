package views

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.shared.ui.label.ContentMode
import com.vaadin.ui.Label
import com.vaadin.ui.Notification

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 20-1-14
 * Time: 14:59
 */
public class HelpView extends AlgosView {

    public HelpView() {
        super()
        addComponent(headingLabel())
        addComponent(someText())
    } //fine del metodo costruttore

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show('Vista di aiuto')
    } //fine del metodo

    //@todo provvisorio
    private static Label headingLabel() {
        return new Label("Help")
    }

    //@todo provvisorio
    private static Label someText() {
        Label label = new Label('Pippoz, determinato e molto esteso')
        label.setContentMode(ContentMode.HTML)
        return label
    }

} // fine della classe
