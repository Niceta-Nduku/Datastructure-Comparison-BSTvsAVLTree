#!/bin/bash
set -x

head -n 501 ../cleaned_data.csv | tail -n 500  > unSortedDAta.csv


while IFS=, read -ra array;do

		echo ${array[0]} >> Testkeys.txt
		

done < unSortedDAta.csv

sort unSortedDAta.csv > SortedData.csv

cd ..

cd bin 

java PowerBSTAppP6 ../Tests/Testkeys.txt >../Tests/BSTPart6.txt

java PowerAVLAppP6 ../Tests/Testkeys.txt >../Tests/AVLPart6.txt

cd ..


