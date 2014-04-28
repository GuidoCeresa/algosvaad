package algosvaad

import grails.transaction.Transactional

@Transactional
class ClienteService {

    public List<Cliente> getClienti() {
        return Cliente.list()
    } //fine del metodo

    public boolean elimina(Cliente cliente) {
        boolean eliminato = false

        if (cliente) {
            cliente.delete(flush: true)
            eliminato = true
        }// fine del blocco if

        return eliminato
    } //fine del metodo

    public boolean registra(def cliente) {
        boolean registrato = false

        if (cliente) {
            cliente.save(flush: true)
            registrato = true
        }// fine del blocco if

        return registrato
    } //fine del metodo

} // fine della classe
