package org.exercises.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Tech Interview for Howdy
 */
@Slf4j
public class FindValueTree {

    // Given a tree in the following format: [1, [2, null, null], [4, null, null]]
    public static void main(String[] args) {
        List<Object> tree = List.of(1, Arrays.asList(2, null, null), Arrays.asList(4, null, null));
        log.info("Found: {}", isValueInTree(tree, 4));
    }

    /*
    Traverse the tree recursively using DFS algorithm.
    Start with the head and see if its null, if true, then return false.
    Then, if the tree head is not null, check if its value is equal to the searched value. Return true if they are.
    If the searched value is not equal to the node value, then call the method again asking the left subtree to do the same.
    If the searched value is not found in the left portion, then look in the right subtrees.
    Return true if the value is found, false otherwise.
    Look that we are using a short-circuit OR operator, so the right subtree evaluation is omitted if the value is found
    in the left subtree,
     */
    @SuppressWarnings("unchecked")
    static boolean isValueInTree(List<?> tree, int value) {
        if (Objects.isNull(tree)) {
            return false;
        }

        if (tree.get(0) instanceof Integer && tree.get(0).equals(value)) {
            return true;
        }

        return isValueInTree((List<Object>) tree.get(1), value) ||
                isValueInTree((List<Object>) tree.get(2), value);
    }
}
