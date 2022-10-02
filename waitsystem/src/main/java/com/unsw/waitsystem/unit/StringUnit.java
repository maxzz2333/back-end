package com.unsw.waitsystem.unit;

import java.util.ArrayList;
import java.util.List;

public class StringUnit {

    public static List<Integer> getIdList(String Ids) {
        String[] strings = Ids.split(",");
        List<Integer> idList = new ArrayList<>();
        if(strings.length != 1){
            for (String string : strings) {
                if(string.equals("")){
                    continue;
                }
                idList.add(Integer.valueOf(string));
            }
        }
        return idList;
    }


    public static String addId(String ids,Integer newId) {
        ids = ids + "," + newId;
        return ids;
    }

    public static String deleteId(String ids,Integer newId) {
        String s = String.valueOf(newId);
        return ids.replace(s,"");
    }
}
