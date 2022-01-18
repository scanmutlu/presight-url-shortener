# presight-url-shortener

To run the url shortener application, follow these steps:

In your repository folder, run commands below:

##### 1. mvn clean install
##### 2. docker compose up -d


You can find OpenApi Docs, swagger-ui on: 

http://localhost:3000/swagger-ui/index.html




### Methods
#### Post:

http://localhost:3000/generate

{
“url”:” https://presight.ai/”
}

Return value:

{
  "version": 0,
  "createDate": "2022-01-18T02:06:21.973+00:00",
  "updateDate": "2022-01-18T02:06:21.973+00:00",
  "url": "https://presight.ai/",
  "urlShortener": 3
}


#### Get:

http://localhost:3000/goto/url?urlShortener=3

Return value:

Redirect to https://presight.ai


