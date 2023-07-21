package de.nimarion.osv.protocol.gemini.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.gemini.GeminiPacket;
import de.nimarion.osv.protocol.gemini.event.EndOfAcquisitionEvent;
import de.nimarion.osv.protocol.gemini.event.StartReadyEvent;

/**
 * Name: NetTimeA, NetTimeB
 * Group: RACING_HHMMSS
 * RACING_HMMSSD
 * RACING_MMSSDC
 * RACING_MSSDCM
 * Message: <SOH><DC4>S00<STX><BS> <STX>%1<EOT>
 * <SOH><DC4>S01<STX><BS> <STX>%1<EOT>
 * <SOH><DC4>S02<STX><BS> <STX>%1<EOT>
 * <SOH><DC4>S03<STX><BS> <STX>%1<EOT>
 * -- NetTimeB --
 * <SOH><DC4>S00<STX><BS><LF> <STX>%1<EOT>
 * <SOH><DC4>S01<STX><BS><LF> <STX>%1<EOT>
 * <SOH><DC4>S02<STX><BS><LF> <STX>%1<EOT>
 * <SOH><DC4>S03<STX><BS><LF> <STX>%1<EOT>
 */
public class NetTimePacket extends GeminiPacket {

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
                        String firstdata = dataString.substring(dataString.indexOf('\u0008') + 1,
                                        dataString.lastIndexOf('\u0002'));
                        // NetTimePacket
                        return firstdata.isBlank(); // else ResultLinePacket with bib rank data
                }
                return false;
        }

        @Override
        public Event handleData(String data) {
                String time = data.substring(data.lastIndexOf('\u0002')).trim();
                if (time.isEmpty()) {
                        return new EndOfAcquisitionEvent();
                }
                if (time.equals("0.0")) {
                        return new StartReadyEvent();
                }
                boolean netTimeA = ((int) data.charAt(7)) == 32;
                System.out.println("NetTimeA: " + netTimeA + " Time: " + time);
                return null;
        }

}
