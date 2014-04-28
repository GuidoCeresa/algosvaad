package comp

import app.MyUI
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.MenuBar.Command
import com.vaadin.ui.MenuBar.MenuItem

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 18-1-14
 * Time: 12:18
 */
public abstract class Menu extends CustomComponent {

    protected HorizontalLayout layout

    /**
     * Costruttore
     */
    public Menu() {
        layout = new HorizontalLayout()
        layout.setSizeUndefined()
        layout.setSpacing(true)
        setCompositionRoot(layout)
    } //fine del metodo costruttore

    /**
     * Si sposta alla view indicata
     *
     * @param indirizzoFrammento della view
     */
    protected void linkNormale(String indirizzoFrammento) {
        getUI().getNavigator().navigateTo(indirizzoFrammento)
    } //fine del metodo


    private String getLogoutPath() {
        return getUI().getPage().getLocation().getPath()
    } //fine del metodo

    /**
     * Si sposta alla prima view e chiude la sessione
     */
    protected void linkSessione() {
        getUI().getSession().close()
        getUI().getPage().setLocation(getLogoutPath())
    } //fine del metodo

    /**
     * @deprecated
     */
    class Ritorno implements Command {
        void menuSelected(MenuItem selectedItem) {
            getUI().getNavigator().navigateTo(MyUI.MAINVIEW)
        } //fine del metodo
    } // fine della classe interna
} // fine della classe
