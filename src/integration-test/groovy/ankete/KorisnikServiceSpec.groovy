package ankete

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class KorisnikServiceSpec extends Specification {

    KorisnikService korisnikService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Korisnik(...).save(flush: true, failOnError: true)
        //new Korisnik(...).save(flush: true, failOnError: true)
        //Korisnik korisnik = new Korisnik(...).save(flush: true, failOnError: true)
        //new Korisnik(...).save(flush: true, failOnError: true)
        //new Korisnik(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //korisnik.id
    }

    void "test get"() {
        setupData()

        expect:
        korisnikService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Korisnik> korisnikList = korisnikService.list(max: 2, offset: 2)

        then:
        korisnikList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        korisnikService.count() == 5
    }

    void "test delete"() {
        Long korisnikId = setupData()

        expect:
        korisnikService.count() == 5

        when:
        korisnikService.delete(korisnikId)
        sessionFactory.currentSession.flush()

        then:
        korisnikService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Korisnik korisnik = new Korisnik()
        korisnikService.save(korisnik)

        then:
        korisnik.id != null
    }
}
