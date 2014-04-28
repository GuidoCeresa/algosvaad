import algosvaad.Cliente
import algosvaad.Versione

class BootStrap {

    def init = { servletContext ->

        if (true) {
            Versione versione
            versione = Versione.findByNumeroAndTitoloAndDescrizione(1, 'Init', 'Creazione iniziale')
            if (!versione) {
                versione = new Versione(numero: 1, titolo: 'Init', descrizione: 'Creazione iniziale')
                versione.save(flush: true)
                versione = null
            }// fine del blocco if

            Cliente cliente
            cliente = Cliente.findByNomeAndCognomeAndEta('Mario', 'Rossi', 45)
            if (!cliente) {
                cliente = new Cliente(nome: 'Mario', cognome: 'Rossi', eta: 45)
                cliente.save(flush: true)
                cliente = null
            }// fine del blocco if

            cliente = Cliente.findByNomeAndCognomeAndEta('Giulia', 'Barca', 38)
            if (!cliente) {
                cliente = new Cliente(nome: 'Giulia', cognome: 'Barca', eta: 38)
                cliente.save(flush: true)
                cliente = null
            }// fine del blocco if

            cliente = Cliente.findByNomeAndCognomeAndEta('Antonio', 'Rossetti', 61)
            if (!cliente) {
                cliente = new Cliente(nome: 'Antonio', cognome: 'Rossetti', eta: 61)
                cliente.save(flush: true)
                cliente = null
            }// fine del blocco if
        }// fine del blocco if

    } //fine del metodo

    def destroy = {
    }
}
