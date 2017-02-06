package DependencyInjectionsExample;
import javax.inject.Inject;

/**
 * Created by garethlye on 06/02/2017.
 */

public class Engine implements Fuel{

    private final Oil mOil;

    @Override
    public void Fuel() {
    }

    @Inject
    Engine(Oil mOil){
        this.mOil = mOil;
    }


}
