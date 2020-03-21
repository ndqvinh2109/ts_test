# Trusting social test

This is first test to sort the input phone number file and output file where all the actual activation date of phone number are found.

### The implementation approach

The implementation uses the total line of input file and maximum buffer items to calculate temporary file to be sorted.
And using <code>Collections.sort</code> which behind the scence implement <b>Tim sort</b> and K merging to merge sorted temporary files.
Once merged to one sorted file, split sorted file into buffer chunk, calculate actual activation date and write to final output file.

* <code>FileSplitter</code><br>
Split unsorted file to multiple sorted temp files.<br>
Time complexity: 0 (k * m * log(m)) where k is numbers of temp files in maximum m buffer items<br>
Space complexity: O (m) where m is maximum buffer items
* <code>FileSorted</code><br>
Merge K sorted temp files to a sorted file<br>
Time complexity: O (N * log K) where N is total number of elements in all K temp files<br>
Space complexity: O(K) min heap will be storing one number from all K temp files
* <code>PhoneNumberService</code> <br>
Split sorted input list into the chunk then find actual activation date<br>
Time complexity: O (k*m) where k is numbers of temp files in maximum m buffer items<br>
Space complexity: O(m + t) where m is the list of numbers in chunk file and t is the
map storing grouping by phone number => 0(m)

### Technique
* Java JDK 8
* Spring boot

### Build and Run
* mvn clean package
* jar -jar assessment-0.0.1-SNAPSHOT.jar --ts.file.total=1000 --ts.file.buffer=200 --ts.file.input=pathInputFile --ts.file.output=pathOutputFile<br>

<code>ts.file.total</code>: Total line of input file by default: 50000000<br>
<code>ts.file.buffer</code>: Maximum buffer items by default 1000000<br>
<code>ts.file.input</code>: Absolute path of input file (For example: D:/data/input.txt)<br>
<code>ts.file.output</code>: Absolute path of output file (For example: D:/data/output.txt)<br>

### Results
[main] c.t.assessment.PhoneNumberActivation     : ----------------------Running Application!---------------------- <br>
[main] c.t.assessment.util.TimeMetric           : File Splitter took 56065 ms. <br>
[main] c.t.assessment.service.FileSplitter      : Lines processed: 50000000 <br>
[main] c.t.assessment.service.FileSplitter      : Number of temp files: 100 <br>
[main] c.t.assessment.util.TimeMetric           : File Sorter took 113127 ms. <br>
[main] c.t.assessment.util.TimeMetric           : Phone Number Service took 80456 ms. <br>
[main] c.t.assessment.util.TimeMetric           : Find the actual activation date took 249666 ms. <br>
[main] c.t.assessment.PhoneNumberActivation     : ----------------------Successfully executed!-------------------- <br>

