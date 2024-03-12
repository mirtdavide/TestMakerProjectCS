# TestMakerProject Client/Server 

## A small description of this repo content

### Disclaimer: the app screens are in italian but along with the description of each image should be understandable

Hello!<br>
So, this is basically the same app that i have made <a href="https://github.com/mirtdavide/TestMakerProject">here</a>, the only thing that changes is the handling of info and the fact that as an admin you no longer can add images to your question as i had a bit of trouble handling that.<br>
With this project i tried to understand and implement a Client/Server Architecture using Java functions specifically for the UDP type. The functionalities of the app remain pretty much the same with the difference that this time the data we create from registering a new user or generating a test is stored server side while the client connects through the server and just gets the data from it.</br>
Again the code is not written in a very professional way and it has the same issues of the non C/S version, (and the screens of the app are in italian as i made these projects during my high school years in Italy) but it made for a good training.
<br >If you want to check out the<a href="https://github.com/mirtdavide/TestMakerProject"> non C/S part of the app click here</a> or click the other link up there as here i will post just some screens of the C/S implementetion.


## Running the server

When we run the server we don't have any GUI, just the terminal that tells us what's happening.<br>
In this case it first starts listening for clients, if anyone connects it tells us on the terminal its address and the port used for connecting and then it continues to listen.



![connection_server.png](readme_res%2Fconnection_server.png)

In this case i aimed for a multi-client server capable to serve many clients at the same time, in order to do this i generate a server thread for each client coming in.

## Communication
<br>The communication between the client and the server is made through a defined message protocol between the two.
When the client wants to request something to the server it sends a specific string to the server and the server will resond according to the type of request the client made
</br> As an example when we want to log in the client will send first to the server the LOGIN string, the server receives it and knows that the next two messages sent by the 
client will be  the user and the password which the server will elaborate and confront with the data he has stored in order to authenticate the user trying to log in

![server_communication.png](readme_res%2Fserver_communication.png)

In this case someon tried to connect with the credentials of an already connected: the server received the LOGIN string
it receives the two strings containing username and password, it sees that someone with those credentials is already connect so it outputs: "Sono uguali"/"They are equal" and it refuses them
the user trying to connect is not already in so it will accept them
## Running the Client

The client runs as usual altough before logging in we must type the address of the server we want to connect to, there is no need to type the port as it is set as default to: 6789


![connection_client_LH.png](readme_res%2Fconnection_client_LH.png)

if the connection is successful we will be prompted witht the ususal login frame.<br>
In order to avoid two users connected at the same time the servers keep track of the connected users once they log in into an arraylist
and if someone tries to log in with the same credentials of a user already logged in it will prevent from doing so.


![already_connected.png](readme_res%2Falready_connected.png)

## Conclusion

This time i will cut shorter since the app basically works the same as it does with the login: send the type of request to server/receive data from the server only that most of the time it will be used also to complete data into a GUI frame such as the question frame where all the data comes from the server.
<br>With that said it was nice to experiment with C/S architecture and it definitely helped me to understand it even if at a basic level


