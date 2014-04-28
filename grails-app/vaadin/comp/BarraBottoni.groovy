package comp

import com.vaadin.ui.Button
import com.vaadin.ui.HorizontalLayout

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 25-1-14
 * Time: 08:41
 */
class BarraBottoni extends HorizontalLayout {

    /**
     * Costruttore
     */
    public BarraBottoni() {
        setMargin(true)
        setSpacing(true)
    } //fine del metodo costruttore

    /**
     * Costruttore con parametri
     */
    public BarraBottoni(List<Button> bottoni) {
        this()

        bottoni?.each {
            addComponent(it)
        } // fine del ciclo each

    } //fine del metodo costruttore


} // fine della classe
