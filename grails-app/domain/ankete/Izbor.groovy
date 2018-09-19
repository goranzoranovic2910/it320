package ankete

class Izbor {

    static constraints = {
    }

    static hasOne = [anketa:Anketa, odgovor:Odgovor, korisnik:Korisnik]
    Pitanje pitanje
    Date datum
    String ipAdress
}
