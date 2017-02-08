package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Compressor_aircond {

    private boolean compressor;

    public Compressor_aircond(){
        compressor = true;
    }

    public void disableCompressor(){
        compressor = false;
    }

    public void enableCompressor(){
        compressor = true;
    }

}
