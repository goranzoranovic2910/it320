package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OdgovorServiceSpec extends Specification {

    OdgovorService odgovorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Odgovor(...).save(flush: true, failOnError: true)
        //new Odgovor(...).save(flush: true, failOnError: true)
        //Odgovor odgovor = new Odgovor(...).save(flush: true, failOnError: true)
        //new Odgovor(...).save(flush: true, failOnError: true)
        //new Odgovor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //odgovor.id
    }

    void "test get"() {
        setupData()

        expect:
        odgovorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Odgovor> odgovorList = odgovorService.list(max: 2, offset: 2)

        then:
        odgovorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        odgovorService.count() == 5
    }

    void "test delete"() {
        Long odgovorId = setupData()

        expect:
        odgovorService.count() == 5

        when:
        odgovorService.delete(odgovorId)
        sessionFactory.currentSession.flush()

        then:
        odgovorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Odgovor odgovor = new Odgovor()
        odgovorService.save(odgovor)

        then:
        odgovor.id != null
    }
}
