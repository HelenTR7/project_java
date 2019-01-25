import java.lang.reflect.Array;
import java.util.ArrayList;

public class HalfMass {


    public static void main (String args[]) throws Exception {
        //int mass[] = {2,3,14,9,4,5,2,15};
        //int mass[] = {3,4,3,3,2};
        int mass[] = {1,2,3,4,5,6};
        //int mass[] = {6,5,4,3,2,1};
        //int mass[] = {3,7,4,8,15,14,17,3,9,11,13,19,16};
        //int mass[] = {8,7,3,3,3};


        ArrayList<Integer> arraymass  = new ArrayList<>();

        for(int i = 0; i < mass.length; i++) {
            arraymass.add(mass[i]);
        }

        int l = 0;

        int k = 0;
        int minsumm = 0;
        for(l = 0; l < arraymass.size() - 1; l++) {//remove duble
            for(k = l+1; k < arraymass.size(); k++) {
                if(arraymass.get(l) == arraymass.get(k)){
                    minsumm += arraymass.get(l)*2;
                    arraymass.remove(l);
                    arraymass.remove(k);
                }
            }
        }

        int summ = 0;
        //sum up
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
        int maxSumm = maxSumReturn(arraymass);
        //System.out.println("MAX SUMM: " + maxSumm);
        System.out.println(maxSumm+minsumm);
    }

    public static Integer maxSumReturn(ArrayList<Integer> arraymass) {
        int halfSumm = 0;
        ArrayList<Integer> massWork = new ArrayList<>();

        int minelem = 0;
        if(summass(arraymass)%2 == 1){
            minelem = 1;
        }

        int memory = 0;
        for(int k = minelem; k <= 21; k += 2) {
            if(memory != 0) {
                arraymass.add(memory);
                memory = 0;
            }
            for(int j = 0; j < arraymass.size(); j++) {
                if(arraymass.get(j) == k) {
                    arraymass.remove(j);
                    memory = k;
                    break;
                }
            }

            //System.out.print("Array without some number: ");
            //showmass(arraymass);//Array without 0,1,.....20

            halfSumm = summass(arraymass)/2;

            massWork = massSumFunc(arraymass);//возвращает набор сумм элементов для данного массива
            for(int i = 0; i < massWork.size(); i++) {
                if((massWork.get(i) == halfSumm)&&(halfSumm*2 == summass(arraymass))) {//если нашли сумму = половине суммы эл-в массива
                    //System.out.println("SUM FOUND: " + halfSumm*2);

                    return halfSumm*2;
                }
            }

        }
        return 0;
    }

    //func
    public static ArrayList<Integer> massSumFunc(ArrayList<Integer> arraymass) {
        ArrayList<Integer> nullmass = new ArrayList<>();
        for(int i = 0; i < arraymass.size(); i++) {
            nullmass.add(0);
        }

        int k = 0;
        int n = arraymass.size();

        ArrayList<Integer> sumMass = new ArrayList<>();
        combination(nullmass,k,n, arraymass, sumMass);
        //showmass(sumMass);//получили массив сумм
        //System.out.println();
        return sumMass;
    }

    //all combinations
    public static void combination(ArrayList<Integer> mass, int k, int n, ArrayList<Integer> arraymass, ArrayList<Integer> summass) {
        if(k == n-1) {
            mass.set(k,0);
            print(mass, n, arraymass, summass);
            mass.set(k,1);
            print(mass,n, arraymass,summass);
            return;
        }
        mass.set(k,0);
        combination(mass, k+1, n, arraymass,summass);
        mass.set(k,1);
        combination(mass, k+1, n, arraymass,summass);
    }

    //array of wll combinations sum
    public static void print(ArrayList<Integer> mass, int n, ArrayList<Integer> arraymass, ArrayList<Integer> summass){

        int summ = 0;
        int massSumElement = 0;
        for(int i = 0; i < n; i++){
            if(mass.get(i) == 1) {
                //System.out.print(" / " + arraymass.get(i));
                summ ++;
                massSumElement += arraymass.get(i);
            }
        }
        if(summ == 0){
            //System.out.println("NULL");
        }
        //System.out.println("MassSummElem: " + massSumElement);
        summass.add(massSumElement);
    }



    public static ArrayList<Integer> sortmass( int mass[]) {
        for(int i = 0; i < mass.length; i++)
            for(int j = 0; j < mass.length; j++){
                if(mass[i] > mass[j]) {
                  int flag = mass[i];
                  mass[i] = mass[j];
                  mass[j] = flag;
                }
            }

        ArrayList<Integer> arraymass  = new ArrayList<>();

        for(int i = 0; i < mass.length; i++) {
            arraymass.add(mass[i]);
        }
        return arraymass;
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
