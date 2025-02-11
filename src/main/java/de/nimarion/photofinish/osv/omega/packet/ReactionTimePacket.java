package de.nimarion.photofinish.osv.omega.packet;

import java.util.Arrays;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.ReactionTimeEvent;

/**
 * Name: [ReactionTime]
 * Group: [RACING, JUDGING, RESULT]
 * Message: <SOH><DC3>DVRTC<STX> 0 %2%3 %1%4<EOT>
 * Placeholder 2: RACE_BIB
 * Placeholder 3: RACE_LANE
 * Placeholder 4: RACE_REACTION_TIME
 * Placeholder 1: RACE_REACTION_FLAG
 */
public class ReactionTimePacket extends OmegaPacket {

    public ReactionTimePacket() {
        super("RTC");
    }

    @Override
    public boolean isPacket(byte[] data) {
        if (!super.isPacket(data)) {
            return false;
        }
        return data.length >= 11 && data[11] == 48;
    }

    @Override
    public Event handleData(String dataString) {
        // TODO: Special handling for this packet
        // Same type result intermediate packet but three space + 0 + three space
        // indicate a reaction time packet there <SOH><DC3>DVRTC<STX> 0 %2%3 %1%4<EOT>
        dataString = dataString.substring(8, dataString.length());
        char[] data = dataString.toCharArray();
        int bib = Integer.parseInt(new String(Arrays.copyOfRange(data, 0, 5)).trim());
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 5, 7)).trim());
        String reactionTime = new String(Arrays.copyOfRange(data, 12, 18)).trim();
        String reactionFlag = new String(Arrays.copyOfRange(data, 18, 19)).trim();
        if(reactionTime.isEmpty()) {
            reactionTime = null;
        }
        if(reactionFlag.isEmpty()) {
            reactionFlag = null;
        }
        return new ReactionTimeEvent(bib, lane, reactionTime, reactionFlag);
    }

}
