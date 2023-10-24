package app;

import java.net.InetAddress;

public class createServerSocket {
    private int port;
    private InetAddress ipAddress;
    private String ipAddressStr;
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

    public String getIpStr() {
        return ipAddressStr;
    }

    public void setIpStr(String iS) {
        this.ipAddressStr = iS;
    }

}
