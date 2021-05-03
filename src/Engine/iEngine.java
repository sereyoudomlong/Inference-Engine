package Engine;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class iEngine {

    public List<String> _kb = new ArrayList<String>();
    public String _query;
    public List<String> _var = new ArrayList<String>();

    public iEngine(String KB, String Query){
        _query = Query.replaceAll(" ", "");
        InitKB(KB);
        InitVar();
    }

    public void InitKB (String KB){
        // Delete all space in KB
        KB = KB.replaceAll(" ", "");
        // Split the KB by ; and add to the list
        String[] tempKB = KB.split(";");
        for(int i = 0; i < tempKB.length; i++){
            _kb.add(tempKB[i]);
        }
    }

    public void InitVar(){
        // goes through all element in _kb
        for (int i = 0; i < _kb.size(); i++){
            //split them by '=>' e.g. a&b=>p3 would become [a&b,p3]
            String[] temp1 = _kb.get(i).split("=>");
            // for each string after split
            for (String var1 : temp1){
                // split them again if they have '&' e.g. a&b would become [a, b]
                String[] temp2 = var1.split("&");
                // for each string after the second split
                for (String var2 : temp2){
                    // check if the string is in list of variable
                    if (_var.contains(var2))
                        continue;               // if true skip
                    else
                        _var.add(var2);         //else add it in
                }
            }
        }
    }




    // testing function
    public void printInfo(){
        System.out.println("Knowledge Base: ");
        for (int i = 0; i < _kb.size(); i++){
            System.out.println(_kb.get(i));
        }

        System.out.println("Query: ");
        System.out.println(_query);

        System.out.println("Variables: ");
        for (int i = 0; i < _var.size(); i++){
            System.out.println(_var.get(i));
        }
    }

}
