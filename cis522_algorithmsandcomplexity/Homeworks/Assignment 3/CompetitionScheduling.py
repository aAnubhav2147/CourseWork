# This problem entails us to order the participants in an order
# which minimizes the completion time

import random


def smallest_completion_time():
    time = 0

    # Prompt the user to input the number of participant
    print("Please input the number of participants")
    print(' ')
    participant = int(input())

    # The easiest way to store the schedule and keep track of the participant is to use a table
    # We will represent the table in a matrix form

    completion_time = [0] * participant
    biking = [0] * participant
    swimming = [0] * participant
    riding = [0] * participant

    # The random integers below will be used to give the projected times for all the competitions
    print("Input a random integer: random1")
    print(' ')
    random1 = int(input())
    print("Input a random integer: random2")
    print(' ')
    random2 = int(input())

    # Initialize the Swimming, Biking, and Riding times
    for p in range(participant):
        swimming[p] = random.randint(random1, random2)
        biking[p] = random.randint(random1, random2)
        riding[p] = random.randint(random1, random2)

    # We will now order the participants in decreasing order of biking + riding time
    # This is done as we claim that such an order will reduce the finishing time

    for i in range(participant - 1):
        # j = i + 1
        for j in range(participant):
            if biking[i] + riding[i] < biking[j] + riding[j]:
                # The above if condition is an inversion wherein participant j is sent out
                # directly after i
                # The swapping is an exchange argument where j finishes before i
                # We will now implement the swapped schedule
                temp = swimming[i]
                swimming[i] = swimming[j]
                swimming[j] = temp

                temp = biking[i]
                biking[i] = biking[j]
                biking[j] = temp

                temp = riding[i]
                riding[i] = riding[j]
                riding[j] = temp

    for p in range(participant):
        print("Participant No.", p + 1, " Swimming Time = ", swimming[p], "Biking Time = ", biking[p],
              "Riding Time = ", riding[p])

    # Check to see if our swapped schedule has a higher completion time
    for p in range(participant):
        # Initialize the variable to count time post the first event which is swimming in our case
        time = time + swimming[p]
        if len(completion_time) > p:
            completion_time[p] = completion_time[p] + time
        else:
            completion_time[p] = time

    # The time variable here is used not only as a tracker
    # but also to check the completion time in the completion_time matrix
    # Using this eliminates all the inversions without increasing the completion time
    # This produces an optimal algorithm by comparing the approach of assuming that
    # we can order effectively by comparing the biking + riding time to the earliest completion time
    for p in range(participant):
        if biking[p] + riding[p] <= time - completion_time[p]:
            continue
        else:
            time = time + (biking[p] + riding[p]) - (time - completion_time[p])
    print(" ")
    print("For", participant, "participants", "shortest completion time is: ", time)
