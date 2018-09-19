package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RolaServiceSpec extends Specification {

    RolaService rolaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Rola(...).save(flush: true, failOnError: true)
        //new Rola(...).save(flush: true, failOnError: true)
        //Rola rola = new Rola(...).save(flush: true, failOnError: true)
        //new Rola(...).save(flush: true, failOnError: true)
        //new Rola(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //rola.id
    }

    void "test get"() {
        setupData()

        expect:
        rolaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Rola> rolaList = rolaService.list(max: 2, offset: 2)

        then:
        rolaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        rolaService.count() == 5
    }

    void "test delete"() {
        Long rolaId = setupData()

        expect:
        rolaService.count() == 5

        when:
        rolaService.delete(rolaId)
        sessionFactory.currentSession.flush()

        then:
        rolaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Rola rola = new Rola()
        rolaService.save(rola)

        then:
        rola.id != null
    }
}
