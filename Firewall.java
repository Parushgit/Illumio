import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Firewall {

    public class NetworkInfo {
        String direction;
        String protocol;
        String port;
        String ip;
    
        public NetworkInfo(String direction, String protocol, String port, String ip) {
            this.direction = direction;
            this.protocol = protocol;
            this.port = port;
            this.ip = ip;
        }
    }

    static List<NetworkInfo> rules = new ArrayList<NetworkInfo>();
    public static void main(String[] args) {
        try {
            Firewall fw = new Firewall("pathToRulesFile.csv");

            // Valid test cases taken from coding assessment
            System.out.println(fw.acceptPacket("inbound", "tcp", 80, "192.168.1.2"));
            System.out.println(fw.acceptPacket("inbound", "udp", 53, "192.168.2.1"));
            System.out.println(fw.acceptPacket("outbound", "tcp", 10234, "192.168.10.11"));
            System.out.println(fw.acceptPacket("inbound", "tcp", 81, "192.168.1.2"));
            System.out.println(fw.acceptPacket("inbound", "udp", 24, "52.12.48.92"));
        }catch (FileNotFoundException e) {
            System.out.println("Exception occurred");
        }
    }

    public Firewall(String filePath) throws FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for(String line; (line = br.readLine()) != null;) { 
                String [] tokens = line.split(",");
                NetworkInfo rule = new NetworkInfo(tokens[0], tokens[1], tokens[2], tokens[3]);
                rules.add(rule);
            }
        }
        catch (IOException e) {
            System.out.println("Exception occurred");
        }
    }

    public boolean verifyDirection(String rule, String direction) {
        return direction.equals(rule);
    }

    public boolean verifyProtocol(String rule, String protocol) {
        return protocol.equals(rule);
    }

    public boolean verifyPort(String rule, int port) {
        if(rule.contains("-")) {
            String[] portRanges = rule.split("-");
            String minPortStr = portRanges[0].trim();
            String maxPortStr = portRanges[1].substring(1, portRanges[1].length());

            int minPort = Integer.parseInt(minPortStr);
            int maxPort = Integer.parseInt(maxPortStr);
            if(port >= minPort && port <= maxPort)
                return true;
            return false;
        }
        return Integer.parseInt(rule) == port;
    }

    public boolean verifyIP(String rule, String ip) {
        if(rule.contains("-")) {
            String [] ipAddressRanges = rule.split("-");
            String minIPStr = ipAddressRanges[0].trim();
            String maxIPStr = ipAddressRanges[1].substring(1, ipAddressRanges[1].length());

            long ipAddressMin = Long.parseLong(minIPStr.replaceAll("\\.", ""));
            long ipAddressMax = Long.parseLong(maxIPStr.replaceAll("\\.", ""));
            long longIP = Long.parseLong(ip.replaceAll("\\.", ""));
            if(longIP >= ipAddressMin && longIP <= ipAddressMax)
                return true;
            return false;
        }
        return rule.equals(ip);
    }

    public boolean acceptPacket(String direction, String protocol, int port, String ip) {
        for (NetworkInfo rule : rules) {
            if(!verifyDirection(rule.direction, direction))
                continue;
            if(!verifyProtocol(rule.protocol, protocol))
                continue;
            if(!verifyPort(rule.port, port))
                continue;
            if(!verifyIP(rule.ip, ip))
                continue;
            return true;
        }
        return false;
    }
}