// Temperatures1.java

// proccessing measurement data


/**********************************************************************

A problem: processing measurement data

Temperature measurements are taken in one and the same place for a couple
of weeks. The measurements are taken regularly � the same number of
measurements each week. At the end of the measurement period the collected
data is to be processed: for each week the least, the greatest and the
average temperature is to be determined. The least, greatest and
average temperature for the whole period is also to be computed.

A solution to the problem

This program reads the temperatures and displays them. Then the least,
greatest and average temperature for each week is computed and stored.
These results are printed on the standard output device. Finally, the
least, greatest and average temperature over the whole measurement

output device.

Author: Fadil Galjic

**********************************************************************/


import java.util.*; // Scanner, Locale
import static java.lang.System.out;

class Temperatures1
{
	public static void main (String[] args)
	{
 		out.println("TEMPERATURES\n");

		// input tool
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

        // enter the number of weeks and measurements
		out.print("number of weeks: ");
		int nofWeeks = in.nextInt();
		out.print("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1]
                                 [nofMeasurementsPerWeek + 1];

        // read the temperatures
		for (int week = 1; week <= nofWeeks; week++)
		{
			out.println("temperatures - week " + week + ":");
			for (int measurement = 1; measurement <= nofMeasurementsPerWeek; measurement++)
				t[week][measurement] = in.nextDouble();
		}
		out.println("");

		// show the temperatures
		out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
			for (int measurement = 1;
			    measurement <= nofMeasurementsPerWeek; measurement++)
			    out.print(t[week][measurement] + " ");
		    out.println("");
		}
		out.println("");

		// the least, greatest and average temperatures - weekly
		double[] minT = new double[nofWeeks + 1];
		double[] maxT = new double[nofWeeks + 1];
		double[] sumT = new double[nofWeeks + 1];
		double[] avgT = new double[nofWeeks + 1];
		// add code here
	
// HAFSHAS KOD
		//Mininum value
		for (int week = 1; week <= nofWeeks; week++)
        {
				minT[week] = t[week][1]; 
			for (int measurement = 2; measurement <= nofMeasurementsPerWeek; measurement++)
			    if (t[week][measurement] < minT[week])
					minT[week] = t[week][measurement];
		    
		}
		//Max value
		for (int week = 1; week <= nofWeeks; week++)
        {
				maxT[week] = t[week][1]; 
			for (int measurement = 2; measurement <= nofMeasurementsPerWeek; measurement++)
			    if (t[week][measurement] > maxT[week])
					maxT[week] = t[week][measurement];
		    
		}
		// average
		for (int week = 1; week <= nofWeeks; week++){
			for (int measurement = 1; measurement <= nofMeasurementsPerWeek; measurement++)
				{
					sumT[week] += t[week][measurement];
				}
			if (nofMeasurementsPerWeek > 0)
				avgT[week] = sumT[week] / nofMeasurementsPerWeek ;

		}
//HAFSHAS KOD

		out.println("the least, greatest and average temperatures"
		    + " - weekly");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(minT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(maxT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(avgT[week] + " ");
		out.println("");
		out.println();

		// the least, greatest and average temperatures - whole period
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = 0; // ändrade sum temp till 0 så att beräkningen blir correct. 
		double avgTemp = 0;
		// add code here
		for (int week = 1; week <= nofWeeks; week++)
		{
			if( minT[week] < minTemp)
			{
				minTemp = minT[week];
			}
			if (maxT[week] > maxTemp)
			{
				maxTemp = maxT[week];
			}
			if (nofMeasurementsPerWeek > 0){
			sumTemp += sumT[week];
			
			}
		}

	if (nofWeeks > 0 && nofMeasurementsPerWeek > 0) {
			avgTemp = sumTemp / (nofMeasurementsPerWeek * nofWeeks);
		}
		

        // show the least, greatest and average temperature for
        // the whole period
		out.println("the least, greatest and average temperature"
		    + " - whole period");
        out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);
    }
}
