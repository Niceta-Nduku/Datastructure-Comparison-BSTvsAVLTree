#!/bin/bash
set -x

#open bin directory in order to run the app
cd ..
cd bin

#create subset
for N in {2..501}
do
	head -n $N ../cleaned_data.csv | tail -n $((N-1))  > ../Tests/Subset2.txt
	rm -f ../Tests/keys.txt

	#create a txt file of keys
	while IFS=, read -ra array;do

		echo ${array[0]} >> ../Tests/keys.txt

	done < ../Tests/Subset2.txt

	#run the app with the txt file
	java PowerAVLApp ../Tests/keys.txt >>../Tests/PowerAVLApp.txt
	echo "" >> ../Tests/PowerAVLApp.txt 
done 

rm -f ../Tests/Subset2.txt ../Tests/keys.txt