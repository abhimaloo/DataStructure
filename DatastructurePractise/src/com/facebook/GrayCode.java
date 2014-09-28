package com.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class GrayCode {

    public static List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for (int i = 0; i < n; i++) {
            int size = ret.size();
            for (int j = size - 1; j >= 0; j--)
                ret.add(ret.get(j) + size);
        }
        return ret;
    }

    public static void main(String[] args) {
        for(int i: grayCode(4)) {
            System.out.println(i);
        }
    }

}
