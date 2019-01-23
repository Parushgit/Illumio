# Illumio Coding Assignment 2018

## Problem statement
Given a set of firewall rules, a network packet will be accepted by the firewall if and only if the
direction, protocol, port, and IP address match at least one of the input rules. If a rule contains
a port range, it will match all packets whose port falls within the range. If a rule contains an IP
address range, it will match all packets whose IP address falls within the range.
Your job is to implement a Firewall class.

## Main files of the solution
- `Firewall.java` - Start block of the program
- `MyTestCases.java` - Test cases for `Firewall.java`
- `pathToRulesFile.csv` - It contains network rules

## Solution
The approach I took is to first add all the records in the list `rules` of type `NetworkInfo`. `NetworkInfo` is a class I created which consists of the following things:
```
String direction;
String protocol;
String port;
String ip;
``` 
After that as we get the inputs to process the requests, do a `for` loop in the list and see if it satisfies all the rules or not. If it does then it is a valid answer else it is an invalid record. The time complexity of the solution is O(N) where N is the number of records which are present in `pathToRulesFile.csv` file. Of course in the worst case if the input record match happens with the last record of the `pathToRulesFile.csv` file then it will have to go till `N` records. There is another way I could think of while coding for this problem is to use `HashMap` and start hashing the values as we process the `pathToRulesFile.csv` file. But the space will go on increasing for the records which has ranges. Why? Because to hash all the records, we will have to hash each and every record present in the required range. For example, if port range is `10-1000` then `990` values which `(1000-10)` will need to be hashed. This will grow space very massively. Therefore, I chose to have a time complexity method here.

## Testing
Due to constraint in time, I was unable to use `JUnit` testing framework due to export errors otherwise that is the best choice. I wrote the code in the maximum modularity level so that each and every line can be unit tested. Quickly I created a class `MyTestClasses` to assert the methods written in class `Firewall`. But as I say again, these all can better be tested via JUnit for bool asserts, exception testing, file testing etc if given more time.

## Team choosing
Platform Team and Policy Team

