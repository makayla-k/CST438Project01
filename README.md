# Music Event Finder
### Group 9

## App / Project Description

Find events Randomly, by your favorite artist, or location

## User Stories (Required)
* Users can login / create an account 
* Users can search for music events based on artist
* Users can save save events and it will be displayed on their profile

## Screen Archetypes

* Login / Create an Account
    * Users will be stored using Room database
* List Artists / Events
    * Using the [Bandsintown API](https://www.artists.bandsintown.com/support/api-installation) and Retrofit artist and event data will be fetched
* Search for Artists / Events
    * data will be fetch from the API based on user input
* User Profile
    * Display username & saved events
    * User can modify account
