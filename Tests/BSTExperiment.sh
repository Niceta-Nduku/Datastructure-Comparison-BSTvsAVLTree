#!/bin/bash
set -x

#open bin directory in order to run the app
cd ..
cd bin

#create subset
for N in {2..501}
do
	head -n $N ../cleaned_data.csv | tail -n $((N-1))  > ../Tests/Subset1.txt
	rm -f ../Tests/Testkeys.txt

	#create a txt file of keys
	while IFS=, read -ra array;do

		echo ${array[0]} >> ../Tests/Testkeys.txt

	done < ../Tests/Subset1.txt

	#run the app with the txt file
	java PowerBSTApp ../Tests/Testkeys.txt >>../Tests/PowerBSTApp.txt
	echo "" >> ../Tests/PowerBSTApp.txt 
done 

rm -f ../Tests/Subset1.txt ../Tests/Testkeys.txt