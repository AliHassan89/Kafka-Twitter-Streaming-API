## Task ##

+ Connect to the [Twitter Streaming API](https://dev.twitter.com/streaming/overview)
    * Use the following values:
        + Consumer Key: `RLSrphihyR4G2UxvA0XBkLAdl`
        + Consumer Secret: `FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4`
    * The app name is `java-exercise`
    * You will need to login with Twitter
+ Filter messages that track on "bieber"
+ Retrieve the incoming messages for 30 seconds or up to 100 messages, whichever comes first
+ Your application should return the messages grouped by user (users sorted chronologically, ascending)
+ The messages per user should also be sorted chronologically, ascending
+ For each message, we will need the following:
    * The message ID
    * The creation date of the message as epoch value
    * The text of the message
    * The author of the message
+ For each author, we will need the following:
    * The user ID
    * The creation date of the user as epoch value
    * The name of the user
    * The screen name of the user
+ All the above infomation is provided in either SDTOUT or a log file
+ You are free to choose the output format, provided that it makes it easy to parse and process by a machine

## Provided functionality ##

The present project in itself is a [Maven project](http://maven.apache.org/) that contains one class that provides you with a `com.google.api.client.http.HttpRequestFactory` that is authorised to execute calls to the Twitter API in the scope of a specific user.
You will need to provide your _Consumer Key_ and _Consumer Secret_ and follow through the OAuth process (get temporary token, retrieve access URL, authorise application, enter PIN for authenticated token).
With the resulting factory you are able to generate and execute all necessary requests.
If you want to, you can also disregard the provided classes or Maven configuration and create your own application from scratch.

# Solution

- The application is created as a REST application.
- In order to make sure smooth scalability and fault tolerance Kafka streaming is used to produce incoming stream of tweets, on the Kafka consumer side the tweets are consumed and displayed in console.
- For now only one consumer and one producer does the job. As per kafka api we can easily introduce multiple consumers to horizontally scale the application.
- SpringBoot Kafka client has been used. 
- Json serialization and deserialization is used to send the data object from producer to consumer. 

# Running the application
- In order to run the application you need to start following 
   * Zookeeper
   * Kafka
   
Once started start the application in any IDE by clicking run
In order to make a call to the GET endpoint use browser or SOAP UI or POSTMAN to invoke the call on localhost on port 50177.
The endpoint is /v1/api/tweet/

http://localhost:50177/v1/api/tweet/

OR

use the Dockerfile provided which will run zookeeper, kafka and start off the application on port 50177.

# Application.yml properties
- Twitter consumerKey and consumerSecret are configured here
- localhost port is configured here. In order to make calls to the application using different port, please change it here
- crawler-properties includes
    * keywords: the words which needs to be looked up in tweets. any number of keywords can be defined. Keywords should be separated by comma ","
    * maxRecordsLimit: the maximum number of records until the kafka producer and consumer keeps running.
    * maxTimeLimit: the maximum time limit in milliseconds until the kafka producer and consumer keeps running.
- kafka-properties
    * bootstrap-servers: the bootstrap server config for kafka.
