# Supbot

<div align="center">
  <img src="https://cdn.steemitimages.com/DQmVfVL98g7QbEyb4t4bt5A9DuMZLBYpiekwceBswjSUqTJ/small%20banner.jpg"/>
</div>

### What is Supbot API?
Supbot API is a Whatsapp Group Chatbot API, [Showcase video](https://www.youtube.com/watch?v=MWCjVzM0rW8).

Whatsapp is the most used messenger platform, but the lack of bot support made it very tedious to perform some operations that other platform with bot support performs easily.

Supbot API uses Selenium to automate Whatsapp Web to read and collect data, runs business logic and perform different actions defined by the developer, it provides various features to developers to create their own features and add it inside Whatsapp itself.

### Objective
- To create bot support on the most used messenger platform out there.
- To create a way for developers to make their own features inside whatsapp itself.
- To teach developers how to use Supbot API using [video tutorials](https://www.youtube.com/playlist?list=PLdeajH45r2EKfD4lB6LJW6jF66PLSFGFa).

### Technology Stack
Supbot is made using
- [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Selenium](https://www.seleniumhq.org/)
- [JDBC](http://www.oracle.com/technetwork/java/javase/jdbc/index.html),  [google-gson](https://github.com/google/gson), [Apache HttpComponents](https://hc.apache.org/)

### Features
- **Role system**, (Admin, CoAdmin, Elder, Member) assign roles to clients to give them access to different level of commands.
- **Multiple Groups Support**, make bot respond to multiple groups by using just one instance of the bot.
- **Command and Features**, add your own command to the bot by writing one simple class.
- **Database, save, access**, manage data required by the bot.
- **Schedule**, schedule different task to be performed at specific time or repeat specific interval of time.
- **Console Command**, manage the bot using your own build console commands and the inbuild ones too.
- **ChatProcessor**, control the lower level of the API using ChatProcessor.
- **Action**, create new actions in the bot like, downloading  and uploading images using Actions.
- **Network Job**, use 3rd party programs to connect with the bot to send and recieve requests.

### How to install
You can either watch the [Setting up Supbot video on youtube](https://www.youtube.com/watch?v=XURtoZfKdBg), or follow the sets written below

- Download [chromedriver](https://www.seleniumhq.org/download/) for selenium and put it in your path variable.
- Clone/Download the project.
- Open the example project in an IDE.
- Include the libraries in the lib folder.
- Then just run the main class.
- To add your group to the bot.
- Add a group id to your whatsapp group by writing ```;;<groupid>``` preceding to it, each group mush have a unique group id.
- Use this command in console ```group add <groupid>``` to add the group to the bot.

Make sure Google Chrome is updated. 

### How to use the API?
Check out the [video tutorial series](https://www.youtube.com/playlist?list=PLdeajH45r2EKfD4lB6LJW6jF66PLSFGFa) or read the JavaDocs in the source files.<br/>

### Roadmap
Development for Supbot API has finished (Since 3 months), but as developers using the API request new features or some bad design in the bot is noticed, I will try my best to add/change it in the bot. 

### How to contribute?
You can contribute this project by,
- Using the the API and creating issue when any bug is encountered.
- Helping me in the development by bug squashing or developing new features. (If you want to do this, contact me so that we can collaborate.)
- Let me know if you have any good feature ideas.

### Whats new
Check out [changes](https://github.com/adsau59/supbot/blob/master/changes) in the repository.

### Contact
If you have any problems or you want to contact me for feature ideas or want to collaborate in development you can contact me on [DefineX Community discord server](https://discord.gg/V6e2fpc).

### Feeling generous?
You can donate me on [PayPal](https://www.paypal.me/AdamSaudagar).

### Licencse
This project is licence to the MIT License, check out the full licence over [here](https://github.com/adsau59/supbot/blob/master/LICENSE).