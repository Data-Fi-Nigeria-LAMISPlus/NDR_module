//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.26 at 09:32:03 AM WAT 
//


package org.lamisplus.modules.ndr.schema.recapture;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FingerPrintType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FingerPrintType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateCaptured" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="visitId" type="{}StringType"/>
 *         &lt;element name="visitDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="captureCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rightHand" type="{}rightHandType"/>
 *         &lt;element name="leftHand" type="{}leftHandType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FingerPrintType", propOrder = {
    "dateCaptured",
    "visitId",
    "visitDate",
    "captureCount",
    "rightHand",
    "leftHand"
})
@ToString
public class FingerPrintType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateCaptured;
    @XmlElement(required = true)
    protected String visitId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar visitDate;
    protected int captureCount;
    @XmlElement(required = true)
    protected RightHandType rightHand;
    @XmlElement(required = true)
    protected LeftHandType leftHand;

    /**
     * Gets the value of the dateCaptured property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCaptured() {
        return dateCaptured;
    }

    /**
     * Sets the value of the dateCaptured property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCaptured(XMLGregorianCalendar value) {
        this.dateCaptured = value;
    }

    /**
     * Gets the value of the visitId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitId() {
        return visitId;
    }

    /**
     * Sets the value of the visitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitId(String value) {
        this.visitId = value;
    }

    /**
     * Gets the value of the visitDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVisitDate() {
        return visitDate;
    }

    /**
     * Sets the value of the visitDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVisitDate(XMLGregorianCalendar value) {
        this.visitDate = value;
    }

    /**
     * Gets the value of the captureCount property.
     * 
     */
    public int getCaptureCount() {
        return captureCount;
    }

    /**
     * Sets the value of the captureCount property.
     * 
     */
    public void setCaptureCount(int value) {
        this.captureCount = value;
    }

    /**
     * Gets the value of the rightHand property.
     * 
     * @return
     *     possible object is
     *     {@link RightHandType }
     *     
     */
    public RightHandType getRightHand() {
        return rightHand;
    }

    /**
     * Sets the value of the rightHand property.
     * 
     * @param value
     *     allowed object is
     *     {@link RightHandType }
     *     
     */
    public void setRightHand(RightHandType value) {
        this.rightHand = value;
    }

    /**
     * Gets the value of the leftHand property.
     * 
     * @return
     *     possible object is
     *     {@link LeftHandType }
     *     
     */
    public LeftHandType getLeftHand() {
        return leftHand;
    }

    /**
     * Sets the value of the leftHand property.
     * 
     * @param value
     *     allowed object is
     *     {@link LeftHandType }
     *     
     */
    public void setLeftHand(LeftHandType value) {
        this.leftHand = value;
    }

}
