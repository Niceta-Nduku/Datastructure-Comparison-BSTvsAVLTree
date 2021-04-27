#!/usr/bin/bash
# Test cases for the Applications 
# Part 1 to 4
# Program is run and the output is redirected to a file 

declare -a KNOWNCASES=("16/12/2006/19:51:00" "16/12/2006/21:39:00" "16/12/2006/17:43:00")

FAILCASE="16/12/2007/17:43:00"

cd ..

cd bin

echo Type app name PowerAVLApp or PowerBSTApp

read app

#go through the 3 known times (KNOWNCASES)
echo $app Test Cases >> ../Outputs/KnownCase.txt

for test in "${KNOWNCASES[@]}"; do
	echo Date/Time: $test 
	echo ""
	echo Output: 
	java $app $test 
	echo ""
done > ../Outputs/KnownCase.txt

#This will run the App with a false date time
echo $app Test Cases >> ../Outputs/UnknownCase.txt
echo Unknown Case:  >>../Outputs/UnknownCase.txt
echo "" >>../Outputs/UnknownCase.txt
echo Output: >> ../Outputs/UnknownCase.txt
java $app $FAILCASE >> ../Outputs/UnknownCase.txt

#This will run the App to print all datetimes
#No parameters
echo No parameters >> ../Outputs/all.txt
echo Output: >> ../Outputs/all.txt
java $app >>  ../Outputs/all.txt

echo $app No parameters Test Cases >> ../Outputs/NoParam.txt
head -n 12 ../Outputs/all.txt >> ../Outputs/NoParam.txt
tail -n 10 ../Outputs/all.txt >> ../Outputs/NoParam.txt

echo end of testing

cd ..

mkdir  Outputs/$app

mv Outputs/*.txt Outputs/$app