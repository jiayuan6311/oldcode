@echo off
set classpath=.;lib/commons-codec-1.3.jar;lib/commons-logging-1.1.jar;lib/htmlparser.jar;lib/httpclient-4.0.1.jar;lib/httpcore-4.0.1.jar;lib/je-4.0.71.jar;bin
java -Xmx1536m -Xms256m crawler.Crawler
pause