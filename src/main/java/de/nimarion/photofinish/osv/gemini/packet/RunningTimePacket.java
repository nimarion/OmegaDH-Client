package de.nimarion.photofinish.osv.gemini.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.gemini.GeminiPacket;
import de.nimarion.photofinish.osv.gemini.event.RunningTimeEvent;

/**
 * Name, RunningTimeA, RunningTimeB, DayTime
 * Group:
 * RunningTime_HHMMSS;RunningTime_HMMSSD;RunningTime_MMSSDC;RunningTime_MSSDCM
 * Message: <SOH><DC4>R00<STX><BS> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R01<STX><BS> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R02<STX><BS> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R03<STX><BS> <STX>hh:mm:ss.d <EOT>
 * -- RunningTimeB --
 * <SOH><DC4>R00<STX><BS><LF> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R01<STX><BS><LF> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R02<STX><BS><LF> <STX>hh:mm:ss.d <EOT>
 * <SOH><DC4>R03<STX><BS><LF> <STX>hh:mm:ss.d <EOT>
 */
public class RunningTimePacket extends GeminiPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 20, 82 };

    @Override
    public boolean isPacket(byte[] data) {
        if (data.length < PACKET_IDENTIFIER.length) {
            return false;
        }
        for (int i = 0; i < PACKET_IDENTIFIER.length; i++) {
            if (data[i] != PACKET_IDENTIFIER[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Event handleData(String data) {
        int startIndex = data.lastIndexOf('\u0002');
        int lastIndex = data.lastIndexOf('\u0004');
        String time = data.substring(startIndex, lastIndex).trim();
        return new RunningTimeEvent(time);
    }
}
