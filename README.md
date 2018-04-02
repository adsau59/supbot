# Supbot
Whatsapp Chatbot using java and selenium
[Youtube video](https://www.youtube.com/watch?v=MWCjVzM0rW8)

### How to install

Download [chromedriver](https://www.seleniumhq.org/download/) for selenium and put it in ur path variable 

for the bot to detect the groups, the name of the group should contain its id with ;; in the end (for example, ;;yo), I have created 2 ids "1" and "2" for testing, so just put ;;1 and ;;2 in the end of your groups  
You will need to change the supbot.db to add other groups  
(you can use [mysqlite editor](http://sqlitebrowser.org/) to do so)

and just run the bot

### How the bot works

Feature - feature is list of commands that the client can use, like Math, Help  
Action - action is used to perform different action, like goto group, send message  
Checker - checkers are special type of actions, which check for events in whatsapp web, like new chat in current group checker, new chat in other group checker  

to create new Feature/Action simple create a new class and extent it with its abstract class and implement its method,
look at the core features to understand how it works

### Exit the bot

type quit and press enter in the console to exit the bot

### Bugs?

if you find any bugs, create an issure on github