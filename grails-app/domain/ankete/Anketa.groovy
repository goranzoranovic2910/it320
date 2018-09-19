package ankete

class Anketa {

    static constraints = {
    }

    static hasMany = [pitanja: Pitanje, izbori: Izbor]

    String naslov
    boolean aktivna
    boolean anonimna 

}
