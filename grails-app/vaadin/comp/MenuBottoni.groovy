package comp

import app.MyUI
import com.vaadin.ui.Button

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 22-1-14
 * Time: 13:08
 */
class MenuBottoni extends Menu {

    /**
     * Costruttore
     * Rimanda alla superclasse
     * Costruisce ed aggiunge i bottoni
     */
    public MenuBottoni() {
        super()
        layout.addComponent(mainButton())
        layout.addComponent(clientButton())
        layout.addComponent(versionButton())
        layout.addComponent(helpButton())
        layout.addComponent(logoutButton())
    } //fine del metodo costruttore

    /**
     * Costruisce il bottone (senza aggiungerlo alla view)
     * Costruisce l'azione legata al click del bottone (con una innerclass)
     * @return button
     */
    private Button mainButton() {
        Button button = new Button('Main', new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(MyUI.MAINVIEW)
            } //fine del metodo
        }) //fine della inner class
        return button
    } //fine del metodo

    /**
     * Costruisce il bottone (senza aggiungerlo alla view)
     * Costruisce l'azione legata al click del bottone (con una innerclass)
     * @return button
     */
    private Button clientButton() {
        Button button = new Button('Clienti', new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(MyUI.CLIENTEVIEW)
            } //fine del metodo
        }) //fine della inner class
        return button
    } //fine del metodo

    /**
     * Costruisce il bottone (senza aggiungerlo alla view)
     * Costruisce l'azione legata al click del bottone (con una innerclass)
     * @return button
     */
    private Button versionButton() {
        Button button = new Button('Versione', new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(MyUI.VERSIONEVIEW)
            } //fine del metodo
        }) //fine della inner class
        return button
    } //fine del metodo

    /**
     * Costruisce il bottone (senza aggiungerlo alla view)
     * Costruisce l'azione legata al click del bottone (con una innerclass)
     * @return button
     */
    private Button helpButton() {
        Button button = new Button('Help', new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(MyUI.HELPVIEW)
            } //fine del metodo
        }) //fine della inner class
        return button
    } //fine del metodo

    /**
     * Costruisce il bottone (senza aggiungerlo alla view)
     * Costruisce l'azione legata al click del bottone (con una innerclass)
     * @return button
     */
    private Button logoutButton() {
        Button button = new Button('Logout', new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getSession().close()
                getUI().getPage().setLocation(getLogoutPath())
            } //fine del metodo
        }) //fine della inner class
        return button
    } //fine del metodo

} // fine della classe
