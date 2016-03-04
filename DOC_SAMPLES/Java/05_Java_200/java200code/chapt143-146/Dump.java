//package com.jungbo.cars;

public class  Dump extends Truck
{
        private int weight=14;//Truck shadow weight 4
        public void show(){// overring 12
                System.out.println("Dump ~~12  ");
        }
        public int speed(){//overriding 13
                return sped;
        }
        public void pass(){ //  14
                System.out.println("Dump ~~14   "+weight+" sped"+sped);
        }
}
