//package com.jungbo.cars;
public class  Truck extends Car
{
        protected int weight=4; // Truck  weight=4
        protected int sped=12;
        public void show(){ // overrding  4
                System.out.println("Truck ~~ 4 speed "+sped);
        }
        public void tone(){// * 5
                System.out.println("Truck 5 weight :==> "+weight);
        }
}
