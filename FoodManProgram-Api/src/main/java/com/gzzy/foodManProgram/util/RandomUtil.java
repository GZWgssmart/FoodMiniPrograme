package com.gzzy.foodManProgram.util;

import java.util.Random;

public class RandomUtil {
    static int cnt =1;
    public static String randomCode() {
        int max=150;
        int min=1;
        Random random = new Random();
        String domain = "";
        for(int index = 0 ;index <cnt;index++){
            int subindex = 0;
            while(domain.length() <11){
                int s = random.nextInt(max)%(max-min+1) + min;
                if(subindex == 0){
                    if((s>=97 && s<= 122)){
                        subindex++;
                        domain += (char)s;
                    }
                } else {
                    if((s>= 48 && s<= 57)|| (s>=97 && s<= 122)){

                        domain += (char)s;
                    }
                }

            }
        }
       return domain;
    }

    private static int totalCount = 0;
    public static int Customer(){
        ++totalCount;
        return totalCount;
    }
}
