
public class MechanicsProject{

    public float M0;
    public float M1;
    public float M2;
    public float M3;
    public float mu1;
    public float mu2;
    public float mu3;
    //public float F;
    public float g;
    public static double a2;
    public static double a1;
    public static double a3x;
    public static double a3y;
    
     public static void main(String []args){
         MechanicsProject proj = new MechanicsProject(300,100,50,0.1f,0.2f,0.4f,250);
         if(proj!=null){
            //a2= proj.calculateA2();
            a1= proj.calculateA1(a2);
            a3x= proj.calculateA3x(a1);
            a3y= proj.calculateA3y(a1,a2);
          

            double[] timeUnits= new double[5];
            double[] forces = new double[5];
            double[] positionM2= new double[5];
            double[] positionM1= new double[5];
            double[] positionM3= new double[5];

            for(int i=0; i< timeUnits.length; i++) {
            	timeUnits[i]=(i)*10f;
            	forces[i] = 20*(i);
                System.out.println("time"+timeUnits[i]+ "  force" + forces[i]);
            }
            positionM2= proj.calculatePositionInTimeUnitsM2(forces, timeUnits);
            positionM1 = proj.calculatePositionInTimeUnitsM1orM3(forces, timeUnits);
            positionM3 = proj.calculatePositionInTimeUnitsM1orM3(forces, timeUnits);
            proj.getPositionsReport(positionM1, timeUnits);
            proj.getPositionsReport(positionM2, timeUnits);
            proj.getPositionsReport(positionM3, timeUnits);

            
            
         }
     }
     public  MechanicsProject(float mass1, float mass2, float mass3, float m1, float m2, float m3, float force){
         M0=0;
         g=10;
         if(mass1>0&&mass2>0&&mass3>0 && force>-300 && force<300 && m1>0&& m1<0.5 && m2>0&& m2<0.5 && m3>0&& m3<0.5){
             M1=mass1;
             M2=mass2;
             M3=mass3;
             //F=force;
             mu1=m1;
             mu2=m2;
             mu3=m3;
         } else {
             System.out.println("Wrong input. Masses should be positive.\n F should be between range [-300,300] \n Mu should be between range [0,0.5]");
         }
     }
     public  double[] calculatePositionInTimeUnitsM2(double[] forces, double[] timeUnits){
         double[] positions = new double[forces.length];
         for(int i = 0; i< forces.length; i++){
             double a2= calculateA2(forces[i]);
             double t = timeUnits[i];
             positions[i]=calculatePosition(t,a2);
         }
         return positions;
     }
     
     // as a1=a3
     public  double[] calculatePositionInTimeUnitsM1orM3(double[] forces, double[] timeUnits){
         double[] positions = new double[forces.length];
         for(int i = 0; i< forces.length; i++){
             double a2= calculateA2(forces[i]);
             double a1 = calculateA1(a2);
             double t = timeUnits[i];
             positions[i]=calculatePosition(t,a1);
             //System.out.println("mm"+positions[i]);
         }
         return positions;
     }
    
     
     public double calculatePosition(double t, double a){
         return (0.5*(a*t*t));
     }
     public void getPositionsReport(double[] Mpos, double[] time) {
    	 for(int i=0; i< Mpos.length; i++) {
    		 System.out.println("at time: " + time[i] + "position is: " + Mpos[i]);
    	 }
     }
     public double calculateA2(double F){
        // System.out.println(M1);
        double a2=  ( -(M1+M3) *(mu2*M2*g-M3*g +2*mu3*F) +  M3*(mu2*M2*g - mu1*M1*g +mu1*mu2*M2*g) ) /
                    (M2*(M1+M3) - M3*(-M2+ mu1*M2-M1 - M3));
        return a2;

     }
     public double calculateA1(double a2){
         double a1 = (- M2*a2 + mu2*M2*g-mu1*M1*g+ mu1*M2*a2+mu1*mu2*M2*g) / (M1+M2);
         return a1;

     }
     public double calculateA3y(double a1, double a2){
         double a3y= a1-a2;
         return a3y;
     }
     
     public double calculateA3x(double a1){
         double a3x=a1;
         return a3x;
     }
     
}