# finalproject-ems-dataservice

<img src="/batchservice/Picture1.png">

<img src="/batchservice/Picture1.png" width=200>

## User Story/Goal
1. Build an enterprise message system to streamline data publishing and consuming 
2. Build out data service framework by leveraging message system to stream data into cloud and provide centralized data service.

## Issue Statement:  
### Enterprise message system to 
1. provide a centralize message structure for message queue
2. potentially replace current EMS system 

### Centralized Data Service to Resolve Below Issues
1.  Different system has to 
    1.1 build ETL load data 
    1.2 lookup data 
    1.3 Persist data in different system
2.  Replace Out date Repository  ( ie MARRS etc.)
3.  Provide a cloud solution to centralized reference data service or other service

## Epic/User Story: 
1. Batch/Event Service to processing based on file ( or event)   
2. Producer Service	Receive message from Batch service 	(Provide a standard plumbing on streaming data into cloud or other data stored )	
3. Consumer Service	Receive message from Consumer (Provide a standard plumbing on streaming data into cloud or other data stored)
4. Hosted Data in Centralized DBStore (in Cloud)  Data Save Into Cloud DB	Post Consumer Received Data into Cloud DB	
5. Provide Centralized API to search & return a data set: Payer data search & Lookup
6. Kafka Dashboard/UI - for monitoring/end to end control/validation 		
7. Service Integration / CICD			

## Technology/Practice:  
Kafka Message Service, GCP (Postgres, NOsql, External Table?), Docker, GITHub, CICD, Golang and Java, SpringBoot
TDD, Functional Programming, DevOps, XP, Pair Programming , CPI, 

## Stretch Goal: Topic2 from event trigger to save as file

## Strategy Goal: 
Build a framework & structure to provide centralized messaging system and provide master/reference data service. 
This message queue can be used for application event queue. 
And it can also be externed to other common data such as  NCPDP,  Medical Code , Provide search & lookup as centralized data service offering.

# doc folder 
Contain documents related to the project.

# Project Planning
1. Scrum/resolution call twice daily at 8:00AM and 5:00PM
2. Epic/tasks/assignment/stage are setup and tracked under this repo's projects tab.

# Development Strategy
1. Will use this project as single source for project & version
2. Create folder for each service 
3. Deploy locally as container to ensure it works first
4. Integrate as needed in various stage
5. Create Brand for each service 
   5.1 Commit frequently as possible
   5.2 Merge when a function, test is working or reach certain stage
6. TDD
