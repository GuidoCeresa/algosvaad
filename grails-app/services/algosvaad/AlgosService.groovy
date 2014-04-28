package algosvaad

import grails.transaction.Transactional

@Transactional
abstract class AlgosService {

    public List getListaRecords() {
        return null
    } //fine del metodo

    public boolean elimina(def record) {
        boolean eliminato = false

        if (record && record) {
            record.delete(flush: true)
            eliminato = true
        }// fine del blocco if

        return eliminato
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
