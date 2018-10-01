package ankete

import grails.gorm.services.Service

@Service(KorisnikRola)
interface KorisnikRolaService {

    KorisnikRola get(Serializable id)

    List<KorisnikRola> list(Map args)

    Long count()

    void delete(Serializable id)

    KorisnikRola save(KorisnikRola korisnikRola)

}