package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class RaceStartedEvent extends OmegaEvent {

    private final boolean timeTrial;
    private final String race_start_time;

    public RaceStartedEvent(String race_start_time) {
        this(race_start_time, false);
    }

    public RaceStartedEvent(String race_start_time, boolean timeTrial) {
        super("RACE_STARTED");
        this.race_start_time = race_start_time;
        this.timeTrial = timeTrial;
    }

    public boolean isTimeTrial() {
        return timeTrial;
    }

    public String getRaceStartTime(){
        return race_start_time;
    }

}
