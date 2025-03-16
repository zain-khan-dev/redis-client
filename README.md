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


# Implement different data types
Since multiple commands can return different data types we need to parse the response based on what opeartion might return what response
The redis serialization protocol is defined in the following doc https://redis.io/docs/latest/develop/reference/protocol-spec/


# Implement connection pooling
Since creating and closing connection each time is very expensive we need to provide client with some initial connections which they can reuse
this avoids expensive opeartions such as SSL termination, TCP handshake and bypass all the other connection setup actions we need to do before sending requests

# Provide Authentication Support
Since the client can have authentication enabled for their redis, we need to support authentication within our client to allow users to authenticate within their servers.
https://redis.io/docs/latest/commands/auth/


# Provide support for handling errors, retries, backoff and timeouts.
- It might happen at times that redis servers are experiencing some issues, and our initial requests might fail, we need to provide options to client to retry on some specific errors and provide support for timeouts.


# Provide support for configuration through configuration file
- All the above configurations can be defined either directly through providing these configuration within the code or provide these through configuration files
We will be giving preference to loading the file through configuration file  the following