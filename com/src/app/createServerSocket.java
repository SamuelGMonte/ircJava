package app;

import java.net.InetAddress;

public class CreateServerSocket {
    private int port;
    private InetAddress ipAddress;

    public int getPort() {
        return port;
    }

    public void setPort(int p) {
        this.port = p;
    }

    public InetAddress getIp() {
        return ipAddress;
    }

    public void setIp(InetAddress i) {
        this.ipAddress = i;
    }

}
