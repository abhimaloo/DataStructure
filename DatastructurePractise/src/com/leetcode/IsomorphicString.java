package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abhimaloo on 9/24/14.
 */
public class IsomorphicString {

    class Mapping {
        public Character c ;
        public List<Integer> positions ;

        Mapping(Character c, List<Integer> positions) {
            this.c = c;
            this.positions = positions;
        }
    }

    public boolean isIsomorphic(String a, String b) {
       if(a.length() != b.length()) {
           return false;
       }
       List<Mapping> aMappings = getMappings(a);
       List<Mapping> bMappings = getMappings(b);
       if(aMappings.size() != bMappings.size()){
           return false;
       }
       for(int i = 0;i < aMappings.size(); i++) {
          Mapping amap = aMappings.get(i);
          Mapping bmap = bMappings.get(i);
          if(amap.positions.size() != bmap.positions.size()){
              return false;
          }
          for( int j = 0; j< amap.positions.size(); j++){
              if(amap.positions.get(i) != bmap.positions.get(i)){
                  return false;
              }
          }
       }
        return true;
    }


    public List<Mapping> getMappings(String a) {
        List<Mapping> result = new ArrayList<>();
        if(a == null || a.isEmpty()) {
            return result;
        }

        Map<Character, List<Integer>> map = new LinkedHashMap<>();
        for( int i = 0; i < a.length(); i++) {
            if(!map.containsKey(a.charAt(i))) {
                map.put(a.charAt(i), new ArrayList<Integer>());
            }
            map.get(a.charAt(i)).add(i);
        }

        for(Character c : map.keySet()) {
            result.add(new Mapping(c, map.get(c)));
        }

        return result;
    }


    public static void main(String[] args) {
        IsomorphicString s = new IsomorphicString();
        System.out.println(s.isIsomorphic("foo", "app"));
    }


}
