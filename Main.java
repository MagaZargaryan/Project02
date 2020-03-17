public class Main {
    public static void main(String args[]){
        Main carAcceleration = new Main();
        //uncomment experiments to see the results
        carAcceleration.finalReport();
        //carAcceleration.reportOnExperiment1();
        //carAcceleration.reportOnExperiment2();
        //carAcceleration.reportOnExperiment3();
        //carAcceleration.reportOnExperimentMaxSpeed();
        //carAcceleration.experimentOnTwoCars();

    }
    private class Car{
        private float initial_speed;
        private float distance_to_intersection;
        private float pos_acc_magnitude;
        private float neg_acc_magnitude;
        private float distance_from_second_car;

        private  Car(float init_speed, float dist_to_intersection, float pos_acc_magn, float neg_acc_magn){

            initial_speed= init_speed;
            distance_to_intersection=dist_to_intersection;
            pos_acc_magnitude=pos_acc_magn;
            neg_acc_magnitude= neg_acc_magn;
        }
        private  Car(float init_speed, float dist_to_intersection, float pos_acc_magn, float neg_acc_magn, float dist_from_second_car){

            initial_speed= init_speed;
            distance_to_intersection=dist_to_intersection;
            pos_acc_magnitude=pos_acc_magn;
            neg_acc_magnitude= neg_acc_magn;
            distance_from_second_car=dist_from_second_car;
        }

    }

    private  class Road{
        private int yellow_light_duration;
        private float intersection_width;
        private Road(int yel_light_dur, float intersect_width){
            yellow_light_duration= yel_light_dur;
            intersection_width= intersect_width;

        }

    }

    public Car initCarParameters(float init_speed, float dist_to_intersection, float pos_acc_magn,float neg_acc_magn){
        if(init_speed<20 ||init_speed>80||dist_to_intersection <10 || dist_to_intersection>150||pos_acc_magn<1|| pos_acc_magn>3||neg_acc_magn<1|| neg_acc_magn>3) {

            System.out.println("Initial speed should belong to the interval [20 km/h,80 km/h] \nDistance to intersection should belong to the interval [10 km, 150 km ] \nPossitive acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2 \nNegative acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2");
            return null;
        }
        Car car= new Car(init_speed,dist_to_intersection, pos_acc_magn, neg_acc_magn);
        return car;

    }
    public Car initFrontCarParameters(float init_speed, float dist_to_intersection, float pos_acc_magn,float neg_acc_magn, float dist_from_second_car){
        if(init_speed<20 ||init_speed>80||dist_to_intersection <10 || dist_to_intersection>150||pos_acc_magn<1|| pos_acc_magn>3||neg_acc_magn<1|| neg_acc_magn>3||dist_from_second_car<10 || dist_from_second_car>100) {

            System.out.println("Initial speed should belong to the interval [20 km/h,80 km/h] \nDistance to intersection should belong to the interval [10 km, 150 km ] \nPossitive acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2 \nNegative acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2");
            return null;
        }
        Car car= new Car(init_speed,dist_to_intersection, pos_acc_magn, neg_acc_magn);
        return car;

    }

    public Car initCarParametersMaxSpeed(float init_speed, float dist_to_intersection, float pos_acc_magn,float neg_acc_magn){
        if(init_speed<50 ||init_speed>150||dist_to_intersection <10 || dist_to_intersection>150||pos_acc_magn<1|| pos_acc_magn>3||neg_acc_magn<1|| neg_acc_magn>3) {

            System.out.println("Initial speed should belong to the interval [20 km/h,80 km/h] \nDistance to intersection should belong to the interval [10 km, 150 km ] \nPossitive acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2 \nNegative acceleration magnitude should belong to the interval [1 km/s^2 , 3 km/s^2");
            return null;
        }
        Car car= new Car(init_speed,dist_to_intersection, pos_acc_magn, neg_acc_magn);
        return car;

    }

    public Road initRoadParameters(int yel_light_dur, float intersect_width){
        if(yel_light_dur<2 || yel_light_dur >5 || intersect_width<5 || intersect_width >20){
            System.out.println("Yellow light duration should belong to the interval [2 s,5 s] \nIntersection Width should belong to the interval [5 m, 20 m]");
            return null;
        }
        Road road = new Road(yel_light_dur, intersect_width);
        return road;

    }
    public int accelerationAdvice(Car car, Road road){
        if(car!=null && road!=null){

            double pos_acc_path= car.initial_speed * road.yellow_light_duration  + 0.5*(car.pos_acc_magnitude * road.yellow_light_duration *road.yellow_light_duration);
            double neg_acc_path= car.initial_speed * road.yellow_light_duration  - 0.5*(car.pos_acc_magnitude * road.yellow_light_duration *road.yellow_light_duration);
            double neg_acc_in_a_second_path= car.initial_speed  - 0.5*(car.pos_acc_magnitude);

            System.out.println("Distance car can pass if it accelerates : " +pos_acc_path);
            System.out.println("Distance car can pass if it decelerates : " +neg_acc_path);
            System.out.println("Intersection width : "+ road.intersection_width);
            System.out.println("Distance to intersection line : "+ car.distance_to_intersection);
            if( pos_acc_path > road.intersection_width+car.distance_to_intersection || pos_acc_path == road.intersection_width + car.distance_to_intersection ){
                System.out.println("Accelerate the car, you will manage till the light turns red. \n");
                return 1;
            } else if( neg_acc_path < car.distance_to_intersection||  neg_acc_path == car.distance_to_intersection){
                System.out.println("Decelerate the car, you will not manage till the light turns red. \n");
                return 2;
                //I wrote this condition for the case when car cannot manage to pass the the intersection line during the duration of yellow light and also cannot stop during that duration. So if it is possible to pass in one second time than it is possible to stop the car at time belonging to interval [1,duration of yellow light], example provided in example 2
            } else if(neg_acc_in_a_second_path<car.distance_to_intersection|| neg_acc_in_a_second_path==car.distance_to_intersection ){
                System.out.println("You should stop even though the light is still yellow. \n");
                return 3;

            }else {
                // this happens when both accelerating and decelerating will cross the intersection road anyways
                System.out.println("Whoops, you crashed, sorry. \n");
            }

        }
        return 0;

    }

    public void adviceForTwoCars(Car front_car, Car back_car,Road road){
        int advice1= accelerationAdvice(front_car, road );
        int advice2 = accelerationAdvice(back_car, road);
        if((advice1==2|| advice1==3) && advice2==1){
            System.out.println("As front car decelerates, than car in the back should decelerate as well. ");
        } else if(advice1==1&&advice2==1){

            if((back_car.initial_speed * road.yellow_light_duration  + 0.5*(back_car.pos_acc_magnitude * road.yellow_light_duration *road.yellow_light_duration)) > (front_car.initial_speed * road.yellow_light_duration  + 0.5*(front_car.pos_acc_magnitude * road.yellow_light_duration *road.yellow_light_duration))){
                System.out.println("Front car should accelerate, car in the back should stop.");
            } else System.out.println("Both should accelerate");

        } else if(advice1==1&&(advice2==2|| advice2==3)){
            System.out.println("Front car should accelerate and car in the back should decelerate");
        } else if((advice1==2||advice1==3)&& (advice2==2&&advice2==3)){
            System.out.println("Both cars should stop. Car in the back should stop earlier.");
        }

    }

    public void reportOnExperiment1(){
        Road[] roadList = new Road[5];
        Main carAcceleration = new Main();

        Car car1 = carAcceleration.initCarParameters(50,30,2,3);
        Car car2 = carAcceleration.initCarParameters(50,150,2,3);

        for(int i=0; i<5; i++){
            roadList[i]=  carAcceleration.initRoadParameters(2,16+i);
            carAcceleration.accelerationAdvice(car1,roadList[i]);
            carAcceleration.accelerationAdvice(car2,roadList[i]);

        }
        System.out.println("Here, for all cases accelerating the car is the solution.");
    }
    public void reportOnExperiment2(){
        Road[] roadList = new Road[5];
        Main carAcceleration = new Main();
        Car car = carAcceleration.initCarParameters(80,150,2,3);

        for(int i=0; i<5; i++){
            roadList[i]=  carAcceleration.initRoadParameters(2,16+i);
            carAcceleration.accelerationAdvice(car,roadList[i]);
        }
        System.out.println("Here, for all cases both accelerating and decelerating will \ncross the intersection line. So the only choice is to not move the car.");

    }
    public void reportOnExperiment3(){
        Road[] roadList = new Road[5];
        Main carAcceleration = new Main();
        Car car1 = carAcceleration.initCarParameters(20,150,2,3);
        Car car2 = carAcceleration.initCarParameters(20,10,2,3);

        for(int i=0; i<5; i++){
            roadList[i]=  carAcceleration.initRoadParameters(2,5+2*i);
            carAcceleration.accelerationAdvice(car1,roadList[i]);
            carAcceleration.accelerationAdvice(car2,roadList[i]);

        }
        System.out.println("Here, for all cases decelerating the car is the solution if speed is small but distance to \nintersection is big. While when speed is large and distance to intersection is small, \naccelerating is the solution.");

    }
    public void finalReport(){
        System.out.println("If initial speed is relatively small and distance to intersection is relatively \nlarge than decelerate, if speed and distance are relatively small than accelerate. \nIf speed is relatively large and distance is relatively small than \naccelerate, if distance and speed are relatively large than decelerate.\n");
    }
    public void reportOnExperimentMaxSpeed(){
        Road[] roadList = new Road[5];
        Main carAcceleration = new Main();

        Car car1 = carAcceleration.initCarParametersMaxSpeed(50,150,2,3);
        Car car2 = carAcceleration.initCarParametersMaxSpeed(150,150,1,3);

        for(int i=0; i<5; i++){
            roadList[i]=  carAcceleration.initRoadParameters(2,5+i);
            carAcceleration.accelerationAdvice(car1,roadList[i]);
            carAcceleration.accelerationAdvice(car2,roadList[i]);
        }
        System.out.println("For maximum speed it almost always crosses the interseciton line if the speed is large and does nto manage to cross if it is small.");
    }

    public void experimentOnTwoCars(){
        Main carAcceleration = new Main();
        Car car1 = carAcceleration.initFrontCarParameters(50,30,2,3,10);
        Car car2 = carAcceleration.initCarParameters(20,50,1,3);
        Road road = carAcceleration.initRoadParameters(2,20);
        carAcceleration.adviceForTwoCars(car1,car2,road);

    }

}
