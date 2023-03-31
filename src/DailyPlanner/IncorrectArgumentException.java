package DailyPlanner;

import java.io.IOException;

public class IncorrectArgumentException extends IOException{
    public IncorrectArgumentException(String message) {
        super(message);
    }
}
