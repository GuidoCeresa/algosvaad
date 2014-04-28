package comp

import com.vaadin.ui.Label
import com.vaadin.ui.TabSheet

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 22-1-14
 * Time: 14:02
 */
class MenuTab extends Menu {

    /**
     * Costruttore
     * Rimanda alla superclasse
     * Costruisce una barra di menu con i tab
     */
    public MenuTab() {
        super()
        layout.addComponent(creaTabMenuBar())
    } //fine del metodo costruttore


    private static TabSheet creaTabMenuBar() {
        // Create an empty tab sheet.
        TabSheet tabsheet = new TabSheet()

        tabsheet.addTab(new Label("Contents of the first tab"),
                "First Tab", null)
        tabsheet.addTab(new Label("Contents of the second tab"),
                "Second Tab", null)
        tabsheet.addTab(new Label("Contents of the third tab"),
                "Third tab", null)

//        // Create a component to put in a tab and put
//        // some content in it.
//        VerticalLayout myTabRoot = new VerticalLayout()
//        myTabRoot.addComponent(new Label("Hello, I am a Tab!"))
//
//        // Add the component to the tab sheet as a new tab.
//        tabsheet.addTab(myTabRoot)
//
//        // Get the Tab holding the component and set its caption.
//        tabsheet.getTab(myTabRoot).setCaption("My Tab")

        return tabsheet
    } //fine del metodo

} // fine della classe
