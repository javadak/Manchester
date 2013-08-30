/** ForecastUpdateClient class:
 *  Initiates a communication for integration between Manchester's MultiAgent System with
 *  KUL's Ant based system.
 *  Parameters:
 *  	KULhostPortandXMLRPCref (URL of the server side) 
 *  	ForecastRequestInterval (Continuous update request interval - in milliseconds)
 *  	ForecastRequestPeriod (The time period for which the request will be created)
 *  Methods:
 *  	startSingleCommunication() (Starts a communication and returns a ForecaseResponse)
 * 
 */
package com.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.ws.commons.util.Base64;
import org.apache.ws.commons.util.Base64.DecodingException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import com.main.ForecastRequest;
import com.main.ForecastResponse;

/**
 * @author Javad Akhlaghinia
 *
 */
public class ForecastUpdateClient {
	
	//                  The class parameters
	static String KULhostPortandXMLRPCref = "http://127.0.0.1:8080/"; 
	static int ForecastRequestInterval = 30000; // in milliseconds
	static String ForecastRequestPeriod = "7200"; // in seconds

	//              Parameters setters and getters  
	public static void setForecastRequestInterval(int forecastRequestInterval) {
		ForecastRequestInterval = forecastRequestInterval;
	}
	public static String getForecastRequestPeriod() {
		return ForecastRequestPeriod;
	}
	public void setForecastRequestPeriod(String forecastRequestPeriod) {
		ForecastRequestPeriod = forecastRequestPeriod;
	}
	public static String getKULhostPortandXMLRPCref() {
		return KULhostPortandXMLRPCref;
	}
	public void setKULhostPortandXMLRPCref(String kULhostPortandXMLRPCref) {
		KULhostPortandXMLRPCref = kULhostPortandXMLRPCref;
	}
	public static int getForecastRequestInterval() {
		return ForecastRequestInterval;
	}
	public void setForecaseRequestInterval(int forecastRequestInterval) {
		ForecastRequestInterval = forecastRequestInterval;
	}
	//--------------------------------------------------------------------------
	
	
	/*       Communication Method: works with following sequence        
	 * 1- Creates an XmlRpcClient
	 * 2- Creates a new request object and forms an xml request from that
	 * 3- Calls (executes) a method on server and receives an xml response
	 * 4- Parses the xml response and creates a response object from that
	 * 5- Returns the response object
	 */
	public ForecastResponse startSingleCommunication() throws DecodingException{
		
		System.out.println("Communicating with KUL server\n");
		
		//           Creating a client object
		System.out.print("Creating a client object ......");
		XmlRpcClient myClient = null;
		try {
			myClient = createRpcClient(KULhostPortandXMLRPCref);
			System.out.print("OK\n");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//-------------------------------------------------
		
		//            Creating request and response object
		System.out.print("Creating a request object ......");
		ForecastRequest forecastReq = new ForecastRequest();
		ForecastResponse forecastRes = new ForecastResponse();
	    forecastReq.setTimeWindow(ForecastRequestPeriod);
	    System.out.print("OK\n");
	    //--------------------------------------------------

	    //            Convert request object to XML
	    System.out.print("Converting the request object to XML and pushing them to bytes ......");
	    IBindingFactory bfact = null;
		try {
			bfact = BindingDirectory.getFactory(ForecastRequest.class);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		IMarshallingContext mctx = null;
		try {
			mctx = bfact.createMarshallingContext();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		//-----------------------------------------------------
		
		//               Push XML to Byte[] string
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			mctx.marshalDocument(forecastReq, "UTF-8", null, byteArrayOutputStream);
			System.out.print("OK\n");
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		
		String strRequest = new String(byteArrayOutputStream.toByteArray());
		//-------------------------------------------------------
		
		byte[] strbytes = strRequest.getBytes();
		strRequest = Base64.encode(strbytes);
		
		//            Set it in request parameter
		Object[] paramsN = new Object[] { strRequest }; // { null };
		
		//-------------------------------------------------------
		
		//          Send request to server and get response
		System.out.print("Send request to server and get response ......");
		String strResponse = null;
		//Vector params = new Vector();
		try {			
			strResponse = (String) myClient.execute("request", paramsN);
			System.out.print("OK\n");
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		//--------------------------------------------------------
		
		//              Decode response to base64 string
		byte[] bytesResponse = null;
		try {
			bytesResponse = Base64.decode(strResponse);
			String str = new String(bytesResponse);
			System.out.print("\n" + str + "\n\n");
		} catch (DecodingException e) {
			e.printStackTrace();
		}
		//System.out.println(new String(bytesResponse));
	    //----------------------------------------------------------
		
		//           Convert Byte[] string into Object
		IBindingFactory bfactRes = null;
		try {
			bfactRes = BindingDirectory.getFactory(ForecastResponse.class);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		IUnmarshallingContext imctx = null;
		try {
			imctx = bfactRes.createUnmarshallingContext();
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		try {
			forecastRes = (ForecastResponse) imctx.unmarshalDocument(new ByteArrayInputStream(bytesResponse), "myname", null);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		//-------------------------------------------------------------
		return forecastRes;
	}
	
	//           This method initialises an XmlRpcClient - http://ws.apache.org/xmlrpc/client.html
	private static XmlRpcClient createRpcClient(String hostString) throws MalformedURLException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(hostString));
		config.setEnabledForExtensions(true);
		config.setEnabledForExceptions(true);
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		return client;
	}
	//-------------------------------------------------------------------
}
