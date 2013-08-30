/**
 * 
 */
package com.main;

import java.util.Timer;
import java.util.TimerTask;

import com.main.ForecastResponse;

/**
 * @author Javad Akhlaghinia
 *
 */
public class TestClass {
	/**
	 * 
	 * @param args
	 * 
	 * 
	 */
	public static void main(String[] args) {
			
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					ForecastUpdateClient c1 = new ForecastUpdateClient();
					//c1.setForecaseRequestInterval(10000);
					c1.setForecastRequestPeriod("7200");
					c1.setKULhostPortandXMLRPCref("http://172.30.4.6:9001/"); //ec2-54-213-202-194.us-west-2.compute.amazonaws.com:49152/");
					try {
						ForecastResponse fs =  c1.startSingleCommunication();
						System.out.println(fs);
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}, 4000, 10000);
	}
}
