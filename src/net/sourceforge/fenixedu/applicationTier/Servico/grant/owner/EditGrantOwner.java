package net.sourceforge.fenixedu.applicationTier.Servico.grant.owner;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.grant.owner.InfoGrantOwner;
import net.sourceforge.fenixedu.domain.Country;
import net.sourceforge.fenixedu.domain.DomainObject;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Role;
import net.sourceforge.fenixedu.domain.grant.owner.GrantOwner;
import net.sourceforge.fenixedu.domain.organizationalStructure.Party;
import net.sourceforge.fenixedu.domain.person.RoleType;

public class EditGrantOwner extends FenixService {

    private GrantOwner checkIfGrantOwnerExists(Integer grantOwnerNumber) {
	return GrantOwner.readGrantOwnerByNumber(grantOwnerNumber);
    }

    private GrantOwner prepareGrantOwner(GrantOwner grantOwner, Person person, InfoGrantOwner infoGrantOwner, Integer maxNumber) {

	if (infoGrantOwner.getGrantOwnerNumber() == null) {
	    // Generate the GrantOwner's number
	    int aux = maxNumber + 1;
	    Integer nextNumber = Integer.valueOf(aux);
	    grantOwner.setNumber(nextNumber);
	} else {
	    grantOwner.setNumber(infoGrantOwner.getGrantOwnerNumber());
	}
	grantOwner.setPerson(person);
	grantOwner.setCardCopyNumber(infoGrantOwner.getCardCopyNumber());
	grantOwner.setDateSendCGD(infoGrantOwner.getDateSendCGD());

	return grantOwner;
    }

    protected boolean isNew(DomainObject domainObject) {
	Integer objectId = domainObject.getIdInternal();
	return ((objectId == null) || objectId.equals(Integer.valueOf(0)));
    }

    public Integer run(InfoGrantOwner infoGrantOwner) throws FenixServiceException {
	Person person = null;
	GrantOwner grantOwner = null;
	Country country = null;

	if (infoGrantOwner.getInfoPersonEditor().getInfoPais().getIdInternal() != null) {
	    country = rootDomainObject.readCountryByOID(infoGrantOwner.getInfoPersonEditor().getInfoPais().getIdInternal());
	} else {
	    country = Country.readDefault();
	}

	// create or edit person information
	if (infoGrantOwner.getInfoPersonEditor().getIdInternal() == null) {
	    infoGrantOwner.getInfoPersonEditor().setUsername("X" + System.currentTimeMillis());
	    person = new Person(infoGrantOwner.getInfoPersonEditor(), country);
	} else {
	    person = (Person) rootDomainObject.readPartyByOID(infoGrantOwner.getInfoPersonEditor().getIdInternal());
	    person.edit(infoGrantOwner.getInfoPersonEditor(), country);
	}

	// verify if person is new
	if (infoGrantOwner.getInfoPersonEditor().getIdInternal() != null) {
	    if (infoGrantOwner.getGrantOwnerNumber() == null) {
		if (person.getGrantOwner() != null) {
		    infoGrantOwner.setGrantOwnerNumber(person.getGrantOwner().getNumber());
		}
	    }
	    grantOwner = checkIfGrantOwnerExists(infoGrantOwner.getGrantOwnerNumber());
	}

	// create or edit grantOwner information
	Integer maxNumber = null;
	if (grantOwner == null) {
	    maxNumber = GrantOwner.readMaxGrantOwnerNumber();
	    grantOwner = new GrantOwner();

	}

	grantOwner = prepareGrantOwner(grantOwner, person, infoGrantOwner, maxNumber);

	if (!person.hasRole(RoleType.PERSON)) {
	    person.addPersonRoles(Role.getRoleByRoleType(RoleType.PERSON));
	}
	person.addPersonRoles(Role.getRoleByRoleType(RoleType.GRANT_OWNER));

	return grantOwner.getIdInternal();
    }
}
