<div align="center">
  <img src="https://cdn.steemitimages.com/DQmVfVL98g7QbEyb4t4bt5A9DuMZLBYpiekwceBswjSUqTJ/small%20banner.jpg"/>
</div>

# Supbot
A Whatsapp Chatbot API (using java and selenium), [Youtube video](https://www.youtube.com/watch?v=MWCjVzM0rW8).<br/>
[Subscribe to my Channel](https://www.youtube.com/channel/UCYik1Iuvf2bEw0ewDHqDnGw) as I do streams while developing the bot

### How to install
<div align="center">
  <a href="https://www.youtube.com/watch?v=XURtoZfKdBg"><img src="https://img.youtube.com/vi/XURtoZfKdBg/0.jpg" alt="IMAGE ALT TEXT"></a>
</div>
You can either watch the Setting up Supbot video on youtube, or follow the sets written below

- Download [chromedriver](https://www.seleniumhq.org/download/) for selenium and put it in your path variable.
- Clone/Download the project.
- Open the example project in an IDE.
- Include the libraries in the lib folder.
- Then just run the main class.
- To add your group to the bot.
- Add a group id to your whatsapp group by writing ```;;<groupid>``` preceding to it, each group mush have a unique group id.
- Use this command in console ```group add <groupid>``` to add the group to the bot.

Make sure Google Chrome is updated. 

### How the bot works

Feature - feature is list of commands that the client can use, like Math, Help<br/>
Action - action is used to perform different action, like goto group, send message<br/>
Checker - checkers are special type of actions, which check for events in whatsapp web, like new chat in current group checker, new chat in other group checker<br/>
read the comments in each class to understand what the class does.<br/>

to create new Feature/Action simple create a new class and extend it with its abstract class and implement its method,<br/>
look at the core features to understand how it works<br/>
I also have commented all the files explaining what they do.<br/>
There are many more concepts, which I will cover in the [video tutorial series](https://www.youtube.com/playlist?list=PLdeajH45r2EKfD4lB6LJW6jF66PLSFGFa).<br/>

### Exit the bot

type quit and press enter in the console to exit the bot.

### I'm confused :(
Hop in to my discord server, [DefineX Community Server](https://discord.gg/V6e2fpc), I'll help you out over there.

### Feeling generous?
You can donate me on [PayPal](https://www.paypal.me/AdamSaudagar).

### Whats new
check out [changes](https://github.com/adsau59/supbot/blob/master/changes) in the repository.

### Bugs?

if you find any bugs, create an issue on github.