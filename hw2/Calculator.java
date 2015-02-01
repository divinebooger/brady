import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public EquationList superList = new EquationList(null,0,null);

    public int add(int x, int y) {
        int a = (x & y) << 1;
        int b = (x ^ y); 
        int p,q;
        while(a != 0){
            p = a;
            q = b;
            a = (p & q) << 1;
            b = (p ^ q);
        }
        return b;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        int p = 1;
        int q = y;
        int r = x;
        int total = 0;
        while(q != 0){
            if ((y & p)!= 0){
                total = add(total, r);
            }
            p = p<<1;
            q = q>>>1;
            r = r<<1;
        }
        return total;
    }
    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        EquationList semiList = new EquationList(equation,result,null);
        EquationList tempList = superList;
        while(tempList.next != null){
            tempList = tempList.next;
        }
        tempList.next = semiList;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList tempList = superList.next;
        int length = 0;
        while(tempList != null){
            length += 1;
            tempList = tempList.next;
        }
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList x = null;
        while(n!=0 && superList.next != null){
            EquationList tempList = superList.next;
            while(tempList.next!=x){
                tempList = tempList.next;
            }
            System.out.println(tempList.equation + " = " + Integer.toString(tempList.result));
            n = n - 1;
            x = tempList;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList tempList = superList;
        while(tempList.next!=null){
            if(tempList.next.next != null){
                tempList = tempList.next;
            }
            else{
                tempList.next = null;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        superList.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        EquationList tempList = superList.next;
        while(tempList != null){
            total = total + tempList.result;
            tempList = tempList.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total = 1;
        EquationList tempList = superList.next;
        while(tempList != null){
            total = total * tempList.result;
            tempList = tempList.next;
        }
        return total;
    }
}