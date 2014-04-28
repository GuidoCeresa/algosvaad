package views

import algosvaad.Cliente
import algosvaad.ClienteService
import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.BeanItem
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.grails.Grails
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.*
import comp.BarraBottoni

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 23-1-14
 * Time: 06:59
 */
class ClienteView extends AlgosView {

    ClienteService clienteService
    HorizontalLayout layoutBody
    private static String titoloTable = 'Elenco clienti'

//    private static String[] nomeVisibileCampiTable =
//            { 'Nome'; 'Cognome'; 'E-mail'; 'Cel' }
//    private static String[] idInternoCampiTable =
//            { 'nome'; 'cognome'; 'email'; 'cellulare' }

    private static List<String> nomeVisibileCampiForm =
            ['Nome', 'Cognome', 'Eta', 'E-mail', 'Tel', 'Cel', 'Indirizzo', 'Localita', 'Zip', 'Nazione', 'affidabile']
    private static List idInternoCampiForm =
            ['nome', 'cognome', 'eta', 'email', 'telefono', 'cellulare', 'indirizzo', 'localita', 'zip', 'nazione', 'affidabile']

    private IBackend backend
    private FieldGroup fieldGroup
    private Table table
    private FormLayout form

    private Button bottoneNew
    private Button bottoneDelete
    private Button bottoneCancel
    private Button bottoneSave
    private Button bottoneReset
    private Button bottoneHelp

    private static String tagCliente = 'Cliente: '
    private Label clienteSelezionato = new Label(tagCliente)

    /**
     * Costruttore
     */
    public ClienteView() {
        super()
        inizializza()
    } //fine del metodo costruttore

    /**
     * Costruttore
     */
    public inizializza() {
        //Crea un layoutBody orizzontale con le caratteristiche desiderate
        creaLayout()

        //Classe di appoggio per il binding
        backend = new Backend()

        //Crea una tavola, e la aggiunge alla vista
        creaTableAndButton()

        //Crea un form, e lo aggiunge alla vista
        creaFormAndButton()
    } //fine del metodo costruttore

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        Notification.show('Clienti')
    } //fine del metodo

    /**
     * Crea un layoutBody orizzontale con le caratteristiche desiderate
     */
    private void creaLayout() {
        if (layoutBody) {
            this.removeComponent(layoutBody)
        }// fine del blocco if
        layoutBody = new HorizontalLayout()
        layoutBody.setWidth("100%")
        layoutBody.setSpacing(true)
        addComponent(layoutBody)
    } //fine del metodo

    /**
     * Crea una tavola, e la aggiunge alla vista
     * Bottoni cancella e nuovo in basso
     */
    private void creaTableAndButton() {
        VerticalLayout tableLayout = new VerticalLayout()
        creaTable()
        tableLayout.addComponent(table)
        tableLayout.addComponent(buildTableControls())
        layoutBody.addComponent(tableLayout)
    } //fine del metodo

    /**
     * Crea una tavola
     */
    private void creaTable() {
        table = new Table(titoloTable)

        //parametri tavola
        table.setWidth("100%")
        table.setFooterVisible(true)
        table.setSelectable(true)
        table.setImmediate(true)

        table.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                editCliente(table.getValue())
                regolaBottoniTable(table.getValue())
            } //fine del metodo
        }) //fine della inner class

        updateTableData()
    } //fine del metodo


    private void updateTableData() {
        List<Cliente> persons = backend.getClienti()

        BeanItemContainer<Cliente> container = new BeanItemContainer<Cliente>(Cliente.class, persons)

        table.setContainerDataSource(container)

        table.setVisibleColumns('nome', 'cognome', 'email', 'cellulare', 'eta', 'affidabile')
        table.setColumnHeaders('Nome', 'Cognome', 'Email', 'Cel', 'Eta', 'affidabile')
//        table.sort(['nome'],(Boolean)[true])
    } //fine del metodo

    /**
     * Crea un form, e lo aggiunge alla vista
     * Bottoni salva e annulla in basso
     */
    private void creaFormAndButton() {
        VerticalLayout formLayout = new VerticalLayout()
        formLayout.setSpacing(true)
        formLayout.addComponent(new Label(''))
        formLayout.addComponent(new Label(''))
        formLayout.addComponent(clienteSelezionato)
        creaBindForm()
        formLayout.addComponent(form)
        formLayout.addComponent(buildFormControls())
        layoutBody.addComponent(formLayout)
    } //fine del metodo

    /**
     * Crea un form
     * Bottoni salva e annulla in basso
     */
    private void creaBindForm() {
        form = new FormLayout()

        // Create an instance of the bean
        Cliente bean = new Cliente()

        // Wrap it in a BeanItem
        BeanItem<Cliente> item = new BeanItem<Cliente>(bean)

        // Now create a binder that can also create the fields
        // using the default field factory
        fieldGroup = new FieldGroup(item)
        for (int k = 0; k < idInternoCampiForm.size(); k++) {
            form.addComponent(fieldGroup.buildAndBind(nomeVisibileCampiForm[k], idInternoCampiForm[k]))
        } // fine del ciclo for
    } //fine del metodo

    /**
     * Edit del singolo cliente
     */
    private void editCliente() {
        Cliente cliente = new Cliente()
        editCliente(cliente)
    } //fine del metodo

    /**
     * Edit del singolo cliente
     */
    private void editCliente(def cliente) {
        if (cliente == null) {
            cliente = new Cliente()
        }// fine del blocco if
        BeanItem item = new BeanItem(cliente)

        if (fieldGroup && item) {
            clienteSelezionato.setValue(tagCliente + cliente.toString())
            fieldGroup.setItemDataSource(item)
        }// fine del blocco if
    } //fine del metodo

    /**
     * Regolazione dei bottoni
     */
    private void regolaBottoniTable(def cliente) {
        if (cliente) {
            bottoneDelete.setEnabled(true)
            bottoneCancel.setEnabled(true)
        } else {
            bottoneDelete.setEnabled(false)
            bottoneCancel.setEnabled(false)
        }// fine del blocco if-else
    } //fine del metodo

    /**
     * Bottone specifico con azione
     */
    private void creaBottoneNew() {
        bottoneNew = new Button('Nuovo', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                editCliente()
                table.select(null)
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo

    /**
     * Bottone specifico con azione
     */
    private void creaBottoneDelete() {
        bottoneDelete = new Button('Elimina', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                backend.deleteCliente((Cliente) table.getValue())
                inizializza()
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo

    /**
     * Bottone specifico con azione
     */
    private void creaBottoneCancel() {
        bottoneCancel = new Button('Annulla selezione', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                table.select(null)
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo

    /**
     * Barra bottoni della lista
     */
    private Component buildTableControls() {
        creaBottoneNew()
        creaBottoneDelete()
        creaBottoneCancel()
        regolaBottoniTable(null)
        return new BarraBottoni([bottoneNew, bottoneDelete, bottoneCancel])
    } //fine del metodo

    /**
     * Regolazione dei bottoni
     */
    private void regolaBottoniForm(Cliente cliente) {
        if (true) {
            bottoneSave.setEnabled(true)
            bottoneReset.setEnabled(true)
        } else {
            bottoneSave.setEnabled(false)
            bottoneReset.setEnabled(false)
        }// fine del blocco if-else
    } //fine del metodo

    /**
     * Bottone specifico con azione
     */
    private void creaBottoneSave() {
        bottoneSave = new Button('Registra', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit()
                    backend.storeCliente(((BeanItem) fieldGroup.getItemDataSource()).getBean())
//                    updateTableData()
//                    editCliente()
                    inizializza()
                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace()
                }
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo

    /**
     * Bottone specifico con azione
     */
    private void creaBottoneReset() {
        bottoneReset = new Button('Reset', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                editCliente(((BeanItem<Cliente>) fieldGroup.getItemDataSource()).getBean())
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo

    /**
     * Barra bottoni del form
     */
    private Component buildFormControls() {
        creaBottoneSave()
        creaBottoneReset()
        regolaBottoniForm(null)
        return new BarraBottoni([bottoneSave, bottoneReset])
    } //fine del metodo

    /**
     * Recupera dal service una lista completa
     */
    private List<Cliente> getListaClienti() {
        List<Cliente> lista = null

        clienteService = Grails.get(ClienteService)

        if (clienteService) {
            lista = clienteService.getClienti()
        }// fine del blocco if

        return lista
    } //fine del metodo

    /**
     * Rimanda al service per la cancellazione
     */
    private boolean eliminaCliente(def cliente) {
        boolean eliminato = false
        boolean continua = true
        clienteService = Grails.get(ClienteService)

        if (clienteService) {
            eliminato = clienteService.elimina(cliente)
        } else {
            Notification.show('Attenzione', 'Manca un componente del programma', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        if (eliminato) {
            Notification.show('Eliminato', 'Il cliente è stato cancellato', Notification.Type.TRAY_NOTIFICATION)
        } else {
            Notification.show('Attenzione', 'Non sono riuscito a cancellare il cliente', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        return eliminato
    } //fine del metodo

    /**
     * Rimanda al service per la cancellazione
     */
    private boolean registraCliente(def cliente) {
        boolean registrato = false
        clienteService = Grails.get(ClienteService)

        if (clienteService) {
            registrato = clienteService.registra(cliente)
        } else {
            Notification.show('Attenzione', 'Manca un componente del programma', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        if (registrato) {
            Notification.show('Il cliente è stato registrato')
        } else {
            Notification.show('Attenzione', 'Non sono riuscito a registrare il cliente', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        return registrato
    } //fine del metodo

    public class Backend implements IBackend {
        public List<Cliente> getClienti() {
            return getListaClienti()
        } //fine del metodo

        public boolean storeCliente(def cliente) {
            return registraCliente(cliente)
        } //fine del metodo

        public boolean deleteCliente(Cliente cliente) {
            return eliminaCliente(cliente)
        } //fine del metodo
    } // fine della classe interna

    public interface IBackend {
        List<Cliente> getClienti()

        boolean storeCliente(def cliente)

        boolean deleteCliente(Cliente cliente)
    } //fine dell'interfaccia interna

} // fine della classe
