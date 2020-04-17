package sample.recovery.samplerecovery.utils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import sample.recovery.samplerecovery.keywords.GenericKeywords;

public class Retry  implements IRetryAnalyzer  {
    private int retryCount = 0;
    private int maxRetryCount = 1;
   
    public static String runvalue;
    
    
    public boolean retry(ITestResult result) {
 
    System.out.println("Rerun========>"+GenericKeywords.Rerun);
if(GenericKeywords.Rerun.equalsIgnoreCase("Y")) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
}
        return false;
    }

   
}