# Illumio Coding Assignment 2018

## Problem statement
Given a set of firewall rules, a network packet will be accepted by the firewall if and only if the
direction, protocol, port, and IP address match at least one of the input rules. If a rule contains
a port range, it will match all packets whose port falls within the range. If a rule contains an IP
address range, it will match all packets whose IP address falls within the range.
Your job is to implement a Firewall class, whose interface contains two items:
• A constructor, taking a single string argument, which is a file path to a CSV file whose
contents are as described above, e.g, in Ruby, this would be
Firewall.new(“/path/to/fw.csv”).
o Note that you do not need to define a static ‘new’ method – simply use the
constructor syntax in the language that you chose.
o Remember that you may assume that all content in the input file is valid.
• A function, accept_packet, that takes exactly four arguments and returns a boolean:
true, if there exists a rule in the file that this object was initialized with that allows traffic
with these particular properties, and false otherwise.
o direction (string): “inbound” or “outbound”
o protocol (string): exactly one of “tcp” or “udp”, all lowercase
o port (integer) – an integer in the range [1, 65535]
o ip_address (string): a single well-­‐formed IPv4 address.

## Solution
The approach I took is to first add all the records in the list `rules` of type `NetworkInfo`. `NetworkInfo` is a class I created which consists of the following things:
```
String direction;
String protocol;
String port;
String ip;
``` 
After that as we get the inputs to process the requests, do a `for` loop in the list and see if it satisfies all the rules or not. If it does then it is a valid answer else it is an invalid record. The time complexity of the solution is O(N) where N is the number of records which are present in `networks.csv` file. Of course in the worst case if the input record match happens with the last record of the `networks.csv` file then it will have to go till `N` records. There is another way I could think of while coding for this problem is to use `HashMap` and start hashing the values as we process the `networks.csv` file. But the space will go on increasing for the records which has ranges. Why? Because to hash all the records, we will have to hash each and every record present in the required range. For example, if port range is `10-1000` then `990` values which `(1000-10)` will need to be hashed. This will grow space very massively. Therefore, I chose to have a time complexity method here.

## Testing
Due to constraint in time, I was unable to use `JUnit` testing framework due to export errors otherwise that is the best choice. I wrote the code in the maximum modularity level so that each and every line can be unit tested. Quickly I created a class `MyTestClasses` to assert the methods written in class `Firewall`. But as I say again, these all can better be tested via JUnit for bool asserts, exception testing, file testing etc if given more time.

## Team choosing
Platform Team and Policy Team

