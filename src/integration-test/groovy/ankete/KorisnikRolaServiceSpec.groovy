package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class KorisnikRolaServiceSpec extends Specification {

    KorisnikRolaService korisnikRolaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new KorisnikRola(...).save(flush: true, failOnError: true)
        //new KorisnikRola(...).save(flush: true, failOnError: true)
        //KorisnikRola korisnikRola = new KorisnikRola(...).save(flush: true, failOnError: true)
        //new KorisnikRola(...).save(flush: true, failOnError: true)
        //new KorisnikRola(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //korisnikRola.id
    }

    void "test get"() {
        setupData()

        expect:
        korisnikRolaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<KorisnikRola> korisnikRolaList = korisnikRolaService.list(max: 2, offset: 2)

        then:
        korisnikRolaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        korisnikRolaService.count() == 5
    }

    void "test delete"() {
        Long korisnikRolaId = setupData()

        expect:
        korisnikRolaService.count() == 5

        when:
        korisnikRolaService.delete(korisnikRolaId)
        sessionFactory.currentSession.flush()

        then:
        korisnikRolaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        KorisnikRola korisnikRola = new KorisnikRola()
        korisnikRolaService.save(korisnikRola)

        then:
        korisnikRola.id != null
    }
}
