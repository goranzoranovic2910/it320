package ankete

import grails.gorm.services.Service

@Service(Pitanje)
interface PitanjeService {

    Pitanje get(Serializable id)

    List<Pitanje> list(Map args)

    Long count()

    void delete(Serializable id)

    Pitanje save(Pitanje pitanje)

}