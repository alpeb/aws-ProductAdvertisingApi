aws-ProductAdvertisingApi
=========================

Query Amazon's Product Advertising API, using Scala


How to Build
------------
- First copy ```application.conf.template``` to ```application.conf``` and fill-in the appropriate configuration values.
- sbt assembly

How to Run
----------
The previous step will tell you the location of the built jar. Just run it, passing as an argument a query-string with the operation you want to make with its appropriate parameters.

For example to get the available mp3 downloads for Nirvana:
```
java -jar target/scala-2.10/aws_ProductAdvertisingApi-assembly-0.9.0.jar "Operation=ItemSearch&SearchIndex=MP3Downloads&Keywords=Nirvana"
```

Use xmllint
------------
To pretty-print the xml result, make sure you have the xmllint command available and do:
```
java -jar target/scala-2.10/aws_ProductAdvertisingApi-assembly-0.9.0.jar "Operation=ItemSearch&SearchIndex=MP3Downloads&Keywords=Nirvana" | xmllint --format -
```


License
-------
MIT License
