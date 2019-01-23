package epi;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] a) {

        int[] tree= new int[]{5, 9, 0, 9, 6, 9, 6, 9, 9, 9};

        int i = 0, j = -1;
        int ans = 0 ;
        Map<Integer,Integer> map = new HashMap<>();

        while (i<tree.length&&j<tree.length-1){

            while(j<tree.length-1){
                j++;

//                System.out.println("process "+ i +" " + j);
                map.put(tree[j], map.getOrDefault(tree[j],0) + 1);
                if(map.size()<=2){
                    ans = Math.max(ans, j-i+1);
//                    System.out.println("set ans "+ i +" " + j);
                } else {
                    break;
                }
            }

            while(i<tree.length && map.size() >2){
//                System.out.println("move "+ i +" " + j);
                map.put(tree[i], map.get(tree[i]) - 1);
//                System.out.println("map value "+ i +" " + map.get(tree[i]));
//                System.out.println("map size "+ map.size());

                if( map.size() >2 && map.get(tree[i]) == 0){
                    map.remove(tree[i]);
//                    System.out.println("map size2 "+ map.size());
//                    System.out.println("remove " + i);
                }

                i++;
            }
        }

        System.out.println(ans);
//        return ans;
    }
}
