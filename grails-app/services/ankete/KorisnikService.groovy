package ankete

import grails.gorm.services.Service

@Service(Korisnik)
interface KorisnikService {

    Korisnik get(Serializable id)

    List<Korisnik> list(Map args)

    Long count()

    void delete(Serializable id)

    Korisnik save(Korisnik korisnik)

}