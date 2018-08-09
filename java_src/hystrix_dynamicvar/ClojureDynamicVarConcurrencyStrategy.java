package hystrix_dynamicvar;

import clojure.lang.Var;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.List;
import java.util.concurrent.Callable;

public class ClojureDynamicVarConcurrencyStrategy extends HystrixConcurrencyStrategy {
    private List<Var> dynamicVars;

    public ClojureDynamicVarConcurrencyStrategy(List<Var> dynamicVars) {
        this.dynamicVars = dynamicVars;
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new ClojureDynamicVarCallable(dynamicVars, callable);
    }
}
