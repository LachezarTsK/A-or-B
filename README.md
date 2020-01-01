
# A-or-B

HackerRank>Practice>Algorithms>Bit Manipulation>A or B

https://www.hackerrank.com/challenges/aorb/problem

The presented solution does not apply BigIntegers. Thechnically, the problem could be solved with BigIntegers, applying the built-in methods flipBit() and testBit(). However, such solution will not fit into the alotted time frames for some test cases and thus nearly all test cases with large input will time out. 

There several reason for this:
1. The input is hexadecimal and has to be converted into decimal. 
2. The output is also a hexadecimal which requires once again time consuming convertion from decimal to hexadecimal. 
3. The letters in the hexadecimal output are required to be upper case. However, the built-method of BigInteger for 
   convertion from decimal to hexadecimal returns it with small letters, thus additional time is required to convert all 
   letters in the hexadecimal output into upper case.
