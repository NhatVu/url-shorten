## URL Shorten 

(Ref: System Design Interview: An Insider’s Guide, Alex Xu, chapter 8)

### Overview: 
In order to shorten URL, we have at least 2 approach:
1. Hash the long URL, then get a portion of that hash and use it as short url. However, this way create a conflict. Hash conflict and short url conflict. To solve this, we append random string to longURl and hash it until there is no collison. This can be in effective when db have a lot of data, then the chance for collision increase
2. Using Base62 encode. With shortUrl length is 7, we can generate around 3.5B url. This way guarantee no collision. However, it can be guessable if counter increases by 1 or any predictable rule. And it can create a single point of failure if we use SQL auto_increment. To resolve this issue, using Twitter Snowflake for ID generator.  


This implement is followed by approach 2: using Base62 encode.