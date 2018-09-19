package ankete

import grails.gorm.services.Service

@Service(Anketa)
interface AnketaService {

    Anketa get(Serializable id)

    List<Anketa> list(Map args)

    Long count()

    void delete(Serializable id)

    Anketa save(Anketa anketa)

}