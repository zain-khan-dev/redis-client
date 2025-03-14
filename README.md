# Basic Redis Client in java

## Overall Features 
- Makes a tcp connection to redis client
- Implement the basic redis commands
- Handles different data types
- Implements error handling and timeouts



# Making a TCP connection

Use the socket api from java.net to make a socket connection

Read using the InputStream from the socket whenever we want to read from the redis server. When taking a response from redis server.

Write using the OutStream to the socket whenever we want to send data to the redis server that is when executing a comand

We will explore connection pooling to avoid the expensive operation of establishing a socket connection on each redis operation.



# Serializing the Input and Deserializing the output.

Before sending the data to redis, we need to serialize it to a form that redis understand, Redis has a serialiazation protocol
that converts each of the command we want to send to redis in the form that redis server understand.
According to redis serialization protocol we convert each of the command to a bulk of string.


The response that we receive from redis is also in accordance to the redis serialization protocol. So we need to convert it to the datastructure our language supports.
The different 


# Implementing basic string operations

GET, SET, DEL are the basic commands that redis server for key value pair.
Before sending the commands to the redis server we will serialize them 
and before after getting the response we will deserialize them to normal string response.