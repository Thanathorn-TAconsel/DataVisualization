import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class DataManager {
    HashMap<String,Integer> rowData = new HashMap<String, Integer>();
    HashMap<String,Integer> columnData = new HashMap<String, Integer>();
    ArrayList<String[]> dataList = new ArrayList();
    boolean[] isMeasure;
    HashMap<String ,Integer> dimensionIndex = new HashMap();
    DataManager(String filename) throws Exception {
        loadData(filename);
    }
    public ArrayList<String[]> display(String scanList[],HashMap map) {
        int[] indexList = new int[scanList.length];
        for (int i = 0;i < scanList.length;i++) {
            indexList[i] = dimensionIndex.get(scanList[i]);
        }
        ArrayList<String[]> foundList = new ArrayList<>();
        displayRecusiveMap(map,0,indexList,foundList,null);
        return foundList;
    }

    public boolean isMeasure(String measure) {
        return isMeasure[dimensionIndex.get(measure)];
    }
    public Set getDimension() {
        return dimensionIndex.keySet();
    }
    public static double[] sum(ArrayList<Double[]> list) {
        //double sum = 0;
        double[] sum = new double[list.get(0).length];
        for (Double[] data: list) {
            for (int i = 0;i <sum.length;i++) {
                sum[i] += data[i];
            }
        }
        return sum;
    }
    public static double max(ArrayList<Double> list) {
        double max = list.get(0);
        for (double data: (ArrayList<Double>) list) {
            if (data > max)max = data;
        }
        return max;
    }
    public static double min(ArrayList<Double> list) {
        double min = list.get(0);
        for (double data: (ArrayList<Double>) list) {
            if (data < min)min = data;
        }
        return min;
    }
    public static double avg(ArrayList<Double> list) {
        double sum = 0;
        for (double data: (ArrayList<Double>) list) {
            sum += data;
        }
        return sum / list.size();
    }
    public void displayRecusiveMap(HashMap root,int level,int[] indexList,ArrayList arrayList,String[] build) {

        for (Object key: root.keySet()) {
            Object value = root.get(key);
            System.out.println(spaceBuilder(level) + key);
            if (build == null) {
                build = new String[indexList.length];
                build[level] = (String) key;
            } else {
                build[level] = (String) key;
            }

            if (value instanceof HashMap) {
                if (level == indexList.length-1) {
                    System.out.println("END");
                    arrayList.add(build.clone());
                    build = null;
                }
                displayRecusiveMap((HashMap) value,level+1,indexList,arrayList,build);
            } else if (value instanceof ArrayList) {
                    double[] sum = sum((ArrayList<Double[]>) value);
                    System.out.println(spaceBuilder(level+1) + sum);
                    for (int i = 0;i < sum.length;i++) {
                        build[level+1 + i] = sum[sum.length - i - 1] + "";
                    }
                    //build[level+1] = Arrays.toString(sum) + "";
                    arrayList.add(build.clone());
                    build = null;
            }
        }
    }
    public HashMap scanByList(String[] list) {
        HashMap map = new HashMap();
        int[] indexList = new int[list.length];
        for (int i = 0;i < list.length;i++) {
            indexList[i] = dimensionIndex.get(list[i]);
        }
        for (int i = 0;i < dataList.size();i++) {
            String[] r = dataList.get(i);

            if (map.containsKey(r[indexList[0]])) {
                HashMap subMap = (HashMap) map.get(r[indexList[0]]);
                insertInto(subMap,r,1,list,indexList);
            } else {
                HashMap subMap = new HashMap();
                insertInto(subMap,r,1,list,indexList);
                map.put(r[indexList[0]],subMap);
            }
        }
        return map;
    }
    public void insertInto(Object root,String[] data,int level,String[] list,int[] indexList) {
        if (level < list.length) {
            if (isMeasure[indexList[level]]) {
                Double[] numeric = new Double[indexList.length - level];
                for (int i = level;i < indexList.length;i++) {
                    numeric[(indexList.length - i) - 1] = Double.valueOf(data[indexList[i]]);
                }
                ((ArrayList<Double[]>) root).add(numeric);
                //(ArrayList<String[]>)root.
                    /*
                    ArrayList<String[]> map = (ArrayList<String[]>) root.get(data[indexList[level]]);
                    map.add(data);

                     */
                //insertInto(map,data,level+1,list,indexList);
            } else{
                if (((HashMap)root).containsKey(data[indexList[level]])) {
                    Object map = ((HashMap)root).get(data[indexList[level]]);
                    insertInto(map,data,level+1,list,indexList);
                } else {
                    Object map = new HashMap();
                    if (level + 1 <list.length) {
                        if (isMeasure[indexList[level + 1]]) {
                            map = new ArrayList<Double>();
                        }
                    }
                    insertInto(map,data,level+1,list,indexList);
                    ((HashMap)root).put(data[indexList[level]],map);
                }
            }

            //System.out.println(spaceBuilder(level) + data[indexList[level]]);
        }
    }
    /*
    public void insertInto(HashMap root,String[] data,int level,String[] list,int[] indexList) {
        if (level < list.length) {
            if (root.containsKey(data[indexList[level]])) {
                if (isMeasure[indexList[level]]) {
                    ArrayList<String[]> map = (ArrayList<String[]>) root.get(data[indexList[level]]);
                    map.add(data);
                    //insertInto(map,data,level+1,list,indexList);
                } else {
                    HashMap map = (HashMap) root.get(data[indexList[level]]);
                    insertInto(map,data,level+1,list,indexList);
                }

            } else {
                if (isMeasure[indexList[level]]) {
                    ArrayList<String[]> map = new ArrayList<String[]>();
                    map.add(data);
                    root.put(data[indexList[level]],map);
                } else {
                    HashMap map = new HashMap();
                    insertInto(map,data,level+1,list,indexList);
                    root.put(data[indexList[level]],map);
                }

            }
            //System.out.println(spaceBuilder(level) + data[indexList[level]]);
        }
    }

     */
/*
    public void scanByList(String[] list) {
        int[] findIndex = new int[list.length];
        for (int i = 0;i < list.length;i++) {
            findIndex[i] = dimensionIndex.get(list[i]);
        }
        HashMap root = new HashMap();
        for (int i = 0;i < dataList.size();i++) {
            if (root.containsKey(list[findIndex[0]])) {
                HashMap map = (HashMap) root.get(list[findIndex[0]]);
            } else {
                root.put(list[findIndex[0]],insert);
            }
        }
    }
    private HashMap insert(HashMap map,String[] data,String[] list,int[] findIndex,int level) {
        if (level == findIndex.length-1) {

        } else {
            map.put(list[findIndex[level]]);
        }
        return;

    }

*/
    public void displayList(String[] list,int i,ArrayList<Integer> sector) {
        if (i == 0) {
            HashMap<String,ArrayList<Integer>> map = getListOfItem(list[i]);
            for (String key:map.keySet()) {
                System.out.println(key);
                displayList(list,i+1,map.get(key));
            }
        } else if (i == list.length) {
            return;
        } else {
            HashMap<String,ArrayList<Integer>> map = getListOfItem(list[i],sector);
            if (isMeasure[dimensionIndex.get(list[i])]){
                int sum = 0;
                for (String key:map.keySet()) {
                    displayList(list,i+1,map.get(key));
                    sum += Double.parseDouble(key);
                }
                System.out.println(spaceBuilder(i) + sum);

            } else {
                for (String key:map.keySet()) {
                    System.out.println(spaceBuilder(i) + key);
                    displayList(list,i+1,map.get(key));
                }
            }

            /*
            HashMap<String,ArrayList<Integer>> map = getListOfItem(list[i],sector);
            System.out.println(map.keySet());
            buildList(list,i+1,map);

             */
        }

    }
    public String spaceBuilder(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < count;i++)
        sb.append("\t");
        return sb.toString();
    }
    public HashMap<String,ArrayList<Integer>> getListOfItem(String dimension,ArrayList<Integer> sector) {
        HashMap<String,ArrayList<Integer>> map = new HashMap();
        int i = dimensionIndex.get(dimension);
        int index = 0;
        for (int dataListIndex:sector) {
            String[] data = dataList.get(dataListIndex);
            if(map.containsKey(data[i])) {
                map.get(data[i]).add(index);
            } else {
                ArrayList<Integer> newArray = new ArrayList();
                newArray.add(index);
                map.put(data[i],newArray);
            }
            index++;
        }
        return map;
    }

    public HashMap<String,ArrayList<Integer>> getListOfItem(String dimension) {
        HashMap<String,ArrayList<Integer>> map = new HashMap();
        int i = dimensionIndex.get(dimension);
        int index = 0;
        for (String[] data:dataList) {
            if(map.containsKey(data[i])) {
                map.get(data[i]).add(index);
            } else {
                ArrayList<Integer> newArray = new ArrayList();
                newArray.add(index);
                map.put(data[i],newArray);
            }
            index++;
        }
        return map;
    }

    /*
    public HashMap<String,ArrayList<String[]>> getListOfItem(String dimension) {
        HashMap<String,ArrayList<String[]>> map = new HashMap<String,ArrayList<String[]>>();
        int index = dimensionIndex.get(dimension);
        for (String[] data:dataList) {
            if(map.containsKey(data[index])) {
                map.get(data[index]).add(data);
            } else {
                ArrayList<String[]> newArray = new ArrayList<>();
                newArray.add(data);
                map.put(data[index],newArray);
            }
        }
        return map;
    }
     */
    public String[] StringSplit(String input) {
        boolean en = true;
        ArrayList<String> output = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < input.length();i++) {
            char c = input.charAt(i);
            if (c == '"') {
                en = !en;
            } else if (c == ',') {
                if (en) {
                    output.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            } else {
                stringBuilder.append(c);
            }
        }
        output.add(stringBuilder.toString());
        stringBuilder.setLength(0);

        return output.toArray(new String[0]);
    }
    public void loadData(String filename) throws Exception {
        dimensionIndex = new HashMap<>();
        FileInputStream fin = new FileInputStream(filename);
        Scanner scan = new Scanner(fin);
        String readline = scan.nextLine();
        String[] dataArray = StringSplit(readline);
        System.out.println(Arrays.toString(dataArray));
        int i = 0;
        for (String data: dataArray) {
            dimensionIndex.put(data,i);
            i++;
        }
        isMeasure = new boolean[dimensionIndex.size()];
        readline = scan.nextLine();
        dataArray = StringSplit(readline);
        i = 0;
        for (String data: dataArray) {
            isMeasure[i] = isNumeric(data);
            //System.out.println(isMeasure[i]);
            i++;
        }
        while (scan.hasNextLine()) {
            readline = scan.nextLine();
            dataArray = StringSplit(readline);
            dataList.add(dataArray);
        }
        scan.close();
        fin.close();

    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static void main(String[] args) throws Exception{
        new DataManager("Superstore.csv");
    }
}
