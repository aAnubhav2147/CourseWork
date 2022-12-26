from InversionCounter import count_inversions as inversions_counter

import random
from array import *

# Test case
# arr = [4, 5, 2, 1, 3]
# n = len(arr)

low = 0
print("Enter Max Value of the range: ")
print(' ')
high = int(input())

print("Enter the number of array elements: ")
print(' ')
# maximum = int(input())
n = int(input())
arr = array('i', [])

print(' ')

for i in range(0, n):
    num = random.randint(low, high)
    arr.append(num)
# Print out the array
for i in arr:
    print(i)

print(' ')
print(' ')

answer = inversions_counter(arr, n)
print("The number of significant inversions are: ", answer)

print(' ')


def print_hi(name):
    print(name)


if __name__ == '__main__':
    print("This assignment is submitted by: ")
    print(' ')
    print_hi('Anubhav Shankar')
