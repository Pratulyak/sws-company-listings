# SWS Company Listings

This is a simple application that show's a list of companies and their relevant stock exchange metrics. It also allows the user to see previous close prices.

## Note
There were compatibility issues with the provided SQLite DB and my backend framework of choice i.e. Spring Boot. As Spring Boot and Spring DATA come with Hibernate as the packaged ORM there's no support for SQLite.
To ensure that I was able to connect to the DB and complete this challenge I had to implement my own Dialect provider and custom JDBC plugin support.

Finally, I also had to alter the DB a little to ensure that this app was functional. I had to alter the column type of 'date' to varchar in the table swsCompanyPriceClose. Considering date and companyId are a composite key with a one to many relationship I was having issues hydrating the entity's composite key.

This is an active concession I had to make to ensure I was able to complete this challenge, if not I would've had to learn and implement the backend in a framework like Express.JS or Nest.JS

## SWS BE

Technologies used: 
- Spring Boot
- OpenJDK 16
- Hibernate
- SQL Lite

Installation:
1. To ensure that the backend project is running you need to have a version of AdoptOpenJDK install and configured in your application
2. I've provided the required gradle folders to ensure that we can use gradle without needing to install grade for all the build tools and dependencies 

How to install OpenJDK:

```
brew tap adoptopenjdk/openjdk
brew search adoptopenjdk
brew install adoptopenjdk16
ls -lsa /Library/Java/JavaVirtualMachines/
echo 'export PATH="/Library/Java/JavaVirtualMachines/adoptopenjdk-16.jdk/Contents/Home/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
java -version
```

Running the backed:
```
cd sws-be
./gradlew bootRun
```

## SWS FE

Technologies used:
- Angular
- Node JS
- Typescript
- Angular Material UI

Installation:
- You'll need to have the angular CLI installed to be able to run the application

How to install Angular CLI
```
npm i @angular/cli
```

Running the Frontend:
```
npm install

ng serve 

```

##TODO:
At this stage of this application there are plenty of things that need to be implemented to make it truly production ready, some of these steps include:
- Implement a proper load balancer to ensure that I don't have to make the API open to Cross Origin Requests
- Implement API tests in the backend to ensure the API is returning the appropriate values
- Implement e2e tests in the frontend using protractor or cypress
- Containerise all the applications for easier deployments

##License
[GNU](https://choosealicense.com/licenses/gpl-3.0/)
