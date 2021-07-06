OJDBC-FULL.tar.gz - JDBC Thin Driver and Companion JARS
========================================================
This TAR archive (ojdbc8-full.tar.gz) contains the 12.2.0.1 release of the Oracle JDBC Thin driver(ojdbc8.jar), the Universal Connection Pool (ucp.jar) and other companion JARs grouped by category. 

(1) ojdbc8.jar (4,036,257 bytes) - 
(SHA1 Checksum: 60f439fd01536508df32658d0a416c49ac6f07fb)
Certified with JDK 8; 

(2) ucp.jar (1,099,788 bytes) - (SHA1 Checksum:860bd44a5a593385464a49590169bee81c4482a9)
 Universal Connection Pool classes for use with JDK 8 -- for performance, scalability, high availability, sharded and multitenant databases.

(3) ojdbc.policy (10,602 bytes) - Sample security policy file for Oracle Database JDBC drivers

======================
Security Related JARs
======================
Java applications require some additional jars to use Oracle Wallets. 
You need to use all the three jars while using Oracle Wallets. 

(4) oraclepki.jar (302,479 bytes) - (SHA1 Checksum: ef6e535d008b82cf4bf3d1831a5c1ee6e18122a8)
Additional jar required to access Oracle Wallets from Java
(5) osdt_cert.jar (203,111 bytes) - (SHA1 Checksum: be4a6d41c20d47e23e3b818c5b56968b225ccfba)
Additional jar required to access Oracle Wallets from Java
(6) osdt_core.jar (292,104 bytes) - (SHA1 Checksum: 47b52cf6525aecdda1feddb4e171386783374c89)
Additional jar required to access Oracle Wallets from Java

=============================
JARs for NLS and XDK support 
=============================
(7) orai18n.jar (1,661,088 bytes) - (SHA1 Checksum: a569c1304c519d5de820daba00d5b8a6a6f798ab) 
Classes for NLS support
(8) xdb6.jar (259,806 bytes) - (SHA1 Checksum: bb2ddfae79de117e2b7098fb457a8886a0caf78e)
Classes to support standard JDBC 4.x java.sql.SQLXML interface (Java SE 8 & Java SE 7).

====================================================
JARs for Real Application Clusters(RAC), ADG, or DG 
====================================================
(9) ons.jar (139,977 bytes) - (SHA1 Checksum: 8f9b5f122cd9485ab1b51ce4fd0857d50cfcf1df)
for use by the pure Java client-side Oracle Notification Services (ONS) daemon
(10) simplefan.jar (29,051 bytes) - (SHA1 Checksum: 5a33df223ad23ab8077791a5a689ca11b404837f)
Java APIs for subscribing to RAC events via ONS; simplefan policy and javadoc

=================
USAGE GUIDELINES
=================
Refer to the JDBC Developers Guide (https://docs.oracle.com/en/database/oracle/oracle-database/12.2/jjdbc/toc.htm) and Universal Connection Pool Developers Guide (https://docs.oracle.com/en/database/oracle/oracle-database/12.2/jjucp/toc.htm)for more details. 
