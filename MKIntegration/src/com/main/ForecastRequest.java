
package com.main;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:xs="http://www.w3.org/2001/XMLSchema" name="forecastRequest">
 *   &lt;xs:complexType>
 *     &lt;xs:sequence>
 *       &lt;xs:element type="xs:string" name="timeWindow"/>
 *     &lt;/xs:sequence>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class ForecastRequest
{
    private String timeWindow;

    /** 
     * Get the 'timeWindow' element value.
     * 
     * @return value
     */
    public String getTimeWindow() {
        return timeWindow;
    }

    /** 
     * Set the 'timeWindow' element value.
     * 
     * @param timeWindow
     */
    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }
}
