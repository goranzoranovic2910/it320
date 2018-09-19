package ankete

class Odgovor {

    static constraints = {
    }

    static belongsTo = [pitanje: Pitanje]
    static hasMany=[izbori:Izbor]
    String tekst
    Boolean checked=true

}
