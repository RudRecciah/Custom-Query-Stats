## What is Custom Query Stats?
Custom Query Stats is a plugin that stores player statistics in your server's MOTD. These stats are hidden from players but visible to query scripts.

## Why use Custom Query Stats?
Custom Query Stats' purpose is to allow owners of relatively small servers to send player statistics to other applications by querying their server. The main advantage of Custom Query Stats is it's simplicity. While other plugins require added complexity with maintenance of databases or webservers, Custom Query Stats is simple. Using Custom Query Stats, a server owner doesn't need to maintain anything but the server itself and the application the plugin is used to send data to.

## What supports Custom Query Stats?
Any server running Spigot 1.16.x will support Custom Query Stats. Advanced users can run this on other versions by changing the API version of Custom Query Stats. If you do this, keep in mind versions other than 1.16.x were not tested and are not officially supported.

## How to use Custom Query Stats?
Read the [wiki](https://github.com/RudRecciah/Custom-Query-Stats/wiki)!

## What are the limitations of Custom Query Stats?
Due to Minecraft's protocol, Custom Query Stats can only send a maximum of statistics from 761 players and a minimum of statistics from 25 players depending on the amount of statistics logged and their values. A server owner can deal with this by logging less statistics. If that's not enough, there are some advanced workarounds to this issue but they require code modifications.
