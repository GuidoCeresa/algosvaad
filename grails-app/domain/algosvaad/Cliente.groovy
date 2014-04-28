package algosvaad
// Here is a bean (or more exactly a POJO)
class Cliente {

    String nome = ''
    String cognome = ''
    String email = ''
    String telefono = ''
    String cellulare = ''
    String indirizzo = ''
    String localita = ''
    String zip = ''
    String nazione = ''
    int eta = 0
    boolean affidabile

    static constraints = {
    }

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return cognome + ' ' + nome
    } // end of toString

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getCognome() {
        return cognome
    }

    void setCognome(String cognome) {
        this.cognome = cognome
    }

    int getEta() {
        return eta
    }

    void setEta(int eta) {
        this.eta = eta
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getTelefono() {
        return telefono
    }

    void setTelefono(String telefono) {
        this.telefono = telefono
    }

    String getCellulare() {
        return cellulare
    }

    void setCellulare(String cellulare) {
        this.cellulare = cellulare
    }

    String getNazione() {
        return nazione
    }

    void setNazione(String nazione) {
        this.nazione = nazione
    }

    String getIndirizzo() {
        return indirizzo
    }

    void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo
    }

    String getZip() {
        return zip
    }

    void setZip(String zip) {
        this.zip = zip
    }

    String getLocalita() {
        return localita
    }

    void setLocalita(String localita) {
        this.localita = localita
    }

    boolean getAffidabile() {
        return affidabile
    }

    void setAffidabile(boolean affidabile) {
        this.affidabile = affidabile
    }
}
