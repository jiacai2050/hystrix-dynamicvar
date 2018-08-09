package hystrix_dynamicvar;


import clojure.lang.IPersistentMap;
import clojure.lang.PersistentHashMap;
import clojure.lang.Var;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ClojureDynamicVarCallable implements Callable {

    private IPersistentMap dynamicVarBindings;
    private Callable actual;

    public ClojureDynamicVarCallable(List<Var> dynamicVars, Callable actual) {
        this.actual = actual;
        this.dynamicVarBindings = PersistentHashMap.create();
        // loadFactor chose to 1 to prevent rehashing at the cost of O(n) of put/get
        // this is fine since it's used only once
        // https://stackoverflow.com/questions/15844035/best-hashmap-initial-capacity-while-indexing-a-list
        Map bindings = new HashMap(dynamicVars.size(), 1);
        for (Var v : dynamicVars) {
            bindings.put(v, v.get());
        }
        this.dynamicVarBindings = PersistentHashMap.create(bindings);
    }

    @Override
    public Object call() throws Exception {
        Var.pushThreadBindings(dynamicVarBindings);
        try {
            return actual.call();
        } finally {
            Var.pushThreadBindings(dynamicVarBindings);
        }
    }
}
