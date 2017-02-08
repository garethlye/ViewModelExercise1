package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Speaker_gps {

    private boolean speaker;

    public Speaker_gps(){
        speaker = true;
    }

    public void disableSpeaker(){
        speaker = false;
    }

    public void enableSpeaker(){
        speaker = true;
    }

}
