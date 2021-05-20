package Engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Method {
    public List<String> _kb = new ArrayList<String>();

    public Method(List<String> kb){
        _kb = kb;
    }

    public abstract void Solve();


}
