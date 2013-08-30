
package com.main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:xs="http://www.w3.org/2001/XMLSchema" name="forecastResponse">
 *   &lt;xs:complexType>
 *     &lt;xs:sequence>
 *       &lt;xs:element name="link" minOccurs="1" maxOccurs="unbounded">
 *         &lt;!-- Reference to inner class Link -->
 *       &lt;/xs:element>
 *     &lt;/xs:sequence>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class ForecastResponse
{
    private List<Link> linkList = new ArrayList<Link>();

    /** 
     * Get the list of 'link' element items.
     * 
     * @return list
     */
    public List<Link> getLinkList() {
        return linkList;
    }

    /** 
     * Set the list of 'link' element items.
     * 
     * @param list
     */
    public void setLinkList(List<Link> list) {
        linkList = list;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:xs="http://www.w3.org/2001/XMLSchema" name="link" minOccurs="1" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:string" name="id" minOccurs="1" maxOccurs="1"/>
     *       &lt;xs:element name="interval" minOccurs="1" maxOccurs="unbounded">
     *         &lt;!-- Reference to inner class Interval -->
     *       &lt;/xs:element>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Link
    {
        private String id;
        private List<Interval> intervalList = new ArrayList<Interval>();

        /** 
         * Get the 'id' element value.
         * 
         * @return value
         */
        public String getId() {
            return id;
        }

        /** 
         * Set the 'id' element value.
         * 
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /** 
         * Get the list of 'interval' element items.
         * 
         * @return list
         */
        public List<Interval> getIntervalList() {
            return intervalList;
        }

        /** 
         * Set the list of 'interval' element items.
         * 
         * @param list
         */
        public void setIntervalList(List<Interval> list) {
            intervalList = list;
        }
        /** 
         * Schema fragment(s) for this class:
         * <pre>
         * &lt;xs:element xmlns:xs="http://www.w3.org/2001/XMLSchema" name="interval" minOccurs="1" maxOccurs="unbounded">
         *   &lt;xs:complexType>
         *     &lt;xs:sequence>
         *       &lt;xs:element type="xs:integer" name="timeStart" minOccurs="1" maxOccurs="1"/>
         *       &lt;xs:element type="xs:integer" name="timeStop" minOccurs="1" maxOccurs="1"/>
         *       &lt;xs:element type="xs:integer" name="travelTime" minOccurs="1" maxOccurs="1"/>
         *     &lt;/xs:sequence>
         *   &lt;/xs:complexType>
         * &lt;/xs:element>
         * </pre>
         */
        public static class Interval
        {
            private BigInteger timeStart;
            private BigInteger timeStop;
            private BigInteger travelTime;

            /** 
             * Get the 'timeStart' element value.
             * 
             * @return value
             */
            public BigInteger getTimeStart() {
                return timeStart;
            }

            /** 
             * Set the 'timeStart' element value.
             * 
             * @param timeStart
             */
            public void setTimeStart(BigInteger timeStart) {
                this.timeStart = timeStart;
            }

            /** 
             * Get the 'timeStop' element value.
             * 
             * @return value
             */
            public BigInteger getTimeStop() {
                return timeStop;
            }

            /** 
             * Set the 'timeStop' element value.
             * 
             * @param timeStop
             */
            public void setTimeStop(BigInteger timeStop) {
                this.timeStop = timeStop;
            }

            /** 
             * Get the 'travelTime' element value.
             * 
             * @return value
             */
            public BigInteger getTravelTime() {
                return travelTime;
            }

            /** 
             * Set the 'travelTime' element value.
             * 
             * @param travelTime
             */
            public void setTravelTime(BigInteger travelTime) {
                this.travelTime = travelTime;
            }
        }
    }
}
