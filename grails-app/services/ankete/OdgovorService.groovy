package ankete

import grails.gorm.services.Service

@Service(Odgovor)
interface OdgovorService {

    Odgovor get(Serializable id)

    List<Odgovor> list(Map args)

    Long count()

    void delete(Serializable id)

    Odgovor save(Odgovor odgovor)

}