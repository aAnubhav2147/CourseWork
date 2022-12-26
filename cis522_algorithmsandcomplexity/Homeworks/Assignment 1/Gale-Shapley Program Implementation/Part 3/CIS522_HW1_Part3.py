print("Anubhav Shankar_CIS 522 HW1: Part 3")
print(" ")

# In this instance, all the functions have been changed to make the women to be the proposers
# As we'll see if we assume that unlike women if men don't jump to a higher preference
# the algorithm becomes highly female-optimal i.e. the females end with their highest preference

men_pref = [['Bertha', 'Amy', 'Diane', 'Erika', 'Clare'],
            ['Diane', 'Bertha', 'Amy', 'Clare', 'Erika'],
            ['Bertha', 'Erika', 'Clare', 'Diane', 'Amy'],
            ['Amy', 'Diane', 'Clare', 'Bertha', 'Erika'],
            ['Bertha', 'Diane', 'Amy', 'Erika', 'Clare']]

men = ['Victor', 'Wyatt', 'Xavier', 'Yancey', 'Zeus']
# print(men_pref)

print(" ")

# print("Women Preference List")
print(" ")
women_pref = [['Zeus', 'Victor', 'Wyatt', 'Yancey', 'Xavier'],
              ['Xavier', 'Wyatt', 'Yancey', 'Victor', 'Zeus'],
              ['Wyatt', 'Xavier', 'Yancey', 'Zeus', 'Victor'],
              ['Victor', 'Zeus', 'Yancey', 'Xavier', 'Wyatt'],
              ['Yancey', 'Wyatt', 'Zeus', 'Xavier', 'Victor']]
women = ['Amy', 'Bertha', 'Clare', 'Diane', 'Erika']

x = 5  # For later use

def men_rolodex(str, N):
    for i in range(N):
        if men[i] == str:
            return i
    print('M')
    return 0

def women_higher_preference(str, N):
    for i in range(N):
        if women[i] == str:
            return i
    print('W')
    return 0

def stable_marriage3():
    n = len(men_pref)
    count_stop = 0
    men_preference = men_pref
    women_preference = women_pref
    women_engage = ['X'] * n
    men_match_partner = ['X'] * n
    stable_match3(count_stop, n, women_engage, men_preference, men_match_partner, women_preference)

def preference(curr_partner, new_partner, index, n, men_prefer):
    for i in range(n):
        if men_prefer[index][i] == new_partner:
            return True
        if men_prefer[index][i] == curr_partner:
            return False
    return False

def stable_match3(count_stop, n, women_pref_engaged, men_favorite, men_partner, women_favorite):
    while count_stop < n:
        for free in range(n):
            if women_pref_engaged[free] == 'X':  # Check to see if there are any free women
                break
        i = 0  # If there are free women traverse the list and make them propose to the men on their list
        while i < n and women_pref_engaged[free] == 'X':
            index = men_rolodex(women_favorite[free][i], n)
            if men_partner[index] == 'X':  # If a man hasn't been proposed to
                men_partner[index] = women[free]  # Engage him with the first free woman who proposes
                women_pref_engaged[free] = True
                count_stop = count_stop + 1

            else:  # Basically check if a stable partnership has been created
                current_partner = men_partner[index]
                if preference(current_partner, women[free], index, n, women_favorite):
                    men_partner[index] = women[free]
                    women_pref_engaged[free] = True
                    women_pref_engaged[women_higher_preference(current_partner, n)] = 'X'
                    # If not engaged, make the woman move onto the next preference
            i = i + 1
    confirm_match3(men_favorite, women_favorite, men_partner)

def confirm_match3(men_match, women_match, men_matching_partner):
    i = 0
    j = 0
    print(' ')
    print(' ')

    print("Men Preference List")
    for i in range(x):
        print(men[i], '-> ', end=' ')
        for j in range(x):
            print(men_pref[i][j], end=' ')
        print(' ')
    print(' ')
    print("Women Preference List")
    for i in range(x):
        print(women[i], '-> ', end=' ')
        for j in range(x):
            print(women_pref[i][j], end=' ')
        print(' ')
    print(' ')
    print("Confirmed Matches!")
    for i in range(x):
        print(men_matching_partner[i], ' ', men[i])