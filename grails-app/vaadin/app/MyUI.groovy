package app

import com.vaadin.annotations.Theme
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import views.ClienteView
import views.HelpView
import views.LoginView
import views.MainView
import views.VersioneView
import com.vaadin.addon.calendar.ui.*

/**
 * @author
 */
@Theme("runo")
class MyUI extends UI {

    public Navigator navigator
    protected static final String NOMEAPP = 'Esempio di programma'
    protected static final String MAINVIEW = 'home'
    public static final String HELPVIEW = 'help'
    public static final String CLIENTEVIEW = 'cliente'
    public static final String VERSIONEVIEW = 'versione'
    public static final TipoMenu TIPOMENU = TipoMenu.bottoni

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle(NOMEAPP)
        final VerticalLayout layout = new VerticalLayout()
        layout.setMargin(true)
        layout.setSpacing(true)
        setContent(layout)
        ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout)

        // Create a navigator to control the views
        navigator = new Navigator(getCurrent(), viewDisplay)

        // Create and register the views
        // Le views possono essere create qui (se già note), oppure dall'interno delle altre view
        navigator.addView('', new LoginView())
        navigator.addView(MAINVIEW, new MainView())
        navigator.addView(HELPVIEW, new HelpView())
        navigator.addView(CLIENTEVIEW, new ClienteView())
        navigator.addView(VERSIONEVIEW, new VersioneView())

        Calendar calendar = new Calendar()
        layout.addComponent calendar
    } //fine del metodo


    public static enum TipoMenu {
        standard, bottoni, tab
    } //fine della Enum interna

} // fine della classe
