# KitchenSync
KitchenSync, a CLI Folder Syncronization Tool is a Java implementation on rsync, to Sync two folders across two different machines in real time. The KitchenSync FST is implemented in Core Java, using JAVA Watcher API wrapped withing Java Sockets to communicate between two remote system.


# Features
* Sync two folders on different systems.
* Platform independent.
* Recursively watch changes in directories and its childer directories or files using JAVA Watcher API.
* Cater functionalities line CREATE, MODIFY, DELETE.
* Need to run on both the platforms.
  * Global.java initiates the program
  * SyncServer sends the data to stream.
  * Run SyncClient on recieving system to sync the content in stream.
  * Sync more than one folder on a system to other folders asynchronously.
  
  
# Usage
To Run in CLI mode : 
* Compile the java files, or import project in idea intellij.
* Run the Global.java to run watcher service on the system from where the files need to be synced.
* Run SyncClient on the system on which data is required to be synced.
* Keep the ports same on bothe the machines for the connection to be established.
* Invert the process on both the systems with a different port to listen to and establish a bi-directional sync.


# Future Scope
The current version implementation is based on Sockets and need to be run on both platforms which need to be synced.
The Sockets can be replaced by tcp API implementation, which will support Syncing of Directories, Images etc. from one system to another.

The server logic can then be implemented as a standalone server hosted in cloud, and clients be treated as consumers sending and downloading data in streams on the basis of a specific key. hence syncing bi-directionaly in real time.

The connection establishment process on both systems can be automated by keeping a check on the handshake process.

Extending the current implementation can support SDK like functionality to keep two system directories in sync, with other systems over a cloud.

A web interface or GUI implementation can be created for the same, with logic running the service in background upon a successful connection.

* Support to write Configs to Sync different type of files, custom loggers, connect to db(in future versions.)


# Version
current version : 1.1
