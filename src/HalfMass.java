import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.DoubleToIntFunction;

public class HalfMass {
    public static int maxSum = 0;

    public static void main (String args[]) throws Exception {
        int mass[] = {2,3,14,9,4,5,2,15};//54
        //int mass[] = {3,4,3,3,2};//12
        //int mass[] = {1,2,3,4,5,6};//20
        //int mass[] = {6,5,4,3,2,1};//20
        //int mass[] = {3,7,4,8,15,14,17,3,9,11,13,19,16};//136
        //int mass[] = {8,7,3,3,3};//6


        ArrayList<Integer> arraymass  = new ArrayList<>();

        for(int i = 0; i < mass.length; i++) {
            arraymass.add(mass[i]);
        }

        int summ = 0;

        if((arraymass.size() > 1000)||(arraymass.size() < 1)) {
            throw new Exception();//Number of items [1,1000], otherwise error!
        }
        for(int i = 0; i < arraymass.size(); i++) {
            if((arraymass.get(i) >= 21)||(arraymass.get(i) <= 0)) {
                throw new Exception();//Item in [1,20], otherwise error!
            }
            summ += arraymass.get(i);
            if(summ > 10000) {
                throw new Exception();//Sum all items in [1,10000], otherwise error!
            }
        }

        //System.out.println("START MASS: ");
        //showmass(arraymass);
        maxSumReturn(arraymass);
        //System.out.println("MAX SUMM: " + maxSum);
        System.out.println(maxSum);
    }

    public static Integer maxSumReturn(ArrayList<Integer> arraymass) {
        int size = arraymass.size();
        String binStr;
        ArrayList<Integer> massWork = new ArrayList<>();

        for(int e = 0; e < arraymass.size(); e++){
            massWork.add(0);
        }

        for(int s = (int)Math.pow(2,size) - 1; s > 0; s --) {//перебираем все возможные комбинации массива
            binStr = Integer.toBinaryString(s);
            int binStrLenght = binStr.length();
            int p = arraymass.size() - binStrLenght;
            for(int w = 0; w < arraymass.size(); w ++){//заполняем первую комбинацию
                if(w < p) {
                    massWork.set(w, 0);
                } else {
                    int element = Integer.parseInt(binStr.substring(w - p, w - p + 1));
                    if(element == 1) {
                        massWork.set(w, arraymass.get(w));
                    } else {
                        massWork.set(w, element);
                    }
                }
            }

            int massSum = summass(massWork);//combination sum
            if((massSum%2 == 0)&&(maxSum < massSum)){
                int halfMassSum = massSum/2;
                ArrayList<Integer> nullmass = new ArrayList<>();
                for(int i = 0; i < arraymass.size(); i++) {
                    nullmass.add(0);
                }
                combination(nullmass, 0, arraymass.size(),massWork, halfMassSum, maxSum);//ищем полусумму
            }
        }
        return 0;
    }


    //all combinations
    public static void combination(ArrayList<Integer> mass, int k, int n, ArrayList<Integer> arraymass, int half, int maxSum) {

        if(k == n-1) {
            mass.set(k,0);
            print(mass, n, arraymass, half, maxSum);
            mass.set(k,1);
            print(mass,n, arraymass, half, maxSum);
            return;
        }
        mass.set(k,0);
        combination(mass, k+1, n, arraymass, half, maxSum);
        mass.set(k,1);
        combination(mass, k+1, n, arraymass, half, maxSum);
    }

    //array of wll combinations sum
    public static void print(ArrayList<Integer> mass, int n, ArrayList<Integer> arraymass, int half, int maxSum){
        int massSumElement = 0;

        for(int i = 0; i < n; i++){
            if(mass.get(i) == 1) {
                massSumElement += arraymass.get(i);
            }
        }
        if((massSumElement == half)&&(massSumElement*2 > HalfMass.maxSum)) {
            HalfMass.maxSum = half*2;
        }
    }


    //show array on console
    public static void showmass(ArrayList<Integer> arraymass) {
        for(int i = 0; i < arraymass.size(); i++) {
            System.out.print( arraymass.get(i) + " ");
        }
        System.out.println();
    }

    //sum array items
    public static int summass(ArrayList<Integer> arraymass) {
        int summ = 0;
        for(int i = 0; i < arraymass.size(); i++) {
            summ += arraymass.get(i);
        }
        return summ;
    }



}
