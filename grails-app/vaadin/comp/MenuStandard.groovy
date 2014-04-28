package comp

import app.MyUI
import com.vaadin.ui.MenuBar

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 22-1-14
 * Time: 13:08
 */
class MenuStandard extends Menu {

    /**
     * Costruttore
     * Rimanda alla superclasse
     * Costruisce la barra standard dei menu
     */
    public MenuStandard() {
        super()
        layout.addComponent(creaMenuBar())
    } //fine del metodo costruttore

    /**
     * Costruisce la barra standard dei menu
     */
    private MenuBar creaMenuBar() {
        MenuBar aMenuBar = new MenuBar()
        aMenuBar.setHeight("40px")
//        aMenuBar.addStyleName('algosmenubar') @todo da studiare

        /**
         * Costruisce l'item e lo aggiunge alla barra di menu
         * Costruisce l'azione legata al click del menu (con una innerclass)
         */
        aMenuBar.addItem('Home', new MenuBar.Command() {
            void menuSelected(MenuBar.MenuItem selectedItem) {
                linkNormale(MyUI.MAINVIEW)
            } //fine del metodo
        }) //fine della inner class

        /**
         * Costruisce l'item e lo aggiunge alla barra di menu
         * Costruisce l'azione legata al click del menu (con una innerclass)
         */
        aMenuBar.addItem('Cliente', new MenuBar.Command() {
            void menuSelected(MenuBar.MenuItem selectedItem) {
                linkNormale(MyUI.CLIENTEVIEW)
            } //fine del metodo
        }) //fine della inner class

        /**
         * Costruisce l'item e lo aggiunge alla barra di menu
         * Costruisce l'azione legata al click del menu (con una innerclass)
         */
        aMenuBar.addItem('Help', new MenuBar.Command() {
            void menuSelected(MenuBar.MenuItem selectedItem) {
                linkNormale(MyUI.HELPVIEW)
            } //fine del metodo
        }) //fine della inner class

        /**
         * Costruisce l'item e lo aggiunge alla barra di menu
         * Costruisce l'azione legata al click del menu (con una innerclass)
         */
        aMenuBar.addItem('Logout', new MenuBar.Command() {
            void menuSelected(MenuBar.MenuItem selectedItem) {
                linkSessione()
            } //fine del metodo
        }) //fine della inner class


        return aMenuBar
    } //fine del metodo


} // fine della classe
