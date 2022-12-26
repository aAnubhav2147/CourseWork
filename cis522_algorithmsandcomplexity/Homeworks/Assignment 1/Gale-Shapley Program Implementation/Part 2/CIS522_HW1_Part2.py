# Load the relevant packages

# import pandas as pd
# import numpy as np

print("Anubhav Shankar_CIS 522 HW1: Part 2")
print(" ")

# Create the profiles for men and women respectively
# N = 5
# Attempt 1: Using a dictionary-dataframe combo
# print("Men Preference List")
# print(" ")
# men = pd.DataFrame({0: ['Bertha', 'Diane', 'Bertha', 'Amy', 'Bertha'],
#                     1: ['Amy', 'Bertha', 'Erika', 'Diane', 'Diane'],
#                     2: ['Diane', 'Amy', 'Clare', 'Clare', 'Amy'],
#                     3: ['Erika', 'Clare', 'Diane', 'Bertha', 'Erika'],
#                     4: ['Clare', 'Erika', 'Amy', 'Erika', 'Clare']})
# men.index = ['Victor', 'Wyatt', 'Xavier', 'Yancy', 'Zeus']
# print(men)
#
# print(" ")
#
# print("Women Preference List")
# print(" ")
# women = pd.DataFrame({0: ['Zeus', 'Xavier', 'Wyatt', 'Victor', 'Yancey'],
#                       1: ['Victor', 'Wyatt', 'Xavier', 'Zeus', 'Wyatt'],
#                       2: ['Wyatt', 'Yancey', 'Yancey', 'Yancey', 'Zeus'],
#                       3: ['Yancey', 'Victor', 'Zeus', 'Xavier', 'Xavier'],
#                       4: ['Xavier', 'Zeus', 'Victor', 'Wyatt', 'Victor']})
# women.index = ['Amy', 'Bertha', 'Clare', 'Diane', 'Erika']
# print(women)

# Attempt 2:

# print("Men Preference List")

men_pref = [['Bertha', 'Amy', 'Diane', 'Erika', 'Clare'],
            ['Diane', 'Bertha', 'Amy', 'Clare', 'Erika'],
            ['Bertha', 'Erika', 'Clare', 'Diane', 'Amy'],
            ['Amy', 'Diane', 'Clare', 'Bertha', 'Erika'],
            ['Bertha', 'Diane', 'Amy', 'Erika', 'Clare']]

men = ['Victor', 'Wyatt', 'Xavier', 'Yancey', 'Zeus']


# men_pref_rev = [str(i or ' ') for i in men_pref.reverse()]
# men_rev = [str(i or ' ') for i in men.reverse()]

# Custom function to reverse a list
# If done in a standalone fashion, it'll end up as NoneType and we'll get a TypeError
def reverse(list):
    list.reverse()
    return list


# Reverse the above created lists

men_pref_rev = reverse(men_pref)
men_rev = reverse(men)

# print(men_pref)
# print(" ")
# print(" ")
# print(men_pref_rev)
# print(" ")
# print(" ")
# print(men)
# print(" ")
# print(" ")
# print(men_rev)

#
# print(" ")
#
# # print("Women Preference List")
# print(" ")
women_pref = [['Zeus', 'Victor', 'Wyatt', 'Yancey', 'Xavier'],
              ['Xavier', 'Wyatt', 'Yancey', 'Victor', 'Zeus'],
              ['Wyatt', 'Xavier', 'Yancey', 'Zeus', 'Victor'],
              ['Victor', 'Zeus', 'Yancey', 'Xavier', 'Wyatt'],
              ['Yancey', 'Wyatt', 'Zeus', 'Xavier', 'Victor']]
women = ['Amy', 'Bertha', 'Clare', 'Diane', 'Erika']


# Create two custom functions to keep track of list traversal
# Also, the function needed to define that the elements are string type in order to ensure accurate pointing
def men_rolodex(str, N):
    for i in range(N):
        if men_rev[i] == str:
            return i
    print('M')
    return 0


def women_higher_preference(str, N):
    for i in range(N):
        if women[i] == str:
            return i
    print('W')
    return 0


def stable_marriage2():
    n = len(men_pref_rev)
    count_stop = 0
    men_preference = men_pref_rev
    women_preference = women_pref
    men_engage = ['X'] * n  # Create an empty array with X as the placeholder value
    women_match_partner = ['X'] * n  # Create an empty array with X as the placeholder value
    stable_match2(count_stop, n, men_engage, men_preference, women_match_partner, women_preference)


# As in this case it is the men who are doing the proposing we need to
# have a function to track the changing preference of females
# as a female will drop her current engagement for a better proposal
# This is also required as the algorithm is male-optimal and female-pessimal i.e.
# females will generally tend to end up with a non-optimal choice more often than not
def preference(curr_partner, new_partner, index, n, women_prefer):
    for i in range(n):
        if women_prefer[index][i] == new_partner:
            return True
        if women_prefer[index][i] == curr_partner:
            return False
    return False

# Function to keep track of the matches made and zero down on a stable match
def stable_match2(count_stop, n, men_pref_engaged, men_favorite, women_partner, women_favorite):
    while count_stop < n: # List traversal
        for free in range(n):
            if men_pref_engaged[free] == 'X': # Check to see if there are any free men
                break
        i = 0 # If there are free men traverse the list and make them propose to the females on their list
        while i < n and men_pref_engaged[free] == 'X':
            index = women_higher_preference(men_favorite[free][i], n)
            if women_partner[index] == 'X':  # If a woman hasn't been proposed
                women_partner[index] = men_rev[free]  # Engage her with the first free man who proposes
                men_pref_engaged[free] = True
                count_stop = count_stop + 1

            else:  # Basically check if a stable partnership has been created
                current_partner = women_partner[index]
                if preference(current_partner, men_rev[free], index, n, women_favorite):
                    women_partner[index] = men_rev[free]
                    men_pref_engaged[free] = True
                    men_pref_engaged[men_rolodex(current_partner, n)] = 'X'
                    # If not engaged, make the man move onto the next preference
            i = i + 1
    confirm_match2(men_favorite, women_favorite, women_partner)


def confirm_match2(men_match, women_match, women_matching_partner):
    # Room for improvement: this function can be done with a single argument. But the logic of stable_marriage1()
    # would need to be revisited and made more dynamic
    i = 0
    j = 0
    print(' ')
    print(' ')

    print("Men Preference List")
    # Basically print out the above preference lists in matrix form
    for i in range(5):
        print(men_rev[i], '-> ', end=' ')
        for j in range(5):
            print(men_pref_rev[i][j], end=' ')
        print(' ')
    print(' ')
    print("Women Preference List")
    for i in range(5):
        print(women[i], '-> ', end=' ')
        for j in range(5):
            print(women_pref[i][j], end=' ')
        print(' ')
    print(' ')
    print("Confirmed Matches!")
    for i in range(5):
        print(women_matching_partner[i], ' ', women[i])

# print(women_pref)
#
# # Define index counter to keep track of men's proposal and women's preference change
# def menIndexChange(str, N):
#     for i in range(N):
#         if men[i] == str:
#             return i
#     print('M')
#     return 0
#
# def womenIndexChange(str, N):
#     for i in range(N):
#         if women[i] == str:
#             return i
#     print('W')
#     return 0
#
# def Preference(curPartner, newPartner, index, length, wprefer):
#      # i = 0
#     for i in range(length):
#         if wprefer[index][i] == newPartner:
#             return True
#         if wprefer[index][i] == curPartner:
#             # print('end function')
#             return False
#     return False
#
# def StableMarriage1():
#     print("Men Propose! Order: Victor to Zeus")
#     length = len(men_pref)
#     stop_count = 0
#     pref_men = men_pref
#     pref_women = women_pref
#     men_engage = ['X'] * length  # Create an empty list of X string characters
#     women_match = ['X'] * length
#     StableMatch1(stop_count, length, men_engage, pref_men, pref_women, women_match)
#
# def StableMatch1(stop_count, length, man_pref_engage, man_fav, woman_partner, woman_fav):
#     while stop_count < length:
#         for free in range(length):
#             if man_pref_engage[free] == 'X':
#                 break
#         i = 0
#         while i < length and man_pref_engage[free] == 'X':
#             index = womenIndexChange(man_fav[free][i], length)
#             if woman_partner[index] == 'X':
#                 woman_partner[index] = men[free]
#                 man_pref_engage[free] = True
#                 stop_count = stop_count + 1
#
#             else:
#                 current_partner = woman_partner[index]
#                 if Preference(current_partner, men[free], index, length, woman_fav):
#                     woman_partner[index] = men[free]
#                     man_pref_engage[free] = True
#                     man_pref_engage[menIndexChange(current_partner, length)] = 'X'
#             i = i + 1
#     ConfirmMatch1(man_fav, woman_fav, woman_partner)
#
# def ConfirmMatch1(men_match, woman_match, woman_match_partner):
#     print("Men Preference List")
#     for i in range(5):
#         print(men[i], '-> ', end=' ')
#         for j in range(5):
#             print(men_pref[i][j], end=' ')
#         print(' ')
#     print(' ')
#     print("Women Preference List")
#     for i in range(5):
#         print(women[i], '-> ', end=' ')
#         for j in range(5):
#             print(women_pref[i][j], end=' ')
#         print(' ')
#     print(' ')
#     print("Confirmed Matches!")
#     for i in range(5):
#         print(woman_match_partner[i], ' ', women[i])
#
# iteration1 = StableMarriage1()

#
# # Previous Attempts
# # proposals_count = [0 for _ in men_pref]
# # men_engaged = [0 for _ in men_pref]
# # women_engaged = [0 for _ in women_pref]
# #
# # def inverse_arr(temp_arr):
# #     temp_arr_inv = [0 for _ in temp_arr]
# #     for i in range(len(temp_arr)):
# #         temp_arr_inv[temp_arr[i]-1] = i+1
# #     return temp_arr_inv
# #
# # def all_men_proposed():
# #     return [i for i,j in enumerate(men_engaged)
# #             if proposals_count[i] < len(women_engaged) and not men_engaged[i]]
# #
# # # First Iteration
# #
# # i: str
# # for i,k in enumerate(men_pref): print("Trying to find a good partner for {}".format(i+1))
# # for j,l in enumerate(women_pref):
# #      if women_engaged[men_pref[i][j]-1] == 0:
# #          print(" ", i+1, "likes to be with  ", men_pref[i][j])
# #          print(" ", men_pref[i][j], "isn't engaged at the moment. So, ", i+1, "can get engaged with ", men_pref[i][j])
# #          proposals_count[i] =+ 1
# #          men_engaged[i] = men_pref[i][j]
# #          women_engaged[men_pref[i][j]-1] = i + 1
# #          break
#
#
#
#
#
#
#
#
#
#  # Gale - Shapley Algorithm
#
# # def shepreferstheother(p,w,m,m1):
# #     for i in range(N):
# #         if(p[w][i] == m1):
# #             return True
# #         else:
# #             return False
# #
# # def GaleShapley(men_prefer,women_prefer):
# #     woman_partner = [-1 for i in range(N)]
# #     free_man = [False for i in range(N)]
# #
# #     free_count = N
# # men_array = men.index
# # women_array = men.index
#
# def men_rolodex(str, N):
#     for i in range(N):
#         if men[i] == str:
#             return i
#     print('M')
#     return 0
#
# def women_higher_preference(str, N):
#     for i in range(N):
#         if women[i] == str:
#             return i
#     print('W')
#     return 0
#
# def stable_marriage1():
#     N = len(men_pref)
#     count_stop = 0
#     men_preference = men_pref
#     women_preference = women_pref
#     men_engage = ['X'] * N
#     women_match_partner = ['X'] * N
#     stable_match1(count_stop, N, men_engage, men_preference, women_match_partner, women_preference)
#
# def preference(curr_partner, new_partner, index, N, women_prefer):
#     for i in range(N):
#         if women_prefer[index][i] == new_partner:
#             return True
#         if women_prefer[index][i] == curr_partner:
#             return False
#     return False
#
#
# def stable_match1(count_stop, N, men_pref_engaged, men_favorite, women_partner, women_favorite):
#     while count_stop < N:
#         for free in range(N):
#             if men_pref_engaged[free] == 'X':
#                 break
#         i = 0
#         while i < N and men_pref_engaged[free] == 'X':
#             index = women_higher_preference(men_favorite[free][i], N)
#             if women_partner[index] == 'X':
#                 women_partner[index] = men[free]
#                 men_pref_engaged[free] = True
#                 count_stop = count_stop + 1
#
#             else:
#                 current_partner = women_partner[index]
#                 if preference(current_partner, men[free], index, N, women_favorite):
#                     women_partner[index] = men[free]
#                     men_pref_engaged[free] = True
#                     men_pref_engaged[men_rolodex(current_partner, N)] = 'X'
#             i = i + 1
#     confirm_match1(men_favorite, women_favorite, women_partner)
#
# def confirm_match1(men_match, women_match, women_matching_partner):
#     i = 0
#     j = 0
#     print(' ')
#     print(' ')
#
#     print("Men Preference List")
#
#     for i in range(5):
#         print(men[i], '-> ', end=' ')
#         for j in range(5):
#             print(men_pref[i][j], end=' ')
#         print(' ')
#     print(' ')
#     print("Women Preference List")
#     for i in range(5):
#         print(women[i], '-> ', end=' ')
#         for j in range(5):
#             print(women_pref[i][j], end=' ')
#         print(' ')
#     print(' ')
#     print("Confirmed Matches!")
#     for i in range(5):
#         print(women_matching_partner[i], ' ', women[i])
#
# # iteration1 = stable_marriage1()
# # # #
# # # print(iteration1)