import java.io.FileNotFoundException;

public class MyTestCases {
    public static void main(String[] args) {
        try {
            Firewall fw = new Firewall("networks.csv");

            System.out.println("Testing method verifyDirection..");
            if(fw.verifyDirection("inbound", "inbound"))
                System.out.println("Asserts true for values inbound and inbound: Test passed");
            if(!fw.verifyDirection("inbound", "outbound"))
                System.out.println("Asserts fail for values inbound and outbound: Test passed");
            System.out.println("");

            System.out.println("Testing method verifyProtocol..");
            if(fw.verifyProtocol("tcp", "tcp"))
                System.out.println("Asserts true for values tcp and tcp: Test passed");
            if(!fw.verifyProtocol("tcp", "udp"))
                System.out.println("Asserts fail for values tcp and udp: Test passed");
            System.out.println("");

            System.out.println("Testing method verifyPort..");
            if(fw.verifyPort("10", 10))
                System.out.println("Asserts true for values 10 and 10: Test passed");
            if(!fw.verifyPort("1", 2))
                System.out.println("Asserts fail for values 1 and 2: Test passed");
            System.out.println("");

            System.out.println("Testing method verifyIP..");
            if(fw.verifyIP("1.2.3.4", "1.2.3.4"))
                System.out.println("Asserts true for IP value 1.2.3.4 and 1.2.3.4: Test passed");
            if(!fw.verifyIP("1.2.3.4", "5.6.7.8"))
                System.out.println("Asserts fail for IP value 1.2.3.4 and 5.6.7.8: Test passed");
            System.out.println("");

        } catch (FileNotFoundException e) {
            System.out.println("Exception occurred");
        }
    }
}