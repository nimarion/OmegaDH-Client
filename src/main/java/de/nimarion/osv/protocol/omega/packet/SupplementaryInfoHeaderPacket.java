package de.nimarion.osv.protocol.omega.packet;

import java.util.ArrayList;
import java.util.List;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.SupplementaryInfoHeaderEvent;

/**
 * Name: [SupInfoData]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVSCH<STX>%0<EOT>
 * Placeholder 0: SUP_INFO_HEADER
 */
public class SupplementaryInfoHeaderPacket extends OmegaPacket {

    public SupplementaryInfoHeaderPacket() {
        super("SCH");
    }

    @Override
    public Event handleData(String information) {
        List<String> fields = new ArrayList<>();
        List<Integer> lengths = new ArrayList<>();
        String[] chunks = cutString(information, 6);
        for (String chunk : chunks) {
            String field = chunk.substring(0, 4);
            int length = Integer.parseInt(chunk.substring(4));
            fields.add(field);
            lengths.add(length);
        }
        return new SupplementaryInfoHeaderEvent(fields, lengths);
    }

    public static String[] cutString(String input, int chunkSize) {
        int length = (int) Math.ceil((double) input.length() / chunkSize);
        String[] chunks = new String[length];

        for (int i = 0; i < length; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, input.length());
            chunks[i] = input.substring(start, end);
        }

        return chunks;
    }

}
