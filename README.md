# JobFinder

JobFinder is a job search solution that looks into many providers and display results from all the available job providers, at the current stage we are aggregating from 2 providers: Github &search.gov.

The requirement of JobFinder application is to display the results from both providers, and the result should be shown in a Mobile APP. 

The API documentation for the given provider are:
- Github: https://jobs.github.com/api
- search.gov: https://search.gov/developer/jobs.html

To fetch data from the different providers, I have created seperate class to store URL.

    public class ProviderURLs {
        public static final String GitHubURL = "https://jobs.github.com/positions.json";
        public static final String searchGovURL ="https://jobs.search.gov/jobs/search.json";
    }


![1](https://user-images.githubusercontent.com/20694605/54409474-024a0e00-470f-11e9-9cc7-5ef2112b771c.PNG)


![2](https://user-images.githubusercontent.com/20694605/54409488-1130c080-470f-11e9-8f6f-82884321a5ce.PNG)


![3](https://user-images.githubusercontent.com/20694605/54409490-168e0b00-470f-11e9-9403-459d8e74959f.PNG)




### The reguirement of JobFinder application is to filter the result using:
- By Provider
- By Position
- By Location (autocomplete) using Google APIs



![4](https://user-images.githubusercontent.com/20694605/54409581-a16f0580-470f-11e9-98d8-a7bc20effa74.PNG)



##### 1. By Provider
Here we can only filter providers by GitHub and search.gov. When scalability of provider increases or decreases, dropdown list also increase or decreases accordingly. By default filter is set to GitHub.


![5](https://user-images.githubusercontent.com/20694605/54409621-eb57eb80-470f-11e9-8e15-ebd2b48cb799.PNG)


Sample Code :

    Bundle bundle = getIntent().getExtras();
            String provider = bundle.getString("provider");

     String getURLForSearch = null;
            if(provider.equals("GitHub")){
                getURLForSearch = GitHubURL;  //Gets URL from ProviderURLs class
            }
            else if(provider.equals("searchGov"))
                getURLForSearch = searchGovURL;

##### 2. By Position
User can type position for which job vacancy post. Let's take example for..

    java developers
    
If provider filter is not select then by default it is set to GitHub.


![6](https://user-images.githubusercontent.com/20694605/54409992-a92fa980-4711-11e9-9da2-c0ca5ab69301.PNG)


    ...
    String words = bundle.getString("query");
    ....
     if(!words.equals("")){
                if(provider.equals("GitHub")){
                    String query = "?description="+convertingWordsToQuery(words);
                    getURLForSearch = getURLForSearch + query;
                }
                else if(provider.equals("searchGov")) {
                    String query = "?query="+convertingWordsToQuery(words);
                    getURLForSearch = getURLForSearch + query;
                }

            }
            
    

To make URL call with query parameters, the query should follow like this pattern:
- GitHub: https://jobs.github.com/positions?description=java+developers
- search.gov: https://jobs.search.gov/jobs/search.json?query=nursing+jobs



      private String convertingWordsToQuery(String words) {
            String[] convert = words.split(" ");
            String converted = "";

            if(convert.length == 1){
                converted = convert[0];
            }else {
                for(int i=0; i<convert.length; i++){
                    if(i<convert.length-1)
                        converted = converted +convert[i]+"+";
                    else
                        converted = converted +convert[i];
                }
            }


            return converted;
        }
        
        

##### 3. By Location (autocomplete) using Google APIs
At first set Google service key in manifest file inside application 

        <!-- Google API -->
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="Your_API_KEY"/>


User can type location for which job vacancy post. Let's take example for..

       usa
       canada
       nepal etc.
    
If provider filter is not select then by default it is set to GitHub.


        if(!location.equals("")){
             if(!words.equals("")){
                if(provider.equals("GitHub")){
                    String query = "?description="+convertingWordsToQuery(words);
                    String loc = "&location="+convertingWordsToQuery(location);
                    getURLForSearch = getURLForSearch + query + loc;
                }
                else if(provider.equals("searchGov")) {
                    String query = "?query="+convertingWordsToQuery(words);
                    String loc = "&tags="+convertingWordsToQuery(location);
                    getURLForSearch = getURLForSearch + query + loc;
                }
            }else{
                if(provider.equals("GitHub")){
                    String query = "?location="+convertingWordsToQuery(location);
                    getURLForSearch = getURLForSearch + query;
                }
                else if(provider.equals("searchGov")) {
                    String query = "?tags="+convertingWordsToQuery(location);
                    getURLForSearch = getURLForSearch + query;
                }
            }
        }



### Built Configuration
This project was build on 3.3.2 gradle.

    compileSdkVersion 27 
    minSdkVersion 14 
    targetSdkVersion 27 
    
and Dependencies

    dependencies { 
        //Gradle - To fetch data using volley
        implementation 'com.android.volley:volley:1.1.1'
        //To show images by picasso
        implementation 'com.squareup.picasso:picasso:2.71828'
        
        //gradle library for google place api
        implementation 'com.google.android.gms:play-services:11.8.0'
    }

### Contribution
Suman Neupane – sumanmyon.sam@gmail.com

See LICENSE for more information.

https://github.com/sumanmyon
