package Engine;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

public class iEngine {

    public List<String> _kb = new ArrayList<String>();
    public String _query;
    public List<String> _var;

    public iEngine(String KB, String Query){
        _query = Query.replaceAll(" ", "");
        InitKB(KB);
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


    // testing function
    public void printInfo(){
        System.out.println("Knowledge Base: ");
        try{
            for (int i = 0; i < _kb.size(); i++){
                System.out.println(_kb.get(i));
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }

        System.out.println("Query: ");
        System.out.println(_query);
    }

}
