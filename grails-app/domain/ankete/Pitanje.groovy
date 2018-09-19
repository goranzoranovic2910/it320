package ankete

class Pitanje {

    static constraints = {
    }

    //static belongsTo = [anketa:Anketa]
    static hasMany = [odgovori:Odgovor]

    Anketa anketa

    String tekst

    List<Odgovor> odgovori
}
