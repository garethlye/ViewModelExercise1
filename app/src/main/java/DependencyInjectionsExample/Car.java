package DependencyInjectionsExample;

import javax.inject.Inject;

/**
 * Created by garethlye on 06/02/2017.
 */

public class Car {

    @Inject Engine mEngine;
    @Inject Wheels mWheels;
}
