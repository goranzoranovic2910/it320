package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PitanjeServiceSpec extends Specification {

    PitanjeService pitanjeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Pitanje(...).save(flush: true, failOnError: true)
        //new Pitanje(...).save(flush: true, failOnError: true)
        //Pitanje pitanje = new Pitanje(...).save(flush: true, failOnError: true)
        //new Pitanje(...).save(flush: true, failOnError: true)
        //new Pitanje(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pitanje.id
    }

    void "test get"() {
        setupData()

        expect:
        pitanjeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Pitanje> pitanjeList = pitanjeService.list(max: 2, offset: 2)

        then:
        pitanjeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pitanjeService.count() == 5
    }

    void "test delete"() {
        Long pitanjeId = setupData()

        expect:
        pitanjeService.count() == 5

        when:
        pitanjeService.delete(pitanjeId)
        sessionFactory.currentSession.flush()

        then:
        pitanjeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Pitanje pitanje = new Pitanje()
        pitanjeService.save(pitanje)

        then:
        pitanje.id != null
    }
}
