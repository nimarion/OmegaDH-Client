package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoDataEvent;

/**
 * Name: [SupInfoHeader]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVSCD<STX>%0%1%2%3%7%4%5%6%8%9%A%B%C%D%E%F%G%H<EOT>
 */
public class SupplementaryInfoDataPacket extends OmegaPacket {

    public SupplementaryInfoDataPacket() {
        super("SCD");
    }

    @Override
    public Event handleData(String data) {
        return new SupplementaryInfoDataEvent(data);
    }

}
