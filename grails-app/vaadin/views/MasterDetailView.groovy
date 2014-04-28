package views

import com.vaadin.data.Property
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.util.BeanItem
import com.vaadin.ui.*
import comp.BarraBottoni
import comp.IBackend
/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 26-1-14
 * Time: 17:52
 */
/**
 * Vista Master-Detail (lista/scheda affiancate)
 */
abstract class MasterDetailView extends AlgosView {

    protected HorizontalLayout layoutBody
    protected static String titoloTable

    protected static List<String> nomeVisibileCampiForm
    protected static List idInternoCampiForm

    protected IBackend backend
    protected FieldGroup fieldGroup
    protected Table table
    protected FormLayout form

    protected Button bottoneNew
    protected Button bottoneDelete
    protected Button bottoneCancel
    protected Button bottoneSave
    protected Button bottoneReset
    protected Button bottoneHelp

    protected static String tagDomain
    protected Label recordSelezionato

    /**
     * Costruttore
     */
    public MasterDetailView() {
        super()

        //Regolazioni effettuate SUBITO (init)
        inizializzaPrima()
    } //fine del metodo costruttore

    /**
     * Regolazioni effettuate SUBITO (init)
     */
    protected inizializzaPrima() {
    } //fine del metodo costruttore

    /**
     * Regolazioni effettuate DOPO che la sottoclasse ha regolato alcuni parametri
     */
    protected inizializzaDopo() {
        //Crea un layoutBody orizzontale con le caratteristiche desiderate
        creaLayout()

        //Crea una tavola comprensiva di bottoni e la aggiunge alla vista
        creaTableAndButton()

        //Crea un form, e lo aggiunge alla vista
        creaFormAndButton()
    } //fine del metodo costruttore

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
     * Crea una tavola comprensiva di bottoni e la aggiunge alla vista
     * Bottoni cancella e nuovo in basso
     */
    private void creaTableAndButton() {
        VerticalLayout tableLayout = new VerticalLayout()
        creaTable()
        tableLayout.addComponent(table)
        tableLayout.addComponent(creaBottoniTable())
        layoutBody.addComponent(tableLayout)
    } //fine del metodo

    /**
     * Crea un form comprensivo di bottoni e lo aggiunge alla vista
     * Bottoni salva e annulla in basso
     */
    private void creaFormAndButton() {
        VerticalLayout formLayout = new VerticalLayout()
        formLayout.setSpacing(true)
        formLayout.addComponent(new Label(''))
        formLayout.addComponent(new Label(''))
        formLayout.addComponent(recordSelezionato)
        creaForm()
        formLayout.addComponent(form)
        formLayout.addComponent(creaBottoniForm())
        layoutBody.addComponent(formLayout)
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
                editRecord(table.getValue())
                regolaBottoniTable(table.getValue())
            } //fine del metodo
        }) //fine della inner class
        updateTableData()
    } //fine del metodo

    /**
     * Crea un form
     */
    protected void creaForm() {
        form = new FormLayout()

        // Now create a binder that can also create the fields
        // using the default field factory
        for (int k = 0; k < idInternoCampiForm.size(); k++) {
            form.addComponent(fieldGroup.buildAndBind(nomeVisibileCampiForm[k], idInternoCampiForm[k]))
        } // fine del ciclo for
    } //fine del metodo

    protected void updateTableData() {
    } //fine del metodo

    /**
     * Barra bottoni della lista
     */
    private Component creaBottoniTable() {
        creaBottoneNew()
        creaBottoneDelete()
        creaBottoneCancel()
        regolaBottoniTable(null)
        return new BarraBottoni([bottoneNew, bottoneDelete, bottoneCancel])
    } //fine del metodo

    /**
     * Barra bottoni del form
     */
    private Component creaBottoniForm() {
        creaBottoneSave()
        creaBottoneReset()
        regolaBottoniForm(null)
        return new BarraBottoni([bottoneSave, bottoneReset])
    } //fine del metodo


    /**
     * Bottone specifico con azione
     */
    private void creaBottoneNew() {
        bottoneNew = new Button('Nuovo', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                editRecord()
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
                backend.deleteRecord(table.getValue())
                inizializzaDopo()
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
     * Bottone specifico con azione
     */
    private void creaBottoneSave() {
        bottoneSave = new Button('Registra', new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    fieldGroup.commit()
                    backend.storeRecord(((BeanItem) fieldGroup.getItemDataSource()).getBean())
                    inizializzaDopo()
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
                editRecord(((BeanItem) fieldGroup.getItemDataSource()).getBean())
            } //fine del metodo
        }) //fine della inner class
    } //fine del metodo


    /**
     * Regolazione dei bottoni
     */
    private void regolaBottoniTable(def record) {
        if (record) {
            bottoneDelete.setEnabled(true)
            bottoneCancel.setEnabled(true)
        } else {
            bottoneDelete.setEnabled(false)
            bottoneCancel.setEnabled(false)
        }// fine del blocco if-else
    } //fine del metodo

    /**
     * Regolazione dei bottoni
     */
    private void regolaBottoniForm(def record) {
        if (true) {
            bottoneSave.setEnabled(true)
            bottoneReset.setEnabled(true)
        } else {
            bottoneSave.setEnabled(false)
            bottoneReset.setEnabled(false)
        }// fine del blocco if-else
    } //fine del metodo


    /**
     * Edit del singolo record
     */
    protected void editRecord() {
    } //fine del metodo

    /**
     * Edit del singolo record
     */
    protected void editRecord(def record) {
    } //fine del metodo


} // fine della classe
