def mergesort(arr, temp, left, right):
    inv_count = 0
    # mid = 0

    if right > left:
        mid = int((right + left) / 2)  # Wrap the expression to avoid a "Recursion Error"
        # inv_count = mergesort(arr, temp, left, right)
        # Start by checking the inversion count of the left part
        inv_count = mergesort(arr, temp, left, mid)
        # inv_count = inv_count + mergesort(arr, temp, mid + 1, right)
        # Count the inversions on the right part
        inv_count += mergesort(arr, temp, mid + 1, right)
        # Merge the above two sub-arrays
        inv_count += merge(arr, temp, left, mid + 1, right)
    return inv_count


def merge(arr, temp, left, mid, right):  # Temp array is used to store the elements post comparison and prior to merging
    inv_count = 0

    i = left
    j = mid
    # k = left

    # First pass to count the number of significant inversions
    while (i <= mid - 1) and (j <= right):
        if arr[i] > 2 * arr[j]:  # This is required as per the question stem to be the definition
            #  of significant inversions
            inv_count += (mid - i)  # If the above condition holds check the left side of the ith element
            j = j + 1
        else:
            i = i + 1

    # Re-activate the indices to include the index of the resultant sub-array
    # Also, needs to be done to prevent infinite looping which will result in a StackOverflow
    # resulting in a "RecursionError"
    i = left
    j = mid
    k = left

    # Second pass happens like a simple inversion count
    while (i <= mid - 1) and (j <= right):
        if arr[i] <= arr[j]:
            temp[k] = arr[i]
            i = i + 1
            k = k + 1
        else:
            temp[k] = arr[j]
            k = k + 1
            j = j + 1
    # The pointers will not be moving simultaneously unlike a simple inversion count
    # Also, once we have determined that the condition holds true both the pointers need to
    # move forward separately unlike normal inversion count

    # Check for remaining elements in both the right and left sub-arrays to be copied to temp
    # For Left Sub-Array
    while i <= mid - 1:
        temp[k] = arr[i]
        k = k + 1
        i = i + 1
    # For Right Sub-Array
    while j <= right:
        temp[k] = arr[j]
        k = k + 1
        j = j + 1

    # Copy the merged elements back to the original array
    for i in range(left, right + 1):
        arr[i] = temp[i]

    return inv_count


# This function is created to ensure that the user only has to input an array, and it's range
def count_inversions(arr, size):
    tmp = [0 for i in range(size)]  # Do this to prevent IndexError. Else, because of multiple pointers
    # the one responsible for keeping track of the resultant sub-array will exceed the index range
    return mergesort(arr, tmp, 0, size - 1)  # -1 to accommodate zero indexing

# To count the number of significant inversions we will
# need to execute a MergeSort. However, for counting the number of
# significant inversions, a custom function would need to be created for it.

# Custom function to count the number of inversions on both the left and right side of the array
# Just like a simple MergeSort, they are then merged to get the resultant order
# def merge(arr, temp, left, mid, right):
#     inv_count = 0
#
#     i = left
#     j = mid
#     k = left
#
#     while (i <= mid - 1) and (j <= right):
#         if arr[i] <= arr[j]:
#             temp[k] = arr[i]
#             k = k + 1
#             i = i + 1
#         else:
#             temp[k] = arr[j]
#             k = k + 1
#             j = j + 1
#             inv_count = inv_count + (mid - i)
#
#     while i <= mid - 1:
#         temp[k] = arr[i]
#         k = k + 1
#         i = i + 1
#
#     while j <= right:
#         temp[k] = arr[j]
#         k = k + 1
#         j = j + 1
#
#     for s in range(i, right):
#         # temp[range] = arr[range]
#         arr[s] = temp[s]
#
#     return inv_count
#
#
# def mergesort(arr, temp, left, right):
#     # mid = 0
#     inv_count = 0
#
#     if right > left:
#         # Dividing the array into two parts
#         mid = (right + left) / 2
#
#         # Recursively call the function to independently count the number of inversions
#         # on both the left and right side
#         inv_count = mergesort(arr, temp, left, right)
#         inv_count += mergesort(arr, temp, mid + 1, right)
#         # Merge the two resultant sub-arrays and get the cumulative count as the final number of inversions
#         inv_count += merge(arr, temp, left, mid + 1, right)
#
#     return inv_count
#
#
# def count_inversions(arr, size):
#     inv_count = 0
#
#     for i in range(size):
#         for j in range(i + 1, size):
#             if arr[i] >= arr[j]:
#                 inv_count += 1
#
#     temp = [0 for i in range(size)]
#     # return mergesort(arr, temp, 0, size)
#
#     return inv_count

# def count_inversions(arr):
#     if len(arr) <= 1:
#         return arr, 0
#     else:
#         mid = len(arr)/2
