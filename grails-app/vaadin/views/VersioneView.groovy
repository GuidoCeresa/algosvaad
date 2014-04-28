package views

import algosvaad.Versione
import algosvaad.VersioneService
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.BeanItem
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.grails.Grails
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import comp.IBackend

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 26-1-14
 * Time: 20:19
 */
class VersioneView extends MasterDetailView {

    VersioneService versioneService

    /**
     * Costruttore
     */
    public VersioneView() {
        super()
        inizializzaDopo()
    } //fine del metodo costruttore

    /**
     * Regolazioni effettuate DOPO che la sottoclasse ha regolato alcuni parametri
     */
    protected inizializzaDopo() {
        titoloTable = 'Versione'
        tagDomain = 'Versione: '

        //Classe di appoggio per il binding
        backend = new Backend()
        recordSelezionato = new Label('Pippo')
        super.inizializzaDopo()
    } //fine del metodo costruttore

    /**
     * Crea un form
     */
    protected void creaForm() {
        // Create an instance of the bean
        Versione bean = new Versione()

        // Wrap it in a BeanItem
        BeanItem<Versione> item = new BeanItem<Versione>(bean)

        // Now create a binder that can also create the fields
        // using the default field factory
        fieldGroup = new FieldGroup(item)
        nomeVisibileCampiForm = ['Numero', 'Titolo', 'Descrizione','Giorno']
        idInternoCampiForm = ['numero', 'titolo', 'descrizione','giorno']

        super.creaForm()
    } //fine del metodo


    protected void updateTableData() {
        List records = backend.getRecords()

        BeanItemContainer<Versione> container = new BeanItemContainer<Versione>(Versione.class, records)
        table.setContainerDataSource(container)

        table.setVisibleColumns('titolo', 'descrizione','giorno')
        table.setColumnHeaders('Titolo', 'Descrizione','Giorno')
    } //fine del metodo

    /**
     * Edit del singolo record
     */
    protected void editRecord() {
        Versione record = new Versione()
        editRecord(record)
    } //fine del metodo

    /**
     * Edit del singolo record
     */
    protected void editRecord(def record) {
        if (record == null) {
            record = new Versione()
        }// fine del blocco if
        BeanItem item = new BeanItem(record)

        if (fieldGroup && item) {
            recordSelezionato.setValue(tagDomain + record.toString())
            fieldGroup.setItemDataSource(item)
        }// fine del blocco if
    } //fine del metodo

    /**
     * Recupera dal service una lista completa
     */
    private List getListaRecords() {
        List lista = null
        versioneService = Grails.get(VersioneService)

        if (versioneService) {
            lista = versioneService.getListaRecords()
        }// fine del blocco if

        return lista
    } //fine del metodo

    /**
     * Rimanda al service per la registrazione
     */
    private boolean registra(def record) {
        boolean registrato = false
        versioneService = Grails.get(VersioneService)

        if (versioneService) {
            registrato = versioneService.registra(record)
        } else {
            Notification.show('Attenzione', 'Manca un componente del programma', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        if (registrato) {
            Notification.show('Il record è stato registrato')
        } else {
            Notification.show('Attenzione', 'Non sono riuscito a registrare il record', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        return registrato
    } //fine del metodo

    /**
     * Rimanda al service per la cancellazione
     */
    private boolean elimina(def record) {
        boolean eliminato = false
        boolean continua = true
        versioneService = Grails.get(VersioneService)

        if (versioneService) {
            eliminato = versioneService.elimina(record)
        } else {
            Notification.show('Attenzione', 'Manca un componente del programma', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        if (eliminato) {
            Notification.show('Eliminato', 'Il record è stato cancellato', Notification.Type.TRAY_NOTIFICATION)
        } else {
            Notification.show('Attenzione', 'Non sono riuscito a cancellare il record', Notification.Type.WARNING_MESSAGE)
        }// fine del blocco if-else

        return eliminato
    } //fine del metodo


    public class Backend implements IBackend {
        public List getRecords() {
            return getListaRecords()
        } //fine del metodo

        public boolean storeRecord(def record) {
            return registra(record)
        } //fine del metodo

        public boolean deleteRecord(def record) {
            return elimina(record)
        } //fine del metodo
    } // fine della classe interna

} // fine della classe
