package ankete

import grails.gorm.services.Service

@Service(Izbor)
interface IzborService {

    Izbor get(Serializable id)

    List<Izbor> list(Map args)

    Long count()

    void delete(Serializable id)

    Izbor save(Izbor izbor)

}