package ankete

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class KorisnikRola implements Serializable {

	private static final long serialVersionUID = 1

	Korisnik korisnik
	Rola rola

	@Override
	boolean equals(other) {
		if (other instanceof KorisnikRola) {
			other.korisnikId == korisnik?.id && other.rolaId == rola?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (korisnik) {
            hashCode = HashCodeHelper.updateHash(hashCode, korisnik.id)
		}
		if (rola) {
		    hashCode = HashCodeHelper.updateHash(hashCode, rola.id)
		}
		hashCode
	}

	static KorisnikRola get(long korisnikId, long rolaId) {
		criteriaFor(korisnikId, rolaId).get()
	}

	static boolean exists(long korisnikId, long rolaId) {
		criteriaFor(korisnikId, rolaId).count()
	}

	private static DetachedCriteria criteriaFor(long korisnikId, long rolaId) {
		KorisnikRola.where {
			korisnik == Korisnik.load(korisnikId) &&
			rola == Rola.load(rolaId)
		}
	}

	static KorisnikRola create(Korisnik korisnik, Rola rola, boolean flush = false) {
		def instance = new KorisnikRola(korisnik: korisnik, rola: rola)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Korisnik u, Rola r) {
		if (u != null && r != null) {
			KorisnikRola.where { korisnik == u && rola == r }.deleteAll()
		}
	}

	static int removeAll(Korisnik u) {
		u == null ? 0 : KorisnikRola.where { korisnik == u }.deleteAll() as int
	}

	static int removeAll(Rola r) {
		r == null ? 0 : KorisnikRola.where { rola == r }.deleteAll() as int
	}

	static constraints = {
	    korisnik nullable: false
		rola nullable: false, validator: { Rola r, KorisnikRola ur ->
			if (ur.korisnik?.id) {
				if (KorisnikRola.exists(ur.korisnik.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['korisnik', 'rola']
		version false
	}
}
