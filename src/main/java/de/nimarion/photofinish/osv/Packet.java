package de.nimarion.photofinish.osv;

public interface Packet {

    public abstract boolean isPacket(byte[] data);

    public abstract Event handleData(String data);
    
}
