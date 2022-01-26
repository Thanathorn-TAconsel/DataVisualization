import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DataManager {
    HashMap<String,Integer> rowData = new HashMap<String, Integer>();
    HashMap<String,Integer> columnData = new HashMap<String, Integer>();
    ArrayList<String[]> dataList = new ArrayList();
    HashMap<String ,Integer> dimensionIndex = new HashMap();
    DataManager() throws Exception {
        System.out.println("Intallizing");
        loadData("Superstore.csv");
        System.out.println("Loaded");
        System.out.println(dimensionIndex.keySet());
        /*
        HashMap<String,ArrayList<Integer>> map = getListOfItem("Category");
        for (String key: map.keySet()) {
            ArrayList<Integer> sector = map.get(key);
            System.out.println("Key : " + key);
            HashMap<String,ArrayList<Integer>> sectorMap = getListOfItem("Sub-Category",sector);
            System.out.println(sectorMap.keySet());
        }

         */
        String s[] = {"Category","Sub-Category","Quantity"};
        buildList(s,0,null);
        //System.out.println(map.keySet());
        /*
        for (String r:map.keySet()) {
            ArrayList<Integer> array = map.get(r);
            System.out.println("--------------------------------SET--" + r);
            for (int i = 0;i < array.size();i++) {
                System.out.println(Arrays.toString(dataList.get(array.get(i))));
            }
        }

         */
    }
    public void buildList(String[] list,int i,ArrayList<Integer> sector) {
        if (i == 0) {
            HashMap<String,ArrayList<Integer>> map = getListOfItem(list[i]);
            for (String key:map.keySet()) {
                System.out.println(key);
                buildList(list,i+1,map.get(key));
            }
        } else if (i == list.length) {
            return;
        } else {
            HashMap<String,ArrayList<Integer>> map = getListOfItem(list[i],sector);
            for (String key:map.keySet()) {
                System.out.println(spaceBuilder(i) + key);
                buildList(list,i+1,map.get(key));
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
    public void loadData(String filename) throws Exception {
        FileInputStream fin = new FileInputStream(filename);
        Scanner scan = new Scanner(fin);
        String readline = scan.nextLine();
        String[] dataArray = readline.split(",");
        int i = 0;
        for (String data: dataArray) {
            dimensionIndex.put(data,i);
            i++;
        }
        while (scan.hasNextLine()) {
            readline = scan.nextLine();
            dataArray = readline.split(",");
            dataList.add(dataArray);
        }
        scan.close();
        fin.close();

    }
    public static void main(String[] args) throws Exception{
        new DataManager();
    }
}
