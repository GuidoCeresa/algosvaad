package views

import comp.MenuBottoni
import comp.MenuStandard
import comp.MenuTab
import app.MyUI
import com.vaadin.annotations.Theme
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout

@Theme("runo")
abstract class AlgosView extends VerticalLayout implements View {

    /**
     * Costruttore senza parametri
     * Rimanda al costruttore con parametri usando il valore di default
     */
    public AlgosView() {
        this(true)
    } //fine del metodo costruttore

    /**
     * Costruttore  con parametri
     * Regola i parametri base della vista
     * Eventualmente aggiunge i menu
     */
    public AlgosView(boolean visualizzaMenu) {
        super()
        setSizeFull()
        setSpacing(true)
        setMargin(true)
        regolaMenu(visualizzaMenu)
    } //fine del metodo costruttore

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    } //fine del metodo

    /**
     * Eventuali menu
     */
    private void regolaMenu(boolean visualizzaMenu) {
        if (visualizzaMenu) {

            switch (MyUI.TIPOMENU) {
                case MyUI.TipoMenu.standard:
                    addComponent(new MenuStandard())
                    break
                case MyUI.TipoMenu.bottoni:
                    addComponent(new MenuBottoni())
                    break
                case MyUI.TipoMenu.tab:
                    addComponent(new MenuTab())
                    break
                default: // caso non definito
                    break
            } // fine del blocco switch
        }// fine del blocco if
    } //fine del metodo

} //fine della classe
