# hotels
Hotels repository for DPIT project backend part
There are for moment some credentials that should be updated by every developer:
In pom.xml you should use your user and password in flyway-maven-plugin 
In application.properries you should change the values too.
IMPORTANT: When you change something on database, please add the SQL changes also in 
src/main/resources/db.migration folder. The new file should look like V{number}__{name}.sql where
{number} is the next number in the sequence and {name} is something that express what the sql file contains. 
