package org.exercises.pinterest;

/**
 * Given a list [1 ,3, 5] and a target 15.
 * Find whether adding or multiplying the numbers on the list you can get the target
 */
public class FindTarget {


    /**
     * El problema se complica cuando se busca conservar el orden de operaciones
     * Hay que introducir un nuevo parametro que controle el 'lastOperand':
     * Specifically, when we encounter a multiplication, we must multiply the last operand rather than the entire result.
     * This means we need to keep track of the "last operand" separately,
     * which allows us to correctly handle cases like 1 + (3 * 5).
     *          1
     *        +   *
     *      3       3
     *    +   *    +  *
     *   5     5  5     5
     *
     * @param args
     */
    public static void main(String[] args) {
        findTarget(new int[]{2, 3, 4}, 15);
    }


    private static void findTarget(int[] list, int t) {
        boolean result = hasTarget(list, t, 0, 0, 1);
        System.out.println(result);
    }

    private static boolean hasTarget(int[] list, int t, int idx, int v, int lastOperand) {

        System.out.println(v);
        // Base case
        if (idx >= list.length) {
            return t == v;
        }

        int currentNum = list[idx];
        boolean sum = hasTarget(list, t, idx + 1, v + currentNum, currentNum);
        boolean mult = hasTarget(list, t, idx + 1, (v - lastOperand) + (lastOperand * currentNum), lastOperand * currentNum);

        return sum || mult;
    }
}
