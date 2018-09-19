package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AnketaServiceSpec extends Specification {

    AnketaService anketaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Anketa(...).save(flush: true, failOnError: true)
        //new Anketa(...).save(flush: true, failOnError: true)
        //Anketa anketa = new Anketa(...).save(flush: true, failOnError: true)
        //new Anketa(...).save(flush: true, failOnError: true)
        //new Anketa(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //anketa.id
    }

    void "test get"() {
        setupData()

        expect:
        anketaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Anketa> anketaList = anketaService.list(max: 2, offset: 2)

        then:
        anketaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        anketaService.count() == 5
    }

    void "test delete"() {
        Long anketaId = setupData()

        expect:
        anketaService.count() == 5

        when:
        anketaService.delete(anketaId)
        sessionFactory.currentSession.flush()

        then:
        anketaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Anketa anketa = new Anketa()
        anketaService.save(anketa)

        then:
        anketa.id != null
    }
}
