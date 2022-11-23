package sbi;

import bankdetails.GetBankDetails;
import rbi.RbiInterface;

import java.io.IOException;

public class SbiClass implements RbiInterface {
    public int principalAmount;
    public  int noOfYears;
    public int rate;
    int result=0;
    @Override
    public boolean checkamountWithdrwn() {
        return false;
    }

    @Override
    public int rateOfInterest() throws IOException {
        GetBankDetails.readBankDetails();
        rate = GetBankDetails.interest;
        System.out.println("GetBankDetails.interest "+GetBankDetails.interest);
        return rate;
    }
    public int simpleInterst(){
        System.out.println("GetBankDetails.amount "+GetBankDetails.amount);
        System.out.println("GetBankDetails.years "+GetBankDetails.years);
        System.out.println("rate "+rate);
        principalAmount = GetBankDetails.amount;
        noOfYears = GetBankDetails.years;
        result =((principalAmount*rate*noOfYears)/100);
        System.out.println(result);
        return result;
    }
}
