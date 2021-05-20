package Engine;

import sun.security.util.ArrayUtil;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TruthTable extends Method{
    public String _alpha;
    public List<String> _var;
    public boolean _tTable [][];

    public TruthTable(List<String> kb, String alpha, List<String> var){
        super(kb);
        _alpha = alpha;
        _var = var;
        InitTable(_var.size());
    };

    // Initiating a new truth table which take number of variable as parameter

    /**
     * I have used binary to determine the boolean value of each variables. For easy debugging
     * I will use 0 in binary to represent true instead of false. By doing this the table while
     * in debugging(IntelliJ) will show as follow:
     *      P   Q
     *      F   F
     *      F   T
     *      T   F
     *      T   T
     * */

    public void InitTable(int varSize){
        int tTableRow = (int)Math.pow(2, varSize);     //The row of the table is 2^ (num of variables)
        _tTable = new boolean[tTableRow][varSize];      // create a boolean table full of false value

        // we populate the table using binary to determine truth and false
        for (int x = 0; x < tTableRow; x++){
            String binary = Integer.toBinaryString(x);
            // since the binary will delete the zero in front of it
            while (binary.length() < varSize){
                binary = '0' + binary;      // we add 0 to determine false
            }

            for (int y = 0; y < binary.length(); y++){
                if (binary.charAt(y) == '0'){   // check if it is 1,
                    _tTable[x][y] = true;       // if yes put value as true
                }
            }
        }
    }

    private Integer getIndexOf(String var) {
        for(int i=0; i<_var.size();i++) {
            if(_var.get(i).equals(var))
                return i;
        }
        return null;
    }

    @Override
    public void Solve() {
        int count = 0;                          // final count of how many time KB|=Alpha
        boolean rowValid;                   // boolean to check if the row is valid depend on KB
        boolean entailment = false;          // a boolean to check if Kb|=Alpha

        // check every row in the table
        for (int i = 0; i < _tTable.length; i++){
            rowValid = true;
            for (String k : _kb){
                String[] kbParts = k.split("=>");
                if (kbParts.length == 2){   // check if there is implication
                    // if there is implication, validate it (p => q is false if p is true and q is false)
                    if (((validateAnd(kbParts[0],i) == true) && (validateAnd(kbParts[1],i)) == false )){
                        rowValid = false;
                    }
                }else{  // if there is none
                    if (!validateAnd(k,i)){
                        rowValid = false;
                    }
                }
            }
            if (rowValid){
                if(validateEntail(_alpha, i)){  //check if alpha is valid in this row
                    count++;                    // if so add 1 to count
                    entailment = true;
                }
            }

        }
        // print output
        if (entailment){
            System.out.println("YES: " + count);
        }else{
            System.out.println("NO");
        }

    }

    // These 2 function below is for testing purposes
    public void printTable(){
        for (int i = 0; i < _tTable.length; i++){
            for (int j = 0; j < _tTable[i].length; j++){
                System.out.print(String.valueOf(_tTable[i][j]) + ' ');
            }
            System.out.print("\n");
        }
    }

    public void printRow(int index){
        for(int j = 0; j < _tTable[index].length; j++){
            System.out.print(String.valueOf(_tTable[index][j]) + ' ');
        }
        System.out.print('\n');
    }

    // function to validate &
    public boolean validateAnd(String kb, int index){
        String[] kbVars = kb.split("&");
        Map<String,Integer> vars = new HashMap<String,Integer>();
        for (String v : kbVars){
            vars.put(v, getIndexOf(v));
        }
        for (Map.Entry<String,Integer> entry : vars.entrySet()){
            if(_tTable[index][entry.getValue()] == false){  // if any of the variables is false
                return false;                               // return false
            }
        }
        return true;                                        // otherwise return true
    }

    // validate entailment, (kb|=aplha, if and only if alpha is true in all worlds where kb is true
    public boolean validateEntail(String alpha, int index){
        if (alpha.contains("~")){
            alpha = alpha.substring(1);
            if (_tTable[index][getIndexOf(alpha)] == false){
                return true;
            }else{
                return false;
            }
        }else{
            if (_tTable[index][getIndexOf(alpha)] == true){
                return true;
            }else{
                return false;
            }
        }
    }

}
