//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.03.19 at 12:54:00 AM MSK 
//


package ru.datana.steel.parser.xml.pojo;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for controllerType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="controllerType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ip" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="rack" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="slot" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="note" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "controllerType", propOrder = {
        "value"
})
public class ControllerType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "ip")
    protected String ip;
    @XmlAttribute(name = "rack")
    protected String rack;
    @XmlAttribute(name = "slot")
    protected String slot;
    @XmlAttribute(name = "note")
    protected String note;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the ip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the rack property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRack() {
        return rack;
    }

    /**
     * Sets the value of the rack property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRack(String value) {
        this.rack = value;
    }

    /**
     * Gets the value of the slot property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSlot() {
        return slot;
    }

    /**
     * Sets the value of the slot property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSlot(String value) {
        this.slot = value;
    }

    /**
     * Gets the value of the note property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNote(String value) {
        this.note = value;
    }

}
