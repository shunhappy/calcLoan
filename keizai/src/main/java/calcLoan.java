import java.util.Scanner;

public class calcLoan {


    public  static void main(String[] args){
        double desiredAmt;//希望金額
        double intRate;//金利
        int bnAmtReturn;//ボーナス返済額
        double bnAmtBorrowed;//うちボーナス分借入額
        int date_yr;
        int date_mn;
        System.out.println("月々の返済額を知りたい方は0を借入金額を知りたい方は1を押してください");
        Scanner scan0 = new Scanner((System.in));
        String type = scan0.next();
        if(type.equals("0")) {
            System.out.println("希望借入額（万円）、金利(%)、うちボーナス分借入額（万円）、年、月を入力してください");
            Scanner scan = new Scanner(System.in);
            desiredAmt = Double.parseDouble(scan.next())*10000;
            Scanner scan1 = new Scanner(System.in);
            intRate = Double.parseDouble(scan1.next());
            Scanner scan3 = new Scanner(System.in);
            bnAmtBorrowed = Double.parseDouble(scan3.next())*10000;
            Scanner scan4 = new Scanner(System.in);
            date_yr = Integer.parseInt(scan4.next());
            Scanner scan5 = new Scanner(System.in);
            date_mn = Integer.parseInt(scan5.next());
            if(bnAmtBorrowed == 0.0) {
                monthRepaymentAmount(desiredAmt,intRate,date_yr,date_mn);

            }else {
                monthRepaymentAmount(desiredAmt,intRate,bnAmtBorrowed,date_yr,date_mn);
            }

        }else{

            System.out.println("月々希望返済金額（万円）、金利(%)、ボーナス返済額（万円）、年、月を入力してください");
            Scanner scan = new Scanner(System.in);
            desiredAmt = Double.parseDouble(scan.next())*10000;
            Scanner scan1 = new Scanner(System.in);
            intRate = Double.parseDouble(scan1.next());
            Scanner scan2 = new Scanner(System.in);
            bnAmtReturn = Integer.parseInt(scan2.next())*10000;
            Scanner scan4 = new Scanner(System.in);
            date_yr = Integer.parseInt(scan4.next());
            Scanner scan5 = new Scanner(System.in);
            date_mn = Integer.parseInt(scan5.next());
            if(bnAmtReturn == 0) {
                totalRepaymentAmount(desiredAmt, intRate, date_yr, date_mn);

            }else {
                totalRepaymentAmount(desiredAmt, bnAmtReturn, intRate, date_yr, date_mn);
            }
        }
    }

    public static double monthRepaymentAmount(double d, double i, int dy, int dm){
        double mnRepaymentAmt = Math.floor((d * (i / 12 / 100) *
                Math.pow(1 + (i / 12 / 100), (dy * 12 + dm))) /
                ((Math.pow(1 + (i / 12 / 100), (dy * 12 + dm))) - 1));
        System.out.println("月々返済金額:"+mnRepaymentAmt+"円");
        System.out.println("年間返済額:"+mnRepaymentAmt*12+"円");
        System.out.println("総返済額"+mnRepaymentAmt*12*dy+"円");
        return mnRepaymentAmt ;
    }

    public static double monthRepaymentAmount(double d, double i, double b, int dy, int dm){
        double mnRepaymentAmt;
        double bnRepaymentAmt;
        double totalRepaymentAmt;
        double val1,val2;
        int f = dy*12+dm;
        mnRepaymentAmt = Math.floor(((d-b) * (i / 12 / 100) *
                Math.pow(1 + (i / 12 / 100), (dy * 12 + dm))) /
                ((Math.pow(1 + (i / 12 / 100), (dy * 12 + dm))) - 1));

        if(dm>=6){
            val1 = b * (i/2/100) * (1+6*i/12/100) * Math.pow( (1+i/2/100), (dy*2+1)-1 );
            val2 = Math.pow( (1+i/2/100), (dy*2+1) ) - 1;
        }
        else{
            val1 = b * (i/2/100) * (1+6*i/12/100) * Math.pow( (1+i/2/100), dy*2-1 );
            val2 = Math.pow( (1+i/2/100), dy*2 ) - 1;
        }
        bnRepaymentAmt = Math.floor( val1 / val2 );
        double annualRepaymentAmt = mnRepaymentAmt * 12 + bnRepaymentAmt * 2; //annual repayment amount
        if(dm>=6){
            totalRepaymentAmt = mnRepaymentAmt * f + bnRepaymentAmt * (dy * 2 + 1); // total repayment amount
        }
        else{
            totalRepaymentAmt = mnRepaymentAmt * f + bnRepaymentAmt * dy * 2; // total repayment amount
        }
        System.out.println("月々返済金額:"+mnRepaymentAmt+"円");
        System.out.println("ボーナス返済額:"+bnRepaymentAmt+"円");
        System.out.println("年間返済額:"+annualRepaymentAmt+"円");
        System.out.println("総返済額"+totalRepaymentAmt+"円");
        return totalRepaymentAmt;
    }

    public static double totalRepaymentAmount(double brAmt, double i, int dy, int dm){
        double mn1,mn2,mnRepaymentAmt,annualRepaymentAmt;
        mn1 = brAmt * ( Math.pow( (1+i/12/100), dy*12+dm ) - 1 );
        mn2 = (i/12/100) * Math.pow( (1+i/12/100), dy*12+dm );
        mnRepaymentAmt =  mn1 / mn2 ;
        annualRepaymentAmt = brAmt * 12;
        System.out.println("借入金額:"+mnRepaymentAmt+"円");
        System.out.println("年間返済額:"+annualRepaymentAmt+"円");
        return mnRepaymentAmt ;
    }
    public static double totalRepaymentAmount(double brAmt, int b, double i, int dy, int dm){
        double mn1 = brAmt * ( Math.pow( (1+i/12/100), dy*12+dm ) - 1 );
        double mn2 = (i/12/100) * Math.pow( (1+i/12/100), dy*12+dm );
        double mnRepaymentAmt = ( mn1 / mn2 );

        double bn1 = b * ( Math.pow( (1+i/2/100), dy*2 ) - 1 );
        double bn2 = (i/2/100) * Math.pow( (1+i/2/100), dy*2 );
        double bnRepaymentAmt = ( bn1 / bn2 );

        double totalRepaymentAmt = mnRepaymentAmt + bnRepaymentAmt;
        double annualRepaymentAmt = brAmt * 12 + b * 2;
        System.out.println("借入金額:"+totalRepaymentAmt+"円");
        System.out.println("年間返済額:"+annualRepaymentAmt+"円");
        return totalRepaymentAmt;
    }
}