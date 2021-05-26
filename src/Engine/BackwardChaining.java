package Engine;

import java.util.ArrayList;
import java.util.List;
public class BackwardChaining extends Method{
    private String _query;
    List<String> validRules = new ArrayList<String>();  // to store rules that is relevant to _query
    List<String> trueVars = new ArrayList<String>();    // to store variables that is true

    public BackwardChaining(List<String> kb, String query){
        super(kb);
        _query = query;
    }

    /**
     * Recursive loop that search backward until a deadend or a true value is found
     */
    private boolean BackwardSearch(String currentQuery) {
        boolean result = false;
        Outer:for (String rule : _kb){
            String[] var = rule.split("=>");              // check implication
            if (var.length == 2){                               // if there is implication
                if(var[1].equals(currentQuery)){                // check if the rule implies currentQuery
                    if (!validRules.contains(rule)){
                        validRules.add(rule);                   // add in validRules if it does
                    }
                    for (String v : var[0].split("&")){
                        if (!trueVars.contains(v)){             // if truevars doesnt contail
                            if (!BackwardSearch(v)){            // recursive loop to find if v is true, if not
                                result = false;                 // result is false;
                                break Outer;                    // goes to next rule;
                            }
                            trueVars.add(v);
                        }else{
                            validRules.add(v);
                        }
                    }
                    result = true;
                }
            }else{
                if (!trueVars.contains(var[0])){
                    trueVars.add(var[0]);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     *  print the result based on if it is true or false
     */

    private void PrintResult(boolean result) {
        if (result) {
            System.out.print("YES:");
            List<String> vars = new ArrayList<String>();    // to store validRules variables
            for (String rule : validRules) {
                String[] parts = rule.split("=>");
                // if no implication add it to vars if it isn't already there
                if (parts.length == 1){
                    if (!vars.contains(parts[0])){
                        vars.add(0, parts[0]);
                    }
                }else{
                    // else add the variable after '=>' first
                    if (!vars.contains(parts[1])){
                        vars.add(0, parts[1]);
                    }
                    // then the variable before '=>'
                    if (!vars.contains(parts[0])){
                        vars.add(0, parts[0]);
                    }
                }

            }
            for (String element : vars) {
                System.out.print(" " + element);
            }
        } else
            System.out.println("NO:");

    }

    /**
     * Solve function to call the 2 function above
     */
    @Override
    public void Solve() {
        boolean result = false;
        result = BackwardSearch(_query);
        PrintResult(result);
    }
}
