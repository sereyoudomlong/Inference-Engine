package Engine;
import java.util.*;

public class ForwardChaining extends Method{
    private String _query;
    private List<String> trueVars = new ArrayList<String>();    // Store var that is true
    public ForwardChaining(List<String> kb, String query) {
        super(kb);
        _query = query;
    }

    // Print the result
    private void PrintResult() {
        System.out.print("YES:");
        for (String var : trueVars) {
            System.out.print(" " + var);
        }
    }

    // Function to solve the query
    @Override
    public void Solve() {
        boolean goal = false;           // check if goal is reach
        boolean validRule;              // check if the rule is true or not with the current true var array
        while (!goal) {
            // this is to check if we go through the entire kb and added nothing, if so break the loop and print no
            boolean newVarAdded = false;
            for (String rule : _kb){
                String[] a1 = rule.split("=>");
                // if there is no implication
                if (a1.length == 1){
                    if (!trueVars.contains(a1[0])){
                        trueVars.add(a1[0]);        // add it to true var if it is not there already
                        newVarAdded = true;         // set to true
                    }
                }else{
                    // if there is implication
                    validRule = true;
                    String[] a2 = a1[0].split("&"); // check if there is &
                    for (String var : a2){
                        if (!trueVars.contains(var)){   // if any of the var on the left of '=>' is false
                            validRule = false;          // set the rule to false
                        }
                    }
                    if (validRule){         // if rule is valid
                        if (!trueVars.contains(a1[1])){
                            trueVars.add(a1[1]);        // add the right var of '=>' if trueVars doesn't contain it
                            if (a1[1].equals(_query)){  // if the one we just added is query
                                goal = true;            // set goal to true
                                break;                  // and break out of the loop
                            }
                            newVarAdded = true;
                        }
                    }

                }
            }
            // if no new var is added after going through the kb, break the loop
            if (!newVarAdded){
                break;
            }
        }
        if (goal){
            PrintResult();              // print result if goal is true
        }else{
            System.out.println("NO:");  // otherwise print no
        }

    }
}
