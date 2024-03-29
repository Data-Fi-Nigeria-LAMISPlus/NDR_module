//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.11.09 at 01:57:11 PM WAT 
//


package org.lamisplus.modules.ndr.schema.redacted;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for MessageHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageHeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MessageCreationDateTime" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="MessageUniqueID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XmlType"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="REDACTED"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="MessageVersion" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="MessageSendingOrganisation" type="{}MessageSendingOrganisationType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeaderType", propOrder = {
    "messageCreationDateTime",
    "messageUniqueID",
    "xmlType",
    "messageVersion",
    "messageSendingOrganisation"
})
public class MessageHeaderType {

    @XmlElement(name = "MessageCreationDateTime", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar messageCreationDateTime;
    @XmlElement(name = "MessageUniqueID", required = true)
    protected String messageUniqueID;
    @XmlElement(name = "XmlType", required = true)
    protected String xmlType;
    @XmlElement(name = "MessageVersion")
    protected BigDecimal messageVersion;
    @XmlElement(name = "MessageSendingOrganisation", required = true)
    protected MessageSendingOrganisationType messageSendingOrganisation;

    /**
     * Gets the value of the messageCreationDateTime property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getMessageCreationDateTime() {
        return messageCreationDateTime;
    }

    /**
     * Sets the value of the messageCreationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMessageCreationDateTime(XMLGregorianCalendar value) {
        this.messageCreationDateTime = value;
    }

    /**
     * Gets the value of the messageUniqueID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUniqueID() {
        return messageUniqueID;
    }

    /**
     * Sets the value of the messageUniqueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUniqueID(String value) {
        this.messageUniqueID = value;
    }

    /**
     * Gets the value of the xmlType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlType() {
        return xmlType;
    }

    /**
     * Sets the value of the xmlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlType(String value) {
        this.xmlType = value;
    }

    /**
     * Gets the value of the messageVersion property.
     */
    public BigDecimal getMessageVersion() {
        return messageVersion;
    }

    /**
     * Sets the value of the messageVersion property.
     * 
     */
    public void setMessageVersion(BigDecimal value) {
        this.messageVersion = value;
    }

    /**
     * Gets the value of the messageSendingOrganisation property.
     * 
     * @return
     *     possible object is
     *     {@link MessageSendingOrganisationType }
     *     
     */
    public MessageSendingOrganisationType getMessageSendingOrganisation() {
        return messageSendingOrganisation;
    }

    /**
     * Sets the value of the messageSendingOrganisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageSendingOrganisationType }
     *     
     */
    public void setMessageSendingOrganisation(MessageSendingOrganisationType value) {
        this.messageSendingOrganisation = value;
    }

}
