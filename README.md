# Simple PostgreSQL connector

This is simple connection module for PostgreSQL which talks with `data` table and allows simple tasks to be done with it.

##How-to run

First, you got to configure the `config.properties` file to be able to connect to your ProgreSQL server
Make sure that `config.properties` is in the same folder as is your runnable jar
(If you have the jar but not the config file, one will be generated for you on your first launch)

Then, you can run the runnable jar from commandline using `java -jar postgres-db-1.0.0.jar`

And now you are up and running

This will launch tomcat on port `8888`

##Adding data

You can add `data` to DB using POST method, accepted input method is JSON.
For adding data you need to POST to `/data`.

For example, you have this running locally and you want to POST "custom data". For this you send `{ "data": "custom data" }` to http://localhost:8888/data
Nothing will be returned

##Searching data

You can search `data` table for strings that have common value as `search` termin has.
For finding data you need to GET from `/data` with value `search`.

For example, when running on localhost, we can use http://localhost:8888/data?search=mydata
The data returned is in JSON

##Removing data

You can remove `data` from table using `/data/remove` with value `remove`. The string must match exactly.

For example, when running on localhost, you could remove data using http://localhost:8888/data/remove?remove=mydata
Nothing will be returned