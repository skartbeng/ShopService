#ShopService
ShopService is a RESTful service that manages shops for a retail chain.

Requirements
============
1. JDK 1.8
2. Gradle
3. Internet connection
4. Google Map API key , enter here src\main\resources\application.yml


Assumptions/further works
===========
1. find nearest shop returns the single most nearest shop relative to the location
2. using ConcurrentHashMap provide the concurrency to the application, but for production more granular level of concurrency is required
3. further enhancement need to implement better error handling and reporting
4. need error logging
5. more unit test cover corner cases and errors handling
6. BDD coverage , attempted , unable to complete due to limited time available

