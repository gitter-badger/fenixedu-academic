package net.sourceforge.fenixedu.dataTransferObject.contacts;

import net.sourceforge.fenixedu.domain.Country;
import net.sourceforge.fenixedu.domain.DomainReference;
import net.sourceforge.fenixedu.domain.contacts.PhysicalAddress;
import net.sourceforge.fenixedu.domain.contacts.PhysicalAddressData;
import net.sourceforge.fenixedu.domain.organizationalStructure.Party;

public class PhysicalAddressBean extends PartyContactBean {

    private static final String CONTACT_NAME = "PhysicalAddress";

    private static final long serialVersionUID = 852136165195545415L;

    private String address;
    private String areaCode;
    private String areaOfAreaCode;
    private String area;
    private String parishOfResidence;
    private String districtSubdivisionOfResidence;
    private String districtOfResidence;

    private DomainReference<Country> countryOfResidence;

    public PhysicalAddressBean(Party party) {
	super(party);
    }

    public PhysicalAddressBean(PhysicalAddress physicalAddress) {
	super(physicalAddress);
	setValue(physicalAddress.getPresentationValue());
	setAddress(physicalAddress.getAddress());
	setAreaCode(physicalAddress.getAreaCode());
	setAreaOfAreaCode(physicalAddress.getAreaOfAreaCode());
	setArea(physicalAddress.getArea());
	setParishOfResidence(physicalAddress.getParishOfResidence());
	setDistrictSubdivisionOfResidence(physicalAddress.getDistrictSubdivisionOfResidence());
	setDistrictOfResidence(physicalAddress.getDistrictOfResidence());
	setCountryOfResidence(physicalAddress.getCountryOfResidence());
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getArea() {
	return area;
    }

    public void setArea(String area) {
	this.area = area;
    }

    public String getAreaCode() {
	return areaCode;
    }

    public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
    }

    public String getAreaOfAreaCode() {
	return areaOfAreaCode;
    }

    public void setAreaOfAreaCode(String areaOfAreaCode) {
	this.areaOfAreaCode = areaOfAreaCode;
    }

    public String getDistrictOfResidence() {
	return districtOfResidence;
    }

    public void setDistrictOfResidence(String districtOfResidence) {
	this.districtOfResidence = districtOfResidence;
    }

    public String getDistrictSubdivisionOfResidence() {
	return districtSubdivisionOfResidence;
    }

    public void setDistrictSubdivisionOfResidence(String districtSubdivisionOfResidence) {
	this.districtSubdivisionOfResidence = districtSubdivisionOfResidence;
    }

    public String getParishOfResidence() {
	return parishOfResidence;
    }

    public void setParishOfResidence(String parishOfResidence) {
	this.parishOfResidence = parishOfResidence;
    }

    public Country getCountryOfResidence() {
	return (this.countryOfResidence != null) ? this.countryOfResidence.getObject() : null;
    }

    public void setCountryOfResidence(Country countryOfResidence) {
	this.countryOfResidence = (countryOfResidence != null) ? new DomainReference<Country>(countryOfResidence) : null;
    }

    @Override
    public String getContactName() {
	return CONTACT_NAME;
    }

    @Override
    public void edit() {
	super.edit();
	((PhysicalAddress) getContact()).edit(new PhysicalAddressData(getAddress(), getAreaCode(), getAreaOfAreaCode(),
		getArea(), getParishOfResidence(), getDistrictSubdivisionOfResidence(), getDistrictOfResidence(),
		getCountryOfResidence()));
    }

    @Override
    public void createNewContact() {
	PhysicalAddress.createPhysicalAddress(getParty(), new PhysicalAddressData(getAddress(), getAreaCode(),
		getAreaOfAreaCode(), getArea(), getParishOfResidence(), getDistrictSubdivisionOfResidence(),
		getDistrictOfResidence(), getCountryOfResidence()), getType(), getDefaultContact());
    }
}
