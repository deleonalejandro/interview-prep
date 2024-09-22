package org.exercises.amazon;

import java.util.*;

public class RelatedGroup {

    /**
     * Matrix
     * 1 1 0
     * 1 1 0
     * 0 0 1
     * Input ['110', '110', '001']
     */
    private static int findGroups(List<String> related) {
        List<Set<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < related.size(); i++) {
            String row = related.get(i);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '1' && !add(groups, i, j)) {
                    create(groups, i, j);
                }
            }
        }
        return groups.size();
    }

    private static void create(List<Set<Integer>> groups, int i, int j) {
        Set<Integer> newGroup = new HashSet<>();
        newGroup.add(i);
        newGroup.add(j);
        groups.add(newGroup);
    }

    private static boolean add(List<Set<Integer>> groups, int i, int j) {
        for (Set<Integer> group : groups) {
            if (group.contains(i) || group.contains(j)) {
                group.add(i);
                group.add(j);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(findGroups(List.of(
                "11000",
                "11000",
                "00101",
                "00010",
                "00101"
        )));
    }
}
