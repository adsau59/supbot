# Supbot
Whatsapp Chatbot using java and selenium, [Youtube video](https://www.youtube.com/watch?v=MWCjVzM0rW8).

### How to install

Download [chromedriver](https://www.seleniumhq.org/download/) for selenium and put it in ur path variable 

for the bot to detect the groups, the name of the group should contain its id with ;; in the end (for example, ;;yo).<br/>
I have created 2 ids "1" and "2" for testing, so just put ;;1 and ;;2 in the end of your groups.<br/>
To add new group simply type "group add &lt;groupID&gt;" in console to add a group with the specified group id,<br/>
and "group remove &lt;groupID&gt;" to remove a group with id.

and just run the bot

### How the bot works

Feature - feature is list of commands that the client can use, like Math, Help<br/>
Action - action is used to perform different action, like goto group, send message<br/>
Checker - checkers are special type of actions, which check for events in whatsapp web, like new chat in current group checker, new chat in other group checker<br/>
read the comments in each class to understand what the class does.<br/>

to create new Feature/Action simple create a new class and extend it with its abstract class and implement its method,<br/>
look at the core features to understand how it works<br/>

### Exit the bot

type quit and press enter in the console to exit the bot

### Bugs?

if you find any bugs, create an issure on github