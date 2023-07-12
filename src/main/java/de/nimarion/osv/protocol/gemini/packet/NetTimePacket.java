package de.nimarion.osv.protocol.gemini.packet;

import de.nimarion.osv.protocol.gemini.GeminiPacket;

/**
 * Name: NetTimeA, NetTimeB
 * Group:   RACING_HHMMSS
            RACING_HMMSSD
            RACING_MMSSDC
            RACING_MSSDCM
    Message: <SOH><DC4>S00<STX><BS>   <STX>%1<EOT>
            <SOH><DC4>S01<STX><BS>   <STX>%1<EOT>
            <SOH><DC4>S02<STX><BS>   <STX>%1<EOT>
            <SOH><DC4>S03<STX><BS>   <STX>%1<EOT>
            -- NetTimeB --
            <SOH><DC4>S00<STX><BS><LF>   <STX>%1<EOT>
            <SOH><DC4>S01<STX><BS><LF>   <STX>%1<EOT>
            <SOH><DC4>S02<STX><BS><LF>   <STX>%1<EOT>
            <SOH><DC4>S03<STX><BS><LF>   <STX>%1<EOT>
 */
public class NetTimePacket extends GeminiPacket {
    
}
