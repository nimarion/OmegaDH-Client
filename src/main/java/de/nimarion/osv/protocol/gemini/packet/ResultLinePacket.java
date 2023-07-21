package de.nimarion.osv.protocol.gemini.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.gemini.GeminiPacket;
import de.nimarion.osv.protocol.gemini.event.IntermediateTimeEvent;
import de.nimarion.osv.protocol.gemini.event.ResultEvent;

/**
 * Name: Line
 * Group: RESULT_HHMMSS
 * RESULT_HMMSSD
 * RESULT_MMSSDC
 * RESULT_MSSDCM
 * RESULT_HHMMSS_BIB
 * RESULT_HMMSSD_BIB
 * RESULT_MMSSDC_BIB
 * RESULT_MSSDCM_BIB
 * Message: <SOH><DC4>S00<STX><BS>%Z%0 %2<STX>%1 <EOT>
 * <SOH><DC4>S01<STX><BS>%Z%0 %2<STX>%1 <EOT>
 * <SOH><DC4>S02<STX><BS>%Z%0 %2<STX>%1 <EOT>
 * <SOH><DC4>S03<STX><BS>%Z%0 %2<STX>%1 <EOT>
 * <SOH><DC4>S00<STX><BS>%Z%0<STX>%1 <EOT>
 * <SOH><DC4>S01<STX><BS>%Z%0<STX>%1 <EOT>
 * <SOH><DC4>S02<STX><BS>%Z%0<STX>%1 <EOT>
 * <SOH><DC4>S03<STX><BS>%Z%0<STX>%1 <EOT>
 * Parameter: %0=RANK
 * %2=LANE
 * %1=TIME
 * %0=BIB
 * %1=TIME
 * %Z=LF (Repeat) -> Amount of LF indicates line number
 */
// TODO: NetTIme zero check firstdata -> test
public class ResultLinePacket extends GeminiPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 20, 83, 48 };

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
        if (data[4] >= 48 && data[4] <= 51) {
            String dataString = new String(data);
            String firstdata = dataString.substring(dataString.indexOf('\u0008') + 1, dataString.lastIndexOf('\u0002'));
            // blank -> NetTimePacket
            return !firstdata.isBlank();
        }
        return false;
    }

    @Override
    public Event handleData(String data) {
        int indexStartTime = data.lastIndexOf('\u0002');
        String time = data.substring(indexStartTime).trim();
        if (time.isEmpty()) {
            return null;
        }
        String firstdata = data.substring(data.indexOf('\u0008') + 1, indexStartTime);
        int line = countLinefeeds(firstdata) + 1;
        if ((int) firstdata.charAt(0) == 10) {
            firstdata = firstdata.substring(firstdata.lastIndexOf("\n") + 1);
        } else if ((int) firstdata.charAt(0) == 73) { // I
            int intermediateCount = Integer.parseInt(firstdata.substring(1, 3).trim());
            return new IntermediateTimeEvent(intermediateCount, time);
        } else {
            firstdata = firstdata.substring(firstdata.lastIndexOf('\u0008') + 1);
        }
        boolean rankLaneTimeResult = firstdata.length() == 3 && (int) firstdata.charAt(1) == 32;
        if (rankLaneTimeResult) {
            int rank = Character.getNumericValue(firstdata.charAt(0));
            int lane = Character.getNumericValue(firstdata.charAt(2));
            return new ResultEvent(line, rank, lane, time);
        }
        String bib = firstdata.trim();
        return new ResultEvent(line, bib, time);
    }

    private int countLinefeeds(String data) {
        int line = 0;
        for (int i = 0; i < data.length(); i++) {
            if ((int) data.charAt(i) != 10) {
                break;
            }
            line++;
        }
        return line;
    }

}
