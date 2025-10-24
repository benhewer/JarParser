How to use the CLI:

First, to create the output files from JAR files, use the arguments "parse <jarPath> <outputPath>". This will scan through the JAR file, and for each file found within, it will write some of the metadata into the output file.

Second, to compare two output files, use the arguments "compare <output1> <output2>". This will compare how many "similar" files there are between the two JAR files by checking if the name, size and compressedSize are all equal. It will output a similarity percentage, which is the percentage of similar files to total unique files.
