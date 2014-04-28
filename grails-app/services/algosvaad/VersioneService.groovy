package algosvaad

import grails.transaction.Transactional

@Transactional
class VersioneService extends AlgosService{

    public List getListaRecords() {
        return Versione.list()
    } //fine del metodo

    public boolean registra(def record) {
        boolean registrato = false

        if (record) {
            record.save(flush: true)
            registrato = true
        }// fine del blocco if

        return registrato
    } //fine del metodo

} // fine della classe
