## BUILD
```
mvn clean install
docker-compose build
docker-compose up

```
## Service test
```
http://localhost:8085/
http://localhost:8085/test
```

## POSTMAN

http://localhost:8085/mckesson/produce/
```
Valid Payloads

{
	"appName" : "drgpayer",
	"message": "140|Metropolitan Jewish Health System Elderplan|320AAAAA"
}

{
	"appName" : "drgplan",
	"message": "140|Metropolitan Jewish Health System Elderplan|320AAAAA"
}

{
	"appName" : "ncpdp",
	"message": "140|Metropolitan Jewish Health System Elderplan|320AAAAA"
}



InValid Payloads

{
	"appName" : "xxxxxxx",
	"message": "140|Metropolitan Jewish Health System Elderplan|320AAAAA"
}
```