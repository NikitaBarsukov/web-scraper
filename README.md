# web-scraper
Create console web scraper (http://en.wikipedia.org/wiki/Web_scraping) utility which:
        accepts as command line parameters:

(must) web resources URL or path to plain text file containing a list of URLs

(must) data command(s)

(must) word (or list of words with “,” delimiter)

(nice to have) output verbosity flag,  if on then the output should contains information about time spend on data scraping and data processing (-v)

         supports the following data processing commands:

(must) count number of provided word(s) occurrence on webpage(s). (-w)

(must) count number of characters of each web page (-c)

(nice to have) extract sentences’ which contain given words (-e)
 
Data processing results should be printed to output for each web resources separately and for all resources as total.
Command line parameters example for Java implementation:
- java –jar scraper.jar https://en.wikipedia.org/wiki/Google Microsoft,Apple –v –w –c –e
