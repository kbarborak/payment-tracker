# Payment Tracker

## Installation 

All following steps are doing in command line.

### Prerequisites

* JDK 1.8
* JAVA_HOME environment variable pointing to JDK  1.8 (e.g. C:\Program Files\Java\jdk1.8.0_151)
* Maven 3.3 or 3.5

Probably building process works with Java 1.8+ but I cannot guarantee it right now. I did not test it.

### Build

* `cd "your choice"`
* `git clone https://github.com/kbarborak/payment-tracker.git payment-tracker` or simply unzip if you get source code in archive
* `cd payment-tracker`
* `mvn package`

### Run

* `java -jar target/payment-tracker.jar` or `java -jar target/payment-tracker.jar src/test/resources/payments.txt`

## Assumptions

* Application accepts only ISO 4217 currency codes. It also means RMB currency ends up with error. Use CNY instead please. 
* In case that application detects invalid payment format, error message is displayed and application is not terminated (no matter on source type - user input or file).
* If there is an error in file being loaded, nothing is saved to in-memory payment storage.
* Application accepts minor currency unit (e.g. USD 20.30).
* All commands are case-sensitive (currency codes must be upper case, quit command lower case).
* Informational USD conversion works only against set of well known currencies.
