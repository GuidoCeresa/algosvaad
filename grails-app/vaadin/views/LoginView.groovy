package views
import com.vaadin.annotations.Theme
/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 20-1-14
 * Time: 15:15
 */
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent
import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField

@Theme("reindeer")
@SuppressWarnings("serial")
public class LoginView extends AlgosView {

    public LoginView() {
        super(false)
        Label label = new Label("Enter your information below to log in.")
        TextField username = new TextField("Username")
        TextField password = new TextField("Password")

        addComponent(label)
        addComponent(username)
        addComponent(password)
        addComponent(loginButton())
    } //fine del metodo costruttore

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Salve! Per favore, registrati")
    }

    private Button loginButton() {
        Button button = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo('home')
            }
        });
        return button;
    }

} // fine della classe
