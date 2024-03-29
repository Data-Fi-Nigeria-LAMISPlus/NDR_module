//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2024.02.02 at 12:49:02 PM WAT
//


package org.lamisplus.modules.ndr.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ClientVerificationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ClientVerificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ClientVerification" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="YES"/&gt;
 *               &lt;enumeration value="NO"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PickupByProxy" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DuplicatedDemographic" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NoRecapture" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BatchPickupDates" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LastVisitIsOver18M" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ARTStartPickupDate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NoInitBiometric" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IncompleteVisitData" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RepeatEncounterNoPrint" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LongIntervalsARVPickup" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SameSexDOBARTStartDate" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="TRUE"/&gt;
 *               &lt;enumeration value="FALSE"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OtherSpecifyForCV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CT1STDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="FirstStatus" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="VerificationOngoing"/&gt;
 *               &lt;enumeration value="RecordDiscontinued"/&gt;
 *               &lt;enumeration value="RecordVerified"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FirstOutcome" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pending"/&gt;
 *               &lt;enumeration value="Valid"/&gt;
 *               &lt;enumeration value="Invalid"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CT2ndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="SecondStatus" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="VerificationOngoing"/&gt;
 *               &lt;enumeration value="RecordDiscontinued"/&gt;
 *               &lt;enumeration value="RecordVerified"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SecondOutcome" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pending"/&gt;
 *               &lt;enumeration value="Valid"/&gt;
 *               &lt;enumeration value="Invalid"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CTLastDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="LastStatus" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="VerificationOngoing"/&gt;
 *               &lt;enumeration value="RecordDiscontinued"/&gt;
 *               &lt;enumeration value="RecordVerified"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="LastOutcome" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pending"/&gt;
 *               &lt;enumeration value="Valid"/&gt;
 *               &lt;enumeration value="Invalid"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientVerificationType", propOrder = {
        "clientVerification",
        "pickupByProxy",
        "duplicatedDemographic",
        "noRecapture",
        "batchPickupDates",
        "lastVisitIsOver18M",
        "artStartPickupDate",
        "noInitBiometric",
        "incompleteVisitData",
        "repeatEncounterNoPrint",
        "longIntervalsARVPickup",
        "sameSexDOBARTStartDate",
        "otherSpecifyForCV",
        "ct1STDate",
        "firstStatus",
        "firstOutcome",
        "ct2NdDate",
        "secondStatus",
        "secondOutcome",
        "ctLastDate",
        "lastStatus",
        "lastOutcome"
})
public class ClientVerificationType {

    @XmlElement(name = "ClientVerification")
    protected String clientVerification;
    @XmlElement(name = "PickupByProxy")
    protected String pickupByProxy;
    @XmlElement(name = "DuplicatedDemographic")
    protected String duplicatedDemographic;
    @XmlElement(name = "NoRecapture")
    protected String noRecapture;
    @XmlElement(name = "BatchPickupDates")
    protected String batchPickupDates;
    @XmlElement(name = "LastVisitIsOver18M")
    protected String lastVisitIsOver18M;
    @XmlElement(name = "ARTStartPickupDate")
    protected String artStartPickupDate;
    @XmlElement(name = "NoInitBiometric")
    protected String noInitBiometric;
    @XmlElement(name = "IncompleteVisitData")
    protected String incompleteVisitData;
    @XmlElement(name = "RepeatEncounterNoPrint")
    protected String repeatEncounterNoPrint;
    @XmlElement(name = "LongIntervalsARVPickup")
    protected String longIntervalsARVPickup;
    @XmlElement(name = "SameSexDOBARTStartDate")
    protected String sameSexDOBARTStartDate;
    @XmlElement(name = "OtherSpecifyForCV")
    protected String otherSpecifyForCV;
    @XmlElement(name = "CT1STDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ct1STDate;
    @XmlElement(name = "FirstStatus")
    protected String firstStatus;
    @XmlElement(name = "FirstOutcome")
    protected String firstOutcome;
    @XmlElement(name = "CT2ndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ct2NdDate;
    @XmlElement(name = "SecondStatus")
    protected String secondStatus;
    @XmlElement(name = "SecondOutcome")
    protected String secondOutcome;
    @XmlElement(name = "CTLastDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ctLastDate;
    @XmlElement(name = "LastStatus")
    protected String lastStatus;
    @XmlElement(name = "LastOutcome")
    protected String lastOutcome;

    /**
     * Gets the value of the clientVerification property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClientVerification() {
        return clientVerification;
    }

    /**
     * Sets the value of the clientVerification property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClientVerification(String value) {
        this.clientVerification = value;
    }

    /**
     * Gets the value of the pickupByProxy property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPickupByProxy() {
        return pickupByProxy;
    }

    /**
     * Sets the value of the pickupByProxy property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPickupByProxy(String value) {
        this.pickupByProxy = value;
    }

    /**
     * Gets the value of the duplicatedDemographic property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDuplicatedDemographic() {
        return duplicatedDemographic;
    }

    /**
     * Sets the value of the duplicatedDemographic property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDuplicatedDemographic(String value) {
        this.duplicatedDemographic = value;
    }

    /**
     * Gets the value of the noRecapture property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNoRecapture() {
        return noRecapture;
    }

    /**
     * Sets the value of the noRecapture property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNoRecapture(String value) {
        this.noRecapture = value;
    }

    /**
     * Gets the value of the batchPickupDates property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBatchPickupDates() {
        return batchPickupDates;
    }

    /**
     * Sets the value of the batchPickupDates property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBatchPickupDates(String value) {
        this.batchPickupDates = value;
    }

    /**
     * Gets the value of the lastVisitIsOver18M property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastVisitIsOver18M() {
        return lastVisitIsOver18M;
    }

    /**
     * Sets the value of the lastVisitIsOver18M property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastVisitIsOver18M(String value) {
        this.lastVisitIsOver18M = value;
    }

    /**
     * Gets the value of the artStartPickupDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getARTStartPickupDate() {
        return artStartPickupDate;
    }

    /**
     * Sets the value of the artStartPickupDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setARTStartPickupDate(String value) {
        this.artStartPickupDate = value;
    }

    /**
     * Gets the value of the noInitBiometric property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNoInitBiometric() {
        return noInitBiometric;
    }

    /**
     * Sets the value of the noInitBiometric property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNoInitBiometric(String value) {
        this.noInitBiometric = value;
    }

    /**
     * Gets the value of the incompleteVisitData property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIncompleteVisitData() {
        return incompleteVisitData;
    }

    /**
     * Sets the value of the incompleteVisitData property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIncompleteVisitData(String value) {
        this.incompleteVisitData = value;
    }

    /**
     * Gets the value of the repeatEncounterNoPrint property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRepeatEncounterNoPrint() {
        return repeatEncounterNoPrint;
    }

    /**
     * Sets the value of the repeatEncounterNoPrint property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRepeatEncounterNoPrint(String value) {
        this.repeatEncounterNoPrint = value;
    }

    /**
     * Gets the value of the longIntervalsARVPickup property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLongIntervalsARVPickup() {
        return longIntervalsARVPickup;
    }

    /**
     * Sets the value of the longIntervalsARVPickup property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLongIntervalsARVPickup(String value) {
        this.longIntervalsARVPickup = value;
    }

    /**
     * Gets the value of the sameSexDOBARTStartDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSameSexDOBARTStartDate() {
        return sameSexDOBARTStartDate;
    }

    /**
     * Sets the value of the sameSexDOBARTStartDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSameSexDOBARTStartDate(String value) {
        this.sameSexDOBARTStartDate = value;
    }

    /**
     * Gets the value of the otherSpecifyForCV property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOtherSpecifyForCV() {
        return otherSpecifyForCV;
    }

    /**
     * Sets the value of the otherSpecifyForCV property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOtherSpecifyForCV(String value) {
        this.otherSpecifyForCV = value;
    }

    /**
     * Gets the value of the ct1STDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCT1STDate() {
        return ct1STDate;
    }

    /**
     * Sets the value of the ct1STDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCT1STDate(XMLGregorianCalendar value) {
        this.ct1STDate = value;
    }

    /**
     * Gets the value of the firstStatus property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFirstStatus() {
        return firstStatus;
    }

    /**
     * Sets the value of the firstStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstStatus(String value) {
        this.firstStatus = value;
    }

    /**
     * Gets the value of the firstOutcome property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFirstOutcome() {
        return firstOutcome;
    }

    /**
     * Sets the value of the firstOutcome property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstOutcome(String value) {
        this.firstOutcome = value;
    }

    /**
     * Gets the value of the ct2NdDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCT2NdDate() {
        return ct2NdDate;
    }

    /**
     * Sets the value of the ct2NdDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCT2NdDate(XMLGregorianCalendar value) {
        this.ct2NdDate = value;
    }

    /**
     * Gets the value of the secondStatus property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSecondStatus() {
        return secondStatus;
    }

    /**
     * Sets the value of the secondStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSecondStatus(String value) {
        this.secondStatus = value;
    }

    /**
     * Gets the value of the secondOutcome property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSecondOutcome() {
        return secondOutcome;
    }

    /**
     * Sets the value of the secondOutcome property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSecondOutcome(String value) {
        this.secondOutcome = value;
    }

    /**
     * Gets the value of the ctLastDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCTLastDate() {
        return ctLastDate;
    }

    /**
     * Sets the value of the ctLastDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCTLastDate(XMLGregorianCalendar value) {
        this.ctLastDate = value;
    }

    /**
     * Gets the value of the lastStatus property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastStatus() {
        return lastStatus;
    }

    /**
     * Sets the value of the lastStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastStatus(String value) {
        this.lastStatus = value;
    }

    /**
     * Gets the value of the lastOutcome property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastOutcome() {
        return lastOutcome;
    }

    /**
     * Sets the value of the lastOutcome property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastOutcome(String value) {
        this.lastOutcome = value;
    }

}
