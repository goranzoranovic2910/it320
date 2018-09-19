package ankete

import grails.gorm.services.Service

@Service(Rola)
interface RolaService {

    Rola get(Serializable id)

    List<Rola> list(Map args)

    Long count()

    void delete(Serializable id)

    Rola save(Rola rola)

}