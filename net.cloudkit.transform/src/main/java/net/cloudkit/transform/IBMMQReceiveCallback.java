package net.cloudkit.transform;

public abstract interface IBMMQReceiveCallback {

    public abstract void execute(byte[] paramArrayOfByte);
}
